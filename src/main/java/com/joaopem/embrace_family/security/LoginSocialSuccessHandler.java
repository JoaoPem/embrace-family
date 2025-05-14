package com.joaopem.embrace_family.security;

import com.joaopem.embrace_family.model.UserAccount;
import com.joaopem.embrace_family.service.UserAccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LoginSocialSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UserAccountService userAccountService;


    public void onAuthenticationSuccess(
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication
    ) throws ServletException, IOException {

        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = oAuth2AuthenticationToken.getPrincipal();

        String email = oAuth2User.getAttribute("email");

        UserAccount userAccount = userAccountService.getByEmail(email);

        CustomAuthentication customAuthentication = new CustomAuthentication(userAccount);

        SecurityContextHolder.getContext().setAuthentication(customAuthentication);

        super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, customAuthentication);

//        try {
//            UserAccount userAccount = userAccountService.getByEmail(email);
//            authentication = new CustomAuthentication(userAccount);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
//        } catch (EntityNotFoundException e){
//            SecurityContextHolder.clearContext();
//            httpServletRequest.getSession().invalidate();
//            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            throw new BadCredentialsException("Invalid email");
//        }

    }

}
