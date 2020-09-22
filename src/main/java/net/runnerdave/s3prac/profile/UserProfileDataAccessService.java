package net.runnerdave.s3prac.profile;

import net.runnerdave.s3prac.datastore.FakeUserProfileDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserProfileDataAccessService {
    private final FakeUserProfileDataStore fakeUserProfileDataStore;

    @Autowired
    public UserProfileDataAccessService(FakeUserProfileDataStore fakeUserProfileDataStore) {
        this.fakeUserProfileDataStore = fakeUserProfileDataStore;
    }

    List<UserProfile> getUserProfiles() {
        return fakeUserProfileDataStore.getUserProfiles();
    }

    UserProfile getUserProfile(UUID uuid) {
        return fakeUserProfileDataStore.getUserProfile(uuid);
    }

    void updateUserProfileLink(UUID uuid, String link) {
        fakeUserProfileDataStore.updateUserProfileImageLink(uuid, link);
    }
}
