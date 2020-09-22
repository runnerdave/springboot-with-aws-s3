package net.runnerdave.s3prac.profile;

import net.runnerdave.s3prac.bucket.BucketName;
import net.runnerdave.s3prac.filestore.FileStore;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
public class UserProfileService {

    private static Logger log = LoggerFactory.getLogger(UserProfile.class);

    private final UserProfileDataAccessService userProfileDataAccessService;

    private FileStore fileStore;

    @Autowired
    public UserProfileService(UserProfileDataAccessService userProfileDataAccessService, FileStore fileStore) {
        this.userProfileDataAccessService = userProfileDataAccessService;
        this.fileStore = fileStore;
    }

    List<UserProfile> getUserProfiles() {
        return userProfileDataAccessService.getUserProfiles();
    }

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file!");
        }

        if (!Arrays.asList(IMAGE_JPEG.toString(), IMAGE_GIF, IMAGE_PNG).contains(file.getContentType())) {
            log.error("Not an image, file type: {}", file.getContentType());
            throw new IllegalStateException("File has to be image!");
        }

        UserProfile userProfile = userProfileDataAccessService.getUserProfile(userProfileId);
        if (userProfile == null) {
            throw new IllegalStateException("User not found");
        }

        log.info(userProfile.getUsername());
        log.info(String.valueOf(file.getSize()));

        Map<String, String> optionalMetadata = new HashMap<>();
        optionalMetadata.put("Content-Length", String.valueOf(file.getSize()));
        optionalMetadata.put("Content-Type", file.getContentType());

        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), userProfile.getUserProfileId());
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
        try {
            fileStore.save(path, filename, Optional.of(optionalMetadata), file.getInputStream());
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new IllegalStateException(e);
        }

//        original solution below
//        if (!file.isEmpty()) {
//            log.info(file.getContentType());
//            if (file.getContentType().equalsIgnoreCase("image/jpeg")) {
//                UserProfile userProfile = userProfileDataAccessService.getUserProfile(userProfileId);
//                if (userProfile != null) {
//                    log.info(userProfile.getUsername());
//                    log.info(String.valueOf(file.getSize()));
//                    userProfileDataAccessService.updateUserProfileLink(userProfileId, file.getName());
//                    Map<String, String> optionalMetadata = new HashMap<>();
//                    optionalMetadata.put("size", String.valueOf(file.getSize()));
//                    optionalMetadata.put("type", String.valueOf(file.getContentType()));
//                    try {
//                        fileStore.save(BucketName.PROFILE_IMAGE.getBucketName(), file.getOriginalFilename(), Optional.of(optionalMetadata), file.getInputStream());
//                    } catch (IOException e) {
//                        log.error(e.getMessage());
//                    }
//                }
//            }
//        }
    }
}
