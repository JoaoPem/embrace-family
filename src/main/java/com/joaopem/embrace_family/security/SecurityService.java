package com.joaopem.embrace_family.security;

import com.joaopem.embrace_family.model.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityService {

    public UserAccount getLoggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof CustomAuthentication customAuthentication){
            return  customAuthentication.getUserAccount();
        }

        return null;
    }
}
