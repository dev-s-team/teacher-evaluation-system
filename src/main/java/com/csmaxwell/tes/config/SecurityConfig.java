package com.csmaxwell.tes.config;

import com.csmaxwell.tes.component.JwtAuthenticationTokenFilter;
import com.csmaxwell.tes.component.RestAuthenticationEntryPoint;
import com.csmaxwell.tes.component.RestfulAccessDeniedHandler;
import com.csmaxwell.tes.domain.TesPermission;
import com.csmaxwell.tes.domain.TesUser;
import com.csmaxwell.tes.dto.TesUserDetails;
import com.csmaxwell.tes.service.TesUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

/**
 * SpringSecurity的配置
 * Created by maxwell on 2020/9/14.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TesUserService tesUserService;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    /**
     * 配置需要拦截的url路径、jwt过滤器和异常的处理器
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf() // 使用的是JWT，这里不需要csrf
                .disable()
                .sessionManagement() // 基于token，不需要session
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, // 运行对网站静态资源的无授权访问
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**",
                        "/v2/api-docs/**"
                )
                .permitAll()
                .antMatchers("/user/login", "/user/register") // 对于登录注册允许匿名访问
                .permitAll()
                .antMatchers(HttpMethod.OPTIONS) // 跨域请求会先进行一次options请求
                .permitAll()
                // .antMatchers("/**") // 测试时全部运行访问
                // .permitAll()
                .anyRequest() // 除上面外的所有请求全部需要鉴权认证
                .authenticated();
                // 自定义登录页面
                // .and()
                // .formLogin()
                // .loginPage("/login.html")
                // .loginProcessingUrl("/user/login")
                // .defaultSuccessUrl("/success.html", true)
                // .failureUrl("/failed.html")
                // .and()
                // .logout()
                // .logoutUrl("/user/logout");
        // 禁用缓存
        http.headers().cacheControl();
        // 添加JWT filter
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        // 添加自定义未授权和未登录结果返回
        http.exceptionHandling()
                // 用户没有访问权限时的处理器，用户返回JSON格式的处理结果
                .accessDeniedHandler(restfulAccessDeniedHandler)
                // 当未登录或token失效时，返回JSON格式的结果
                .authenticationEntryPoint(restAuthenticationEntryPoint);
    }

    /**
     * 配置UserDetailsService和PasswordEncoder
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // 获取登录用户信息
        return no -> {
            TesUser user = tesUserService.getUserByNo(no);
            if (user != null) {
                List<TesPermission> permissionList = tesUserService.getPermissionList(user.getId());
                return new TesUserDetails(user, permissionList);
            }
            throw new UsernameNotFoundException("学号或密码错误");
        };
    }

    /**
     * 在用户名和密码校验前添加的过滤器
     * @return
     */
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
