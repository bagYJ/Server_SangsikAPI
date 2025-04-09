package com.doinglab.sangsik.api.amazon.s3

import com.amazonaws.services.s3.model.S3Object
import com.doinglab.sangsik.Exception.NotAllowedFileTypeException
import com.doinglab.sangsik.utils.TokenGenerator
import com.doinglab.sangsik.utils.sha256
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.io.InputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class AmazonS3Service(
    private val s3FileUploader: S3FileUploader,
    private val s3FileUrlGetter: S3FileUrlGetter,
    private val s3ChatImageFileUploader: S3FileUploader,
    private val s3ChatImageFileUrlGetter: S3FileUrlGetter,
    private val s3EatHistoryImageFileUploader: S3FileUploader,
    private val s3EatHistoryImageFileUrlGetter: S3FileUrlGetter,
    private val s3InquiryImageFileUploader: S3FileUploader,
    private val s3InquiryImageFileUrlGetter: S3FileUrlGetter,
) {
    fun getFile(
        filename: String
    ): S3Object? =
        s3FileUploader.getFile(filename)

    fun getCheckSum(
        filename: String
    ): String =
        if (filename.isNotEmpty()) {
            getFile(filename)?.objectContent?.sha256() ?: ""
        } else ""

    @Throws(IOException::class)
    private fun uploadFile(filename: String, file: MultipartFile, fileUploader: S3FileUploader) {
        fileUploader.uploadFileAsync(file.inputStream, filename, file.size, file.contentType)
    }

    @Throws(IOException::class)
    private fun uploadFile(filename: String, file: InputStream, size: Long, contentType: String?, fileUploader: S3FileUploader) {
        fileUploader.uploadFileAsync(file, filename, size, contentType)
    }

    @Throws(IOException::class)
    private fun copyFile(filename: String, sourceBucketName: String, destinationBucketName: String, fileUploader: S3FileUploader) {
        fileUploader.copyFile(filename, sourceBucketName, destinationBucketName)
    }

    private fun makeFilePublic(filename: String, fileUploader: S3FileUploader) {
        fileUploader.setFilePublic(filename)
    }

    private fun getFileURL(fileUrlGetter: S3FileUrlGetter, filename: String, isPreSigned: Boolean): String {
        val url = if (isPreSigned) {
            fileUrlGetter.getFileUrl(filename)
        } else {
            fileUrlGetter.getFileUrlWithoutPresign(filename)
        }
        return url
    }

    @Throws(IOException::class)
    fun uploadInquiryImageFileAndGetName(file: MultipartFile): String {
        val fileExt = getFileExtensionFromContentType(file.contentType)
        val filename = getSaveFilePathByDate() + TokenGenerator.generateNewToken() + "." + fileExt

        uploadFile(filename, file, s3InquiryImageFileUploader)
        return filename
    }

    @Throws(IOException::class)
    fun uploadChatImageFileAndGetName(file: MultipartFile): String {
        val fileExt = getFileExtensionFromContentType(file.contentType)
        val filename = getSaveFilePathByDate() + TokenGenerator.generateNewToken() + "." + fileExt

        uploadFile(filename, file, s3ChatImageFileUploader)
        return filename
    }

    fun getChatImageFileURL(filename: String?, isPreSigned: Boolean): String {
        if (filename.isNullOrEmpty()) return ""

        return if (isPreSigned) {
            s3ChatImageFileUrlGetter.getFileUrl(filename)
        } else {
            s3ChatImageFileUrlGetter.getFileUrlWithoutPresign(filename)
        }
    }

    @Throws(IOException::class)
    fun uploadFoodImageFileAndGetName(file: MultipartFile): String {
        val fileExt = getFileExtensionFromContentType(file.contentType)
        val filename = getSaveFilePathByDate() + TokenGenerator.generateNewToken() + "." + fileExt
        uploadFile(filename, file, s3FileUploader)
        return filename
    }

    private fun getSaveFilePathByDate() = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/HH/"))

    fun getFoodImageFileURL(filename: String?, isPreSigned: Boolean = true): String {
        if (filename.isNullOrEmpty()) {
            return ""
        }

        return if (isPreSigned) {
            s3FileUrlGetter.getFileUrl(filename)
        } else {
            s3FileUrlGetter.getFileUrlWithoutPresign(filename)
        }
    }

    @Throws(IOException::class)
    fun uploadEatHistoryImageFileAndGetName(file: MultipartFile, prefix: String? = null): String {
        val fileExt = getFileExtensionFromContentType(file.contentType)
        val filename = (if (prefix != null) "$prefix/" else "") + getSaveFilePathByDate() + TokenGenerator.generateNewToken() + "." + fileExt

        uploadFile(filename, file, s3EatHistoryImageFileUploader)
        return filename
    }

    fun getEatHistoryImageFileURL(filename: String?, isPreSigned: Boolean = true): String {
        if (filename.isNullOrEmpty()) {
            return ""
        }

        return if (isPreSigned) {
            s3EatHistoryImageFileUrlGetter.getFileUrl(filename)
        } else {
            s3EatHistoryImageFileUrlGetter.getFileUrlWithoutPresign(filename)
        }
    }

    fun copyEatHistoryImageFile(filename: String) {
        copyFile(filename, s3FileUploader.bucketName, s3EatHistoryImageFileUploader.bucketName, s3FileUploader)
    }

    @Throws(NotAllowedFileTypeException::class)
    private fun getFileExtensionFromContentType(contentType: String?): String {
        return when (contentType!!.lowercase()) {
            "image/jpg", "image/jpeg" -> "jpg"
            "image/png" -> "png"
            else -> throw NotAllowedFileTypeException()
        }
    }
}
