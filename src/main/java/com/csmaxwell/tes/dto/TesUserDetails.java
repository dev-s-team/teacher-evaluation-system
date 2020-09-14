package com.csmaxwell.tes.dto;

import com.csmaxwell.tes.domain.TesPermission;
import com.csmaxwell.tes.domain.TesUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SpringSecurity 需要的用户详情
 * Created by maxwell on 2020/9/14.
 */
public class TesUserDetails implements UserDetails {

    private TesUser tesUser;
    private List<TesPermission> permissionList;

    public TesUserDetails(TesUser tesUser, List<TesPermission> permissionList) {
        this.tesUser = tesUser;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 返回当前用户的权限
        return permissionList.stream()
                .filter(permission -> permission.getPermission() != null)
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return tesUser.getPassword();
    }

    @Override
    public String getUsername() {
        return tesUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return tesUser.getStatus().equals(1);
    }
}
