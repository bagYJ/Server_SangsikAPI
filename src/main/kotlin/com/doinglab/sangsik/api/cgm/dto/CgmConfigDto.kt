package com.doinglab.sangsik.api.cgm.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "cgm")
data class CgmConfigDto(
    var clientId: String = "",
    var clientSecret: String = "",
    var goodsUri: String = "",
    var timeout: Int = 0,
    var redirectUri: String = "",
    var account: Account = Account(),
    var api: Api = Api()
) {
    data class Account(
        var uri: String = "",
        var authorize: AccountInfo = AccountInfo(),
        var token: AccountInfo = AccountInfo(),
        var refresh: AccountInfo = AccountInfo(),
        var logout: AccountInfo = AccountInfo(),
    ) {
        data class AccountInfo(
            var path: String = "",
            @JsonProperty("type-name")
            var typeName: String = "",
            var type: String = "",
        )
    }

    data class Api(
        var uri: String = "",
        var sensors: ApiInfo = ApiInfo(),
        var cgms: ApiInfo = ApiInfo(),
    ) {
        data class ApiInfo(
            var path: String = "",
        )
    }

    fun tokenUri(): String = "${account.uri}${account.token.path}"
    fun refreshUri(): String = "${account.uri}${account.refresh.path}"
    fun sensorsUri(): String = "${api.uri}${api.sensors.path}"
    fun cgmsUri(): String = "${api.uri}${api.cgms.path}"
}
