package com.example.inverntory_management_system.security;

import com.example.inverntory_management_system.models.Role;
import com.example.inverntory_management_system.models.UserEntity;
import com.example.inverntory_management_system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAuthProvider implements AuthenticationProvider {

  @Autowired
  private UserService userService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String email = authentication.getName();
    String password = authentication.getCredentials().toString();
    UserEntity user = userService.findByEmail(email);
    if(user != null && user.getPassword().equals(password)){
      return new UsernamePasswordAuthenticationToken(user.getUsername(),password, getGrantedAuthorities(user.getRoles().get(0)));
    }

    return null;
  }

  private List<GrantedAuthority> getGrantedAuthorities(Role role){
    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));
    return grantedAuthorities;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
