package com.github.diogocerqueiralima.gateway.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {

    @GetMapping("/me")
    public ResponseEntity<String> me(@AuthenticationPrincipal OidcUser user) {
        return ResponseEntity.ok(user.getUserInfo().getFullName());
    }

}
