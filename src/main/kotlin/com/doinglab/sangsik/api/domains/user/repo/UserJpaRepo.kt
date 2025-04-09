package com.doinglab.sangsik.api.domains.user.repo

import com.doinglab.sangsik.api.domains.user.entity.User
import com.doinglab.sangsik.enums.LoginSource
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepo: JpaRepository<User, Long> {
    fun getUserById(id: Long): User?
    fun getUserByFacebookIdAndLoginSourceAndAppName(facebookId: String, loginSource: LoginSource, appName: String): User?
    fun getUserByAccessTokenAndAppName(accessToken: String, appName: String): User?
    fun getUserByEmailAndLoginSourceAndAppName(emailId: String?, loginSource: LoginSource, appName: String): User?
    fun getUserByEmailAndPasswordAndLoginSourceAndAppName(emailId: String, password: String, loginSource: LoginSource, appName: String): User?
    fun getUserByIdAndAppName(userId: Long, appName: String): User?
    fun getUsersByIdInAndAppName(userIds: List<Long>, appName: String): List<User>?
//    fun updateUserById()
}
