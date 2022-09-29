package me.kzv.crud.security.service

import me.kzv.crud.domain.Member
import me.kzv.crud.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class CustomOAuth2UserService(
    private val memberRepository: MemberRepository,
) : OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val delegate: OAuth2UserService<OAuth2UserRequest, OAuth2User> = DefaultOAuth2UserService()
        val oAuth2User = delegate.loadUser(userRequest)

        // 현재 로그인하는 소셜 플랫폼 id
        val socialPlatformId = userRequest.clientRegistration.registrationId
        val userNameAttributeName = userRequest.clientRegistration.providerDetails.userInfoEndpoint.userNameAttributeName

        val attributes: OAuth2Attributes = ofOAuth2Attributes(socialPlatformId, userNameAttributeName, oAuth2User.attributes)
        val member: Member = getMember(attributes)

        val grantedAuthority: MutableList<GrantedAuthority> = arrayListOf()
        member.authorities.forEach { authority -> grantedAuthority.add(SimpleGrantedAuthority(authority.toString())) }

        return DefaultOAuth2User(
            grantedAuthority,
            attributes.attributes,
            attributes.nameAttributeKeys
        )
    }

    private fun getMember(attributes: OAuth2Attributes) =
        memberRepository.findByIdOrNull(attributes.email) ?: memberRepository.save(attributes.toEntity())

}