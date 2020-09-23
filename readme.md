# Learn springboot and AWS s3

Code for the course: https://www.udemy.com/course/spring-boot-and-aws-s3

In this course you will learn how to upload and download files (images) for a react front end application.

You will implement everything from scratch using Spring Boot for the backend and Amazon S3 to store files (images).

This course will allow you store any files that you can think of, a common feature that most applications have. Whether it's a web app or mobile app, what you will build will allow any client to upload files.

What you’ll learn
- Spring Boot
- React
- How to upload photos to Amazon S3
- AWS S3
- Building API's

## Prerequisites

Create a bucket in AWS s3 and save its name in: src/main/java/net/runnerdave/s3prac/bucket/BucketName.java

Also create keys and save it in:  src/main/java/net/runnerdave/s3prac/config/AmazonConfig.java

## Running the app

- Backend:
````
    ./gradlew bootRun
````
    
- Frontend:

````
    cd /src/main/ui/
    nvm use 12.18.3
    npm start
````