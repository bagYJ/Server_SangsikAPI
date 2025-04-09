package com.doinglab.sangsik.define

import com.doinglab.sangsik.api.domains.announcement.entity.QAnnouncement
import com.doinglab.sangsik.api.domains.announcement.entity.QAnnouncementUserRead
import com.doinglab.sangsik.api.domains.cgm.entity.QUserCgmBloodGlucose
import com.doinglab.sangsik.api.domains.cgm.entity.QUserCgmToken
import com.doinglab.sangsik.api.domains.chat.entity.QChatMessage
import com.doinglab.sangsik.api.domains.chat.entity.QChatReadIndex
import com.doinglab.sangsik.api.domains.chat.entity.QChatRoom
import com.doinglab.sangsik.api.domains.dietProgram.entity.QDietProgram
import com.doinglab.sangsik.api.domains.dietProgram.entity.QDietProgramEnrolledUser
import com.doinglab.sangsik.api.domains.dietProgram.entity.QDietProgramPriceList
import com.doinglab.sangsik.api.domains.food.entity.*
import com.doinglab.sangsik.api.domains.meal.entity.*
import com.doinglab.sangsik.api.domains.staff.entity.QPartnerCompany
import com.doinglab.sangsik.api.domains.staff.entity.QStaff
import com.doinglab.sangsik.api.domains.user.entity.*
import org.springframework.context.annotation.Configuration
import java.util.concurrent.ConcurrentHashMap

@Configuration
class CoreDefine {
    companion object {
        const val AUTHORIZATION_HEADER_PREFIX = "Authorization"
        const val AUTHORIZATION_TEXT_BASIC = "Basic "
        const val ACCESSTOKEN_HEADER = "AccessToken"
        const val LANGUAGE = "language"
        const val APP_NAME = "Sangsik"
        const val AES = "AES"
        const val ALTHORITHM = "$AES/CBC/PKCS5Padding"
        const val DEFAULT_CALORIE = 2000
        const val LOW_CALORIE = 1200
        const val PAGE_LIMIT = 20L
        const val MAX_IMAGE_SIZE = 512
        const val BATCH_SIZE = 500
        const val DASH = "-"
        val IGNORE_URI_PATH: List<String> = listOf(
            "/.env",
            "/.git",
            "/.aws/credentials",
            "/config/aws.yml",
            "/phpinfo.php",
            "/info.php",
            "/jianghu/ganghood/config.js",
            "/assets/css/pc/style.css",
        )
        val MESSAGE_QUEUE = ConcurrentHashMap.newKeySet<String>()

        // Qclass - Entity 관련 상수
        val CHAT_MESSAGE: QChatMessage = QChatMessage.chatMessage
        val CHAT_ROOM: QChatRoom = QChatRoom.chatRoom
        val CHAT_READ_INDEX: QChatReadIndex = QChatReadIndex.chatReadIndex
        val DIET_PROGRAM: QDietProgram = QDietProgram.dietProgram
        val EAT_HISTORY = QEatHistory.eatHistory
        val FOOD_INFO = QFoodInfo.foodInfo
        val FOOD_INFO_CUSTOM = QFoodInfoCustom.foodInfoCustom
        val FOOD_INFO_PLATE = QFoodInfoPlate.foodInfoPlate
        val FOOD_PLATE_SCALE = QFoodPlateScale.foodPlateScale
        val FOOD_SERVING_UNIT_TRANSLATION = QFoodServingUnitTranslation.foodServingUnitTranslation
        val PARTNER_COMPANY: QPartnerCompany = QPartnerCompany.partnerCompany
        val STAFF: QStaff = QStaff.staff
        val USER = QUser.user
        val USER_HISTORY = QUserHistory.userHistory
        val BLOOD_SUGAR = QBloodsugar.bloodsugar1
        val USER_PUSH_TOKEN = QUserPushToken.userPushToken
        val USER_CONTACT_POINT = QUserContactPoint.userContactPoint
        val DIET_PROGRAM_ENROLLED_USER = QDietProgramEnrolledUser.dietProgramEnrolledUser
        val USER_AGREEMENT = QUserAgreement.userAgreement
        val PUSH_USER_READ = QPushUserRead.pushUserRead
        val USER_BLOOD_SUGAR = QUserBloodSugar.userBloodSugar
        val USER_BLOOD_GLUCOSE = QUserBloodGlucose.userBloodGlucose
        val ANNOUNCEMENT = QAnnouncement.announcement
        val ANNOUNCEMENT_USER_READ = QAnnouncementUserRead.announcementUserRead
        val DIET_PROGRAM_PRICE_LIST = QDietProgramPriceList.dietProgramPriceList
        val EAT_HISTORY_FOOD = QEatHistoryFood.eatHistoryFood
        val EAT_HISTORY_COMMENT = QEatHistoryComment.eatHistoryComment
        val FAVORITE_EAT_HISTORY = QFavoriteEatHistory.favoriteEatHistory
        val FAVORITE_EAT_HISTORY_FOOD = QFavoriteEatHistoryFood.favoriteEatHistoryFood
        val REPEAT_EAT_HISTORY = QRepeatEatHistory.repeatEatHistory
        val REPEAT_EAT_HISTORY_FOOD = QRepeatEatHistoryFood.repeatEatHistoryFood
        val FAVORITE_LINK = QFavoriteLink.favoriteLink
        val REPEAT_LINK = QRepeatLink.repeatLink
        val MULTI_PREDICT_POSITIONS = QMultiPredictPositions.multiPredictPositions
        val APP_SETTING = QAppSetting.appSetting
        val FOOD_INFO_R2 = QFoodInfoR2.foodInfoR2
        val USER_CGM_TOKEN = QUserCgmToken.userCgmToken
        val USER_CGM_BLOOD_GLUCOSE = QUserCgmBloodGlucose.userCgmBloodGlucose
    }
}
