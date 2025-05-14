package com.joaopem.embrace_family.security;

import com.joaopem.embrace_family.model.UserAccount;
import com.joaopem.embrace_family.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserAccountService userAccountService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = authentication.getName();
        String rawPassword = authentication.getCredentials().toString();

        UserAccount userAccount = userAccountService.getByEmail(email);

        if (userAccount == null || !passwordEncoder.matches(rawPassword, userAccount.getPassword())){
            throw new BadCredentialsException("Invalid email or password");
        }
        return new CustomAuthentication(userAccount);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
