package com.joaopem.embrace_family.security;

import com.joaopem.embrace_family.model.UserAccount;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public class CustomAuthentication implements Authentication {

    private final UserAccount userAccount;

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Stream.of(userAccount.getUserRole()).map(userRole -> new SimpleGrantedAuthority(userRole.name()))
                .collect(Collectors.toList());
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return userAccount;
    }

    @Override
    public Object getPrincipal() {
        return userAccount;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return userAccount.getEmail();
    }
}
