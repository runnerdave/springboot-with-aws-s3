package net.runnerdave.s3prac.bucket;

public enum BucketName {
    PROFILE_IMAGE("runnerdave-s3-image-upload");

    public String getBucketName() {
        return bucketName;
    }

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
