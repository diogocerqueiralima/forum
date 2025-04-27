package com.github.diogocerqueiralima.gateway.controllers;

import com.github.diogocerqueiralima.gateway.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {

    @GetMapping("/me")
    public ResponseEntity<UserDto> me(@AuthenticationPrincipal OidcUser user) {

        OidcUserInfo userInfo = user.getUserInfo();

        return ResponseEntity.ok(
                new UserDto(
                        userInfo.getEmail(), userInfo.getGivenName(), userInfo.getFamilyName(),
                        userInfo.getFullName(), userInfo.getPicture()
                )
        );
    }

}
