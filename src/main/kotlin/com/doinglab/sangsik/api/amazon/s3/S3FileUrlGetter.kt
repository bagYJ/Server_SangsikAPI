package com.doinglab.sangsik.api.amazon.s3

import com.amazonaws.HttpMethod
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest
import org.springframework.cache.annotation.Cacheable
import java.util.*

open class S3FileUrlGetter(
    endPoint: String?,
    region: String?,
    accessKey: String?,
    secretKey: String?,
    private val bucketName: String,
) {
    //AmazonS3 클라이언트는 Thread safe 하게 동작한다.
    //link : https://forums.aws.amazon.com/thread.jspa?messageID=191643&#191643
    private var s3Client: AmazonS3? = null

    @Cacheable(key = "#fileName", value = ["s3Image"], unless = "#result == null")
    open fun getFileUrl(fileName: String?): String {
        val expiration = Date()
        var milliSeconds = expiration.time
        milliSeconds += 1000 * 60 * 60.toLong() // Add 1 hour.
        expiration.time = milliSeconds
        val generatePresignedUrlRequest = GeneratePresignedUrlRequest(bucketName, fileName)
        generatePresignedUrlRequest.method = HttpMethod.GET
        generatePresignedUrlRequest.expiration = expiration
        val url = s3Client!!.generatePresignedUrl(generatePresignedUrlRequest)
        return url.toString()
    }

    @Cacheable(key = "#fileName", value = ["s3Image"], unless = "#result == null")
    open fun getFileUrlWithoutPresign(fileName: String?): String {
        val url = s3Client!!.getUrl(bucketName, fileName)
        return url.toString()
    }

    init {
        val awsCreds = BasicAWSCredentials(accessKey, secretKey)
        s3Client = AmazonS3ClientBuilder.standard()
            .withEndpointConfiguration(EndpointConfiguration(endPoint, region))
            .withCredentials(AWSStaticCredentialsProvider(awsCreds))
            .build()
    }
}
