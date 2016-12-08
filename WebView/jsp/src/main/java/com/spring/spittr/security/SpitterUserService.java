package com.spring.spittr.security;

import com.spring.spittr.Spitter;
import com.spring.spittr.data.SpitterRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Name : SpitterUserService<BR>
 * Descripe : TODO(这里用一句话描述这个类的作用)<BR>
 * Create by : zhaoxl<BR>
 * DATE: 2016/12/210:50<BR>
 * Version: V1.0<BR>
 * <p/>
 * copyright 轻重府.
 */

public class SpitterUserService implements UserDetailsService {

    private SpitterRepository spitterRepository;

    public SpitterUserService(SpitterRepository spitterRepository) {
        this.spitterRepository = spitterRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Spitter spitter = spitterRepository.findByUsername(username);
        if (null != spitter) {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            // 创建权限列表
            authorities.add(new SimpleGrantedAuthority("ROLE_SPITTER"));
            // 返回User
            return new User(spitter.getUsername(),spitter.getPassword(),authorities);
        }

        throw new UsernameNotFoundException("User '" + username + "' not found.");
    }
}
