package com.oauth.oauth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * Created by german on 27/11/14.
 */
@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        System.out.println("------- name: " + authentication.getName());
        System.out.println("------- pass: " + authentication.getCredentials().toString());

        return new UsernamePasswordAuthenticationToken(
                authentication.getName(),
                authentication.getCredentials(),
                Arrays.asList(new GrantedAuthority[] {new SimpleGrantedAuthority("ROLE_CLIENT")}));
    }

}
