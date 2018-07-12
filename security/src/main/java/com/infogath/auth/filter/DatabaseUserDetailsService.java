package com.infogath.auth.filter;

import com.infogath.auth.model.AuthPermission;
import com.infogath.auth.model.AuthUser;
import com.infogath.auth.service.AuthPermissionService;
import com.infogath.auth.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leo
 * <p>
 * 从数据库获取用户信息和权限信息
 */

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

    @Autowired
    AuthUserService authUserService;
    @Autowired
    AuthPermissionService authPermissionService;

    public UserDetails loadUserByUsername(String username) {
        AuthUser user = authUserService.findByUserName(username);
        if (user != null) {
            List<AuthPermission> permissions = authPermissionService.findByAdminUserId(user.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (AuthPermission permission : permissions) {
                if (permission != null && permission.getName() != null) {

                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                    //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
    }

}
