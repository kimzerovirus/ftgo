package me.kzv.crud.security.service

import me.kzv.crud.domain.Member
import me.kzv.crud.domain.enum.AuthorityType
import me.kzv.crud.domain.enum.LoginType
import javax.persistence.EnumType
import javax.persistence.Enumerated


data class OAuth2Attributes(
    val attributes: Map<String, Any>,
    val nameAttributeKeys: String,
    val nickname: String,
    val email: String,
//    val profileImage: String,
    val loginType: LoginType,

    @Enumerated(EnumType.STRING)
    val authorities: List<AuthorityType> = arrayListOf(AuthorityType.ROLE_USER),
)

fun OAuth2Attributes.toEntity() = Member(
    nickname = nickname,
    email = email,
//    profileImage = profileImage,
    loginType = loginType,
    authorities = authorities,
    password = null,
)

fun ofOAuth2Attributes(
    registrationId: String,
    userNameAttributeName: String,
    attributes: Map<String, Any>
): OAuth2Attributes {
    return when (registrationId) {
        "google" -> ofGoogle(userNameAttributeName, attributes)
        "naver" -> ofNaver("id", attributes)
        "kakao" -> ofKakao("id", attributes)
        else -> throw RuntimeException("OAuth2 Login Failed") // 소셜플랫폼 아이디가 없는 경우 예외 발생
    }
}

private fun ofGoogle(userNameAttributeName: String, attributes: Map<String, Any>): OAuth2Attributes =
    OAuth2Attributes(
        nickname = attributes["name"].toString(),
        email = attributes["email"].toString(),
        // profileImage = attributes["picture"].toString(),
        loginType = LoginType.GOOGLE,
        attributes = attributes,
        nameAttributeKeys = userNameAttributeName
    )

private fun ofNaver(userNameAttributeName: String, attributes: Map<String, Any>): OAuth2Attributes {
    val response = attributes["response"] as Map<String, Any>

    return OAuth2Attributes(
        nickname = response["name"].toString(),
        email = response["email"].toString(),
        // profileImage = response["profile_image"].toString(),
        loginType = LoginType.NAVER,
        attributes = response,
        nameAttributeKeys = userNameAttributeName
    )
}

private fun ofKakao(userNameAttributeName: String, attributes: Map<String, Any>): OAuth2Attributes {
    val response = attributes["kakao_account"] as Map<String, Any>
//        val responseImage = response["profile"] as Map<String, Any>

    return OAuth2Attributes(
        nickname = response["nickname"].toString(),
        email = response["email"].toString(),
//            .profileImage = responseImage["profile_image_url"].toString(),
        loginType = LoginType.KAKAO,
        attributes = attributes,
        nameAttributeKeys = userNameAttributeName
    )
}