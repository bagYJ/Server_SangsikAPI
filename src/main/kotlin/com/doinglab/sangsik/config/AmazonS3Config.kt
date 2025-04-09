package com.doinglab.sangsik.config

import com.doinglab.sangsik.api.amazon.s3.S3FileDeleter
import com.doinglab.sangsik.api.amazon.s3.S3FileDownloader
import com.doinglab.sangsik.api.amazon.s3.S3FileUploader
import com.doinglab.sangsik.api.amazon.s3.S3FileUrlGetter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AmazonS3Config {
    @Bean(name = ["s3FileUrlGetter"])
    fun s3FileUrlGetter(
        @Value("\${amazonS3.endpoint}") endPoint: String?,
        @Value("\${amazonS3.regionName}") regionName: String?,
        @Value("\${amazonS3.accessKey}") accessKey: String?,
        @Value("\${amazonS3.secretKey}") secretKey: String?,
        @Value("\${amazonS3.bucketName}") bucketName: String,
    ): S3FileUrlGetter {
        return S3FileUrlGetter(endPoint, regionName, accessKey, secretKey, bucketName)
    }

    @Bean(name = ["s3FileUploader"])
    fun s3FileUploader(
        @Value("\${amazonS3.endpoint}") endPoint: String?,
        @Value("\${amazonS3.regionName}") regionName: String?,
        @Value("\${amazonS3.accessKey}") accessKey: String?,
        @Value("\${amazonS3.secretKey}") secretKey: String?,
        @Value("\${amazonS3.bucketName}") bucketName: String,
    ): S3FileUploader {
        return S3FileUploader(endPoint, regionName, accessKey, secretKey, bucketName)
    }

    // <editor-folder desc="test">
    @Bean(name = ["s3EatHistoryImageFileUploader"])
    fun s3EatHistoryImageFileUploader(
        @Value("\${amazonS3.endpoint}") endPoint: String?,
        @Value("\${amazonS3.regionName}") regionName: String?,
        @Value("\${amazonS3.accessKey}") accessKey: String?,
        @Value("\${amazonS3.secretKey}") secretKey: String?,
        @Value("\${amazonS3.eatHistory.bucketName}") bucketName: String,
    ): S3FileUploader {
        return S3FileUploader(endPoint, regionName, accessKey, secretKey, bucketName)
    }

    @Bean(name = ["s3EatHistoryImageFileUrlGetter"])
    fun s3EatHistoryImageFileUrlGetter(
        @Value("\${amazonS3.endpoint}") endPoint: String?,
        @Value("\${amazonS3.regionName}") regionName: String?,
        @Value("\${amazonS3.accessKey}") accessKey: String?,
        @Value("\${amazonS3.secretKey}") secretKey: String?,
        @Value("\${amazonS3.eatHistory.bucketName}") bucketName: String,
    ): S3FileUrlGetter {
        return S3FileUrlGetter(endPoint, regionName, accessKey, secretKey, bucketName)
    }
    //</editor-folder>

    @Bean(name = ["s3ChatImageFileUrlGetter"])
    fun s3ChatImageFileUrlGetter(
        @Value("\${amazonS3.endpoint}") endPoint: String?,
        @Value("\${amazonS3.regionName}") regionName: String?,
        @Value("\${amazonS3.accessKey}") accessKey: String?,
        @Value("\${amazonS3.secretKey}") secretKey: String?,
        @Value("\${amazonS3.chat.bucketName}") bucketName: String,
    ): S3FileUrlGetter {
        return S3FileUrlGetter(endPoint, regionName, accessKey, secretKey, bucketName)
    }

    @Bean(name = ["s3ChatImageFileUploader"])
    fun s3ChatImageFileUploader(
        @Value("\${amazonS3.endpoint}") endPoint: String?,
        @Value("\${amazonS3.regionName}") regionName: String?,
        @Value("\${amazonS3.accessKey}") accessKey: String?,
        @Value("\${amazonS3.secretKey}") secretKey: String?,
        @Value("\${amazonS3.chat.bucketName}") bucketName: String,
    ): S3FileUploader {
        return S3FileUploader(endPoint, regionName, accessKey, secretKey, bucketName)
    }

    @Bean(name = ["s3FileDownloader"])
    fun s3FileDownloader(
        @Value("\${amazonS3.endpoint}") endPoint: String?,
        @Value("\${amazonS3.regionName}") regionName: String?,
        @Value("\${amazonS3.accessKey}") accessKey: String?,
        @Value("\${amazonS3.secretKey}") secretKey: String?,
        @Value("\${amazonS3.bucketName}") bucketName: String,
    ): S3FileDownloader {
        return S3FileDownloader(endPoint, regionName, accessKey, secretKey, bucketName)
    }

    @Bean(name = ["s3StaffResourceUploader"])
    fun s3StaffResourceUploader(
        @Value("\${amazonS3.endpoint}") endPoint: String?,
        @Value("\${amazonS3.regionName}") regionName: String?,
        @Value("\${amazonS3.accessKey}") accessKey: String?,
        @Value("\${amazonS3.secretKey}") secretKey: String?,
        @Value("\${amazonS3.staffResource.bucketName}") bucketName: String,
    ): S3FileUploader {
        return S3FileUploader(endPoint, regionName, accessKey, secretKey, bucketName)
    }

    @Bean(name = ["s3StaffResourceUrlGetter"])
    fun s3StaffResourceUrlGetter(
        @Value("\${amazonS3.endpoint}") endPoint: String?,
        @Value("\${amazonS3.regionName}") regionName: String?,
        @Value("\${amazonS3.accessKey}") accessKey: String?,
        @Value("\${amazonS3.secretKey}") secretKey: String?,
        @Value("\${amazonS3.staffResource.bucketName}") bucketName: String,
    ): S3FileUrlGetter {
        return S3FileUrlGetter(endPoint, regionName, accessKey, secretKey, bucketName)
    }

    @Bean(name = ["s3StaffResourceDeleter"])
    fun s3StaffResourceDeleter(
        @Value("\${amazonS3.endpoint}") endPoint: String?,
        @Value("\${amazonS3.regionName}") regionName: String?,
        @Value("\${amazonS3.accessKey}") accessKey: String?,
        @Value("\${amazonS3.secretKey}") secretKey: String?,
        @Value("\${amazonS3.staffResource.bucketName}") bucketName: String,
    ): S3FileDeleter {
        return S3FileDeleter(endPoint, regionName, accessKey, secretKey, bucketName)
    }


    @Bean(name = ["s3StaffHealthcareUploader"])
    fun s3StaffHealthcareUploader(
        @Value("\${amazonS3.endpoint}") endPoint: String?,
        @Value("\${amazonS3.regionName}") regionName: String?,
        @Value("\${amazonS3.accessKey}") accessKey: String?,
        @Value("\${amazonS3.secretKey}") secretKey: String?,
        @Value("\${amazonS3.staffHealthcare.bucketName}") bucketName: String,
    ): S3FileUploader {
        return S3FileUploader(endPoint, regionName, accessKey, secretKey, bucketName)
    }

    @Bean(name = ["s3StaffHealthcareUrlGetter"])
    fun s3StaffHealthcareUrlGetter(
        @Value("\${amazonS3.endpoint}") endPoint: String?,
        @Value("\${amazonS3.regionName}") regionName: String?,
        @Value("\${amazonS3.accessKey}") accessKey: String?,
        @Value("\${amazonS3.secretKey}") secretKey: String?,
        @Value("\${amazonS3.staffHealthcare.bucketName}") bucketName: String,
    ): S3FileUrlGetter {
        return S3FileUrlGetter(endPoint, regionName, accessKey, secretKey, bucketName)
    }

    @Bean(name = ["s3StaffHealthcareDeleter"])
    fun s3StaffHealthcareDeleter(
        @Value("\${amazonS3.endpoint}") endPoint: String?,
        @Value("\${amazonS3.regionName}") regionName: String?,
        @Value("\${amazonS3.accessKey}") accessKey: String?,
        @Value("\${amazonS3.secretKey}") secretKey: String?,
        @Value("\${amazonS3.staffHealthcare.bucketName}") bucketName: String,
    ): S3FileDeleter {
        return S3FileDeleter(endPoint, regionName, accessKey, secretKey, bucketName)
    }

    @Bean(name = ["s3FileDeleter"])
    fun s3FileDeleter(
        @Value("\${amazonS3.endpoint}") endPoint: String?,
        @Value("\${amazonS3.regionName}") regionName: String?,
        @Value("\${amazonS3.accessKey}") accessKey: String?,
        @Value("\${amazonS3.secretKey}") secretKey: String?,
        @Value("\${amazonS3.bucketName}") bucketName: String,
    ): S3FileDeleter {
        return S3FileDeleter(endPoint, regionName, accessKey, secretKey, bucketName)
    }

    // <editor-folder desc="test">
    @Bean(name = ["s3InquiryImageFileUploader"])
    fun s3InquiryImageFileUploader(
        @Value("\${amazonS3.endpoint}") endPoint: String?,
        @Value("\${amazonS3.regionName}") regionName: String?,
        @Value("\${amazonS3.accessKey}") accessKey: String?,
        @Value("\${amazonS3.secretKey}") secretKey: String?,
        @Value("\${amazonS3.inquiry.bucketName}") bucketName: String,
    ): S3FileUploader {
        return S3FileUploader(endPoint, regionName, accessKey, secretKey, bucketName)
    }

    @Bean(name = ["s3InquiryImageFileUrlGetter"])
    fun s3InquiryImageFileUrlGetter(
        @Value("\${amazonS3.endpoint}") endPoint: String?,
        @Value("\${amazonS3.regionName}") regionName: String?,
        @Value("\${amazonS3.accessKey}") accessKey: String?,
        @Value("\${amazonS3.secretKey}") secretKey: String?,
        @Value("\${amazonS3.inquiry.bucketName}") bucketName: String,
    ): S3FileUrlGetter {
        return S3FileUrlGetter(endPoint, regionName, accessKey, secretKey, bucketName)
    }
    //</editor-folder>
}
