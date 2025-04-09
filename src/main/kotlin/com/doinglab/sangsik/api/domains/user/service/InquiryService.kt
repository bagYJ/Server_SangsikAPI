package com.doinglab.sangsik.api.domains.user.service

import com.doinglab.sangsik.Exception.CustomException
import com.doinglab.sangsik.api.amazon.s3.AmazonS3Service
import com.doinglab.sangsik.api.domains.common.dto.CustomDto
import com.doinglab.sangsik.api.domains.user.entity.Inquiry
import com.doinglab.sangsik.api.domains.user.entity.User
import com.doinglab.sangsik.api.domains.user.repo.InquiryRepo
import com.doinglab.sangsik.enums.InquiryType
import com.doinglab.sangsik.enums.StatusCode
import com.doinglab.sangsik.utils.UtilsMail
import com.doinglab.sangsik.utils.toJsonString
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
@Transactional
class InquiryService(
    private val inquiryRepo: InquiryRepo,
    private val amazonS3Service: AmazonS3Service
) {
    @Value("\${inquiry.to}")
    private lateinit var to: String
    @Value("\${inquiry.title}")
    private lateinit var title: String
    @Value("\${inquiry.resource}")
    private lateinit var resource: String

    fun doPostInquiry(user: User.Dto, inquiryType: InquiryType, content: String, files: List<MultipartFile>?): CustomDto {
        val fileNames = files?.let { file ->
            if (file.count() > 10) throw CustomException(StatusCode.CHECK_OVER_FILE)

            file.map { amazonS3Service.uploadInquiryImageFileAndGetName(it) }
        }
        return inquiryRepo.saveInquiry(Inquiry.Dto(
            id = 0L,
            userId = user.id,
            inquiryType = inquiryType,
            content = content,
            files = fileNames?.toJsonString()
        ))?.let {
            if (it.id < 1) throw CustomException(StatusCode.FAIL_INSERT)

            UtilsMail.send(
                to = to,
                subject = title,
                contents = javaClass.getResource(resource)!!.readText()
                    .replace("{{NAME}}", user.name)
                    .replace("{{EMAIL}}", user.email)
                    .replace("{{CATEGORY}}", inquiryType.desc)
                    .replace("{{CONTENT}}", content),
                files = files
            )

            CustomDto()
        } ?:  throw CustomException(StatusCode.FAIL_INSERT)
    }
}
