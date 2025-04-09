package com.doinglab.sangsik.api.amazon.s3

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.*
import org.springframework.scheduling.annotation.Async
import java.io.IOException
import java.io.InputStream

open class S3FileUploader(
    endPoint: String?,
    region: String?,
    accessKey: String?,
    secretKey: String?,
    val bucketName: String,
) {
    //AmazonS3 클라이언트는 Thread safe 하게 동작한다.
    //link : https://forums.aws.amazon.com/thread.jspa?messageID=191643&#191643
    private var s3Client: AmazonS3? = null

    @Throws(IOException::class)
    fun uploadFile(file: InputStream?, filename: String?, size: Long) {
        val metadata = ObjectMetadata()
        metadata.contentLength = size
        s3Client!!.putObject(PutObjectRequest(bucketName, filename, file, metadata))
    }

    @Async
    @Throws(IOException::class)
    open fun uploadFileAsync(file: InputStream?, filename: String?, size: Long, contentType: String?) {
        val metadata = ObjectMetadata()
        metadata.contentLength = size
        metadata.contentType = contentType
        s3Client!!.putObject(PutObjectRequest(bucketName, filename, file, metadata))
    }

    fun setFilePublic(filename: String?) {
        val acl = s3Client!!.getObjectAcl(bucketName, filename)
        acl.grantPermission(GroupGrantee.AllUsers, Permission.Read)
        s3Client!!.setObjectAcl(bucketName, filename, acl)
    }

    fun getFile(filename: String): S3Object? {
        return try {
            s3Client!!.getObject(bucketName, filename)
        } catch (e: Exception) {
            null
        }
    }

    fun copyFile(filename: String, sourceBucketName: String, destinationBucketName: String) {
        s3Client!!.copyObject(CopyObjectRequest(sourceBucketName, filename, destinationBucketName, filename))
    }

    init {
        val awsCredentials = BasicAWSCredentials(accessKey, secretKey)
        s3Client = AmazonS3ClientBuilder.standard() //.withRegion("ap-northeast-2")
            .withEndpointConfiguration(EndpointConfiguration(endPoint, region))
            .withCredentials(AWSStaticCredentialsProvider(awsCredentials))
            .build()
    }
}
