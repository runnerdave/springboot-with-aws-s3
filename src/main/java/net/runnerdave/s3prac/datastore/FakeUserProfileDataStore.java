package net.runnerdave.s3prac.datastore;

import net.runnerdave.s3prac.profile.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {
    private static Logger logger = LoggerFactory.getLogger(FakeUserProfileDataStore.class);

    public static final List<UserProfile> USER_PROFILE = new ArrayList<>();

    static {
        USER_PROFILE.add(new UserProfile(UUID.randomUUID(), "janetjones", null));
        USER_PROFILE.add(new UserProfile(UUID.randomUUID(), "antoniojunior", null));
    }

    public static List<UserProfile> getUserProfiles() {
        return USER_PROFILE;
    }

    public static UserProfile getUserProfile(UUID uuid) {
        return USER_PROFILE.stream().filter(user -> user.getUserProfileId().equals(uuid)).findAny().orElse(null);
    }

    public static void updateUserProfileImageLink(UUID uuid, String userProfileLink) {
        UserProfile userProfile = USER_PROFILE.stream()
                .filter(user -> user.getUserProfileId().equals(uuid)).findAny().orElse(null);
        if (userProfile != null) {
            userProfile.setUserProfileImageLink(userProfileLink);
        }
    }
}
