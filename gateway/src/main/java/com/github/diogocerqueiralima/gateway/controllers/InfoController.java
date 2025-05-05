package com.github.diogocerqueiralima.gateway.controllers;

import com.github.diogocerqueiralima.gateway.dto.UserDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/info")
public class InfoController {

    @GetMapping("/me")
    public Mono<UserDto> me(@AuthenticationPrincipal OidcUser user) {

        OidcUserInfo userInfo = user.getUserInfo();

        return Mono.just(
                new UserDto(
                        userInfo.getEmail(), userInfo.getGivenName(), userInfo.getFamilyName(),
                        userInfo.getFullName(), userInfo.getPicture()
                )
        );
    }

}
