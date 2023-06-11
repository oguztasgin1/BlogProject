package com.oguztasgin.security;

import com.oguztasgin.repository.entity.User;
import com.oguztasgin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtUserDetail implements UserDetailsService {
    @Autowired
    UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails loadUserById(long id){
        Optional<User> user=userService.findById(id);

        if (user.isPresent()){

            List<GrantedAuthority> authorityList=new ArrayList<>();
            authorityList.add(new SimpleGrantedAuthority(user.get().getRole().toString()));

            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.get().getUsername())
                    .password("")
                    .accountExpired(false)
                    .accountLocked(false)
                    .authorities(authorityList)
                    .build();
        }
        return  null;
    }
}
