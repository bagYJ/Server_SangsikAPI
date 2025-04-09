package com.doinglab.sangsik.api.domains.user.controller

import com.doinglab.sangsik.annotations.Auth
import com.doinglab.sangsik.api.domains.auth.entity.ClientCompany
import com.doinglab.sangsik.api.domains.common.dto.BaseDto
import com.doinglab.sangsik.api.domains.common.dto.CustomDto
import com.doinglab.sangsik.api.domains.user.dto.request.*
import com.doinglab.sangsik.api.domains.user.dto.response.ResponseGetUserDto
import com.doinglab.sangsik.api.domains.user.dto.response.ResponseGetUserEmailAvailabilityDto
import com.doinglab.sangsik.api.domains.user.dto.response.ResponsePutUserPasswordCodeDto
import com.doinglab.sangsik.api.domains.user.dto.response.ResponseUserDto
import com.doinglab.sangsik.api.domains.user.entity.User
import com.doinglab.sangsik.api.domains.user.service.UserService
import com.doinglab.sangsik.enums.UnitHeight
import com.doinglab.sangsik.enums.UnitWeight
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "user", description = "회원")
@RequestMapping(value = ["/user"])
class UserController(
    private val userService: UserService
) {
    @Operation(summary = "회원 정보 반환")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "")]
    )
    @GetMapping("")
    fun doGetUser(
        @Auth auth: User.Dto
    ): ResponseEntity<ResponseGetUserDto> =
        ResponseEntity(userService.doGetUser(auth), HttpStatus.OK)

    @Operation(summary = "회원 정보 수정")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />fail.update: 수정 실패")]
    )
    @PutMapping("")
    fun doPutUser(
        @RequestBody request: RequestPutUserDto,
        @Auth auth: User.Dto
    ): ResponseEntity<ResponseGetUserDto> =
        ResponseEntity(userService.doPutUser(auth, request), HttpStatus.OK)

    @Operation(summary = "회원 정보(키단위) 수정")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />fail.update: 수정 실패")]
    )
    @PutMapping("/unit/height")
    fun doPutUserUnitHeight(
        @Parameter(description = "키 단위 (CM: cm, FT: feet)") @RequestParam unit: UnitHeight,
        @Auth auth: User.Dto
    ): ResponseEntity<ResponseGetUserDto> =
        ResponseEntity(userService.doPutUserUnitHeight(auth, unit), HttpStatus.OK)

    @Operation(summary = "회원 정보(몸무게단위) 수정")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />fail.update: 수정 실패")]
    )
    @PutMapping("/unit/weight")
    fun doPutUserUnitWeight(
        @Parameter(description = "몸무게 단위 (KG: kg, LBS: pound)") @RequestParam unit: UnitWeight,
        @Auth auth: User.Dto
    ): ResponseEntity<ResponseGetUserDto> =
        ResponseEntity(userService.doPutUserUnitWeight(auth, unit), HttpStatus.OK)

    @Operation(summary = "회원 가입")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />check.email.pattern: 이메일 패턴 확인" +
                "<br />already.email: 등록된 이메일" +
                "<br />check.terms.of.service.agree: 서비스 이용 약관 확인" +
                "<br />check.collection.personal.information.agree: 개인정보 수집 및 이용 확인" +
                "<br />check.personal.information.agree: 개인정보 처리방침 확인" +
                "<br />check.over.age.14.agree: 만 14세 이상 확인" +
                "<br />fail.insert: 등록 실패")]
    )
    @PostMapping("")
    fun doPostUser(
        @RequestBody request: RequestPostUserDto,
        @RequestAttribute("sangsikCompany") sangsikCompany: ClientCompany.Dto,
    ): ResponseEntity<ResponseUserDto> =
        ResponseEntity(userService.doPostUser(request, sangsikCompany), HttpStatus.OK)

    @Operation(summary = "회원 로그인")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />check.email.pattern: 이메일 패턴 확인" +
                "<br />unsamed.password: 비밀번호 불일치")]
    )
    @PutMapping("/login")
    fun doPutUserLogin(
        @RequestBody request: RequestUserDto
    ): ResponseEntity<ResponseUserDto> {
        val response = userService.doPutUserLogin(request)

        return ResponseEntity(response, (if (response.body.firstLogin) HttpStatus.CREATED else HttpStatus.OK))
    }

    @Operation(summary = "푸시 토큰 저장")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "")]
    )
    @PutMapping("/token")
    fun doPutUserToken(
        @RequestBody request: RequestPutUserTokenDto,
        @Auth auth: User.Dto
    ): ResponseEntity<CustomDto> =
        ResponseEntity(userService.doPutUserToken(auth, request), HttpStatus.OK)

    @Operation(summary = "이메일 중복 확인")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />check.email.pattern: 이메일 패턴 확인")]
    )
    @GetMapping("/email/availability")
    fun doGetUserEmailAvailability(
        @Parameter(description = "이메일")
        @RequestParam email: String,
    ): ResponseEntity<ResponseGetUserEmailAvailabilityDto> =
        ResponseEntity(userService.doGetUserEmailAvailability(email), HttpStatus.OK)

    @Operation(summary = "비밀번호 재설정 코드 메일 발송")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />check.email.pattern: 이메일 패턴 확인")]
    )
    @PostMapping("/email/password/reset")
    fun doPostUserEmailPasswordReset(
        @RequestBody request: RequestUserEmailDto
    ): ResponseEntity<BaseDto> =
        ResponseEntity(userService.doPostUserEmailPasswordReset(request), HttpStatus.OK)

    @Operation(summary = "비밀번호 재설정 코드 확인")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />check.email.pattern: 이메일 패턴 확인" +
                "<br />check.code.length: 코드 길이 확인" +
                "<br />expired.code: 코드 만료" +
                "<br />unsamed.code: 코드 불일치" +
                "<br />empty.code: 코드 없음" +
                "<br />not.found.user: 사용자 없음")]
    )
    @PutMapping("/password/code")
    fun doPutUserPasswordCode(
        @RequestBody request: RequestPutUserPasswordCodeDto
    ): ResponseEntity<ResponsePutUserPasswordCodeDto> =
        ResponseEntity(userService.doPutUserPasswordCode(request), HttpStatus.OK)

    @Operation(summary = "비밀번호 변경")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />check.email.pattern: 이메일 패턴 확인" +
                "<br />check.code.length: 코드 길이 확인" +
                "<br />check.password.length: 비밀번호 길이 확인" +
                "<br />unsamed.password: 비밀번호 불일치" +
                "<br />unsamed.code: 코드 불일치" +
                "<br />empty.code: 코드 없음" +
                "<br />not.found.user: 사용자 없음")]
    )
    @PutMapping("/password")
    fun doPutUserPassword(
        @RequestBody request: RequestPutUserPasswordDto
    ): ResponseEntity<BaseDto> =
        ResponseEntity(userService.doPutUserPassword(request), HttpStatus.OK)

    @Operation(summary = "회원약관 등록")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />check.terms.of.service.agree: 서비스 이용 약관 확인" +
                "<br />check.collection.personal.information.agree: 개인정보 수집 및 이용 확인" +
                "<br />check.personal.information.agree: 개인정보 처리방침 확인" +
                "<br />check.over.age.14.agree: 만 14세 이상 확인" +
                "<br />not.found.user: 사용자 없음" +
                "<br />fail.insert: 등록 실패")]
    )
    @PostMapping("/agreement")
    fun doPostUserAgreement(
        @RequestBody request: RequestPostUserAgreementDto,
        @Auth auth: User.Dto
    ): ResponseEntity<CustomDto> =
        ResponseEntity(userService.doPostUserAgreement(auth, request), HttpStatus.OK)

    @Operation(summary = "회원약관 마케팅 정보 수신 설정")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />not.found.user: 사용자 없음" +
                "<br />fail.update: 수정 실패")]
    )
    @PutMapping("/agreement/marketing")
    fun doPutUserAgreementMarketing(
        @Parameter(description = "광고성 정보 수신 여부") @RequestParam isSelectablePersonalMarketing: Boolean,
        @Auth auth: User.Dto
    ): ResponseEntity<CustomDto> =
        ResponseEntity(userService.doPutUserAgreementMarketing(auth.id, isSelectablePersonalMarketing), HttpStatus.OK)

    @Operation(summary = "회원탈퇴")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />fail.insert: 등록 실패")]
    )
    @DeleteMapping("")
    fun doDeleteUser(
        @RequestBody request: RequestDeleteUserDto,
        @Auth auth: User.Dto
    ): ResponseEntity<CustomDto> =
        ResponseEntity(userService.doDeleteUser(auth, request), HttpStatus.OK)

    @Operation(summary = "비밀번호 변경 체크")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />unsamed.password: 비밀번호 불일치")]
    )
    @PostMapping("/password/check")
    fun doPostUserPasswordCheck(
        @Parameter(description = "현재 비밀번호") @RequestParam password: String,
        @Auth auth: User.Dto
    ): ResponseEntity<CustomDto> =
        ResponseEntity(userService.doPostUserPasswordCheck(auth, password), HttpStatus.OK)

    @Operation(summary = "비밀번호 변경")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />check.password.length: 비밀번호 길이 확인" +
                "<br />unsamed.password: 비밀번호 불일치" +
                "<br />fail.update: 수정 실패")]
    )
    @PutMapping("/password/change")
    fun doPutUserPasswordChange(
        @RequestBody request: RequestPutUserPasswordChangeDto,
        @Auth auth: User.Dto
    ): ResponseEntity<CustomDto> =
        ResponseEntity(userService.doPutUserPasswordChange(auth, request), HttpStatus.OK)

    @Operation(summary = "로그아웃")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />not.found.user: 사용자 없음")]
    )
    @GetMapping("/logout")
    fun doGetUserLogout(
        @Auth auth: User.Dto
    ): ResponseEntity<CustomDto> =
        ResponseEntity(userService.doGetUserLogout(auth), HttpStatus.OK)
}
