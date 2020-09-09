package com.cxl.security.service;

import com.cxl.security.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author cxl
 * @description 用户验证
 * @date 2020/09/09 0009
 */
@Slf4j
@Component
public class MyUserDetailService implements UserDetailsService {

    private String username = "zhangsan";
    private String password = "123456";

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        log.info("当前登录用户{}："+s);


        UserModel build = UserModel.builder().id(1L).username(s).password("123").build();
        if(StringUtils.isBlank(s)){
            throw new UsernameNotFoundException("该用户不存在");
        }else if (!build.isEnabled()) {
            throw new DisabledException("用户已被禁用");
        } else if (!build.isAccountNonExpired()) {
            throw new AccountExpiredException("账号已过期");
        } else if (!build.isAccountNonLocked()) {
            throw new LockedException("账号已被锁定");
        } else if (!build.isCredentialsNonExpired()) {
            throw new LockedException("凭证已过期");
        }
        //加密密码
        String password = passwordEncoder.encode(build.getPassword());
        log.info("该用户的密码为{}："+password);



        return build;
    }
}
