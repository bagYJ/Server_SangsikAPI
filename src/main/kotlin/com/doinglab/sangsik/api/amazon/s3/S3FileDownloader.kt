package com.doinglab.sangsik.api.amazon.s3

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.GetObjectRequest
import java.io.File

class S3FileDownloader(
    endPoint: String?,
    region: String?,
    accessKey: String?,
    secretKey: String?,
    private val bucketName: String,
) {
    private var s3Client: AmazonS3? = null
    fun downloadFile(s3Filename: String?, saveFilename: String?) {
        saveFilename?.let {
            val file = File(saveFilename)
            s3Client!!.getObject(GetObjectRequest(bucketName, s3Filename), file)
        }
    }

    init {
        val awsCredentials = BasicAWSCredentials(accessKey, secretKey)
        s3Client = AmazonS3ClientBuilder.standard() //.withRegion("ap-northeast-2")
            .withEndpointConfiguration(EndpointConfiguration(endPoint, region))
            .withCredentials(AWSStaticCredentialsProvider(awsCredentials))
            .build()
    }
}
