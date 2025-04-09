package com.doinglab.sangsik.api.amazon.s3

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder

class S3FileDeleter(
    endPoint: String?,
    region: String?,
    accessKey: String?,
    secretKey: String?,
    private val bucketName: String,
) {
    //AmazonS3 클라이언트는 Thread safe 하게 동작한다.
    //link : https://forums.aws.amazon.com/thread.jspa?messageID=191643&#191643
    private var s3Client: AmazonS3? = null
    fun deleteFile(filename: String?) {
        s3Client!!.deleteObject(bucketName, filename)
    }

    init {
        val awsCredentials = BasicAWSCredentials(accessKey, secretKey)
        s3Client = AmazonS3ClientBuilder.standard() //.withRegion("ap-northeast-2")
            .withEndpointConfiguration(EndpointConfiguration(endPoint, region))
            .withCredentials(AWSStaticCredentialsProvider(awsCredentials))
            .build()
    }
}
