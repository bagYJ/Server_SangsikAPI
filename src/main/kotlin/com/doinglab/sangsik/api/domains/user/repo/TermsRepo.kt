package com.doinglab.sangsik.api.domains.user.repo

import com.doinglab.sangsik.api.domains.user.entity.AppSetting
import com.doinglab.sangsik.define.CoreDefine.Companion.APP_NAME
import com.doinglab.sangsik.define.CoreDefine.Companion.APP_SETTING
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository

@Repository
class TermsRepo(
    @Qualifier("dietJpaQueryFactory") private val jpaQueryFactory: JPAQueryFactory
) {
    fun findAppSetting(): AppSetting.Dto =
        jpaQueryFactory.selectFrom(APP_SETTING)
            .where(APP_SETTING.appName.eq(APP_NAME)).fetchFirst().toDto()
}
