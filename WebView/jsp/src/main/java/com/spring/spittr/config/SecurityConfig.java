package com.spring.spittr.config;

import com.spring.spittr.data.SpitterRepository;
import com.spring.spittr.security.SpitterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;

/**
 * Class Name : SecurityConfig<BR>
 * Descripe :  为Spring mvc启用Web安全性功能的最简单配置<BR>
 * Create by : zhaoxl<BR>
 * DATE: 2016/12/29:47<BR>
 * Version: V1.0<BR>
 * <p/>
 * EnableWebMvcSecurity： 启用web mvc的安全性配置
 * copyright 轻重府.
 */
@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    /**
     *  内存用户存储
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER").and()
                .withUser("admin").password("password").roles("USER","ADMIN");
    }

    @Autowired
    DataSource dataSource;

    /**
     * 基于数据库进行认证：重新默认的用户查询功能
     * usersByUsernameQuery: 认证查询
     * authoritiesByUsernameQuery ： 基本权限查询
     * @param auth
     * @throws Exception
     */
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
            .usersByUsernameQuery(
                    "select username,password,true from Spitter where username=?"
            )
        .authoritiesByUsernameQuery(
                "select username,'ROLE_USER' from Spitter where username=?"
        ).passwordEncoder(new StandardPasswordEncoder("53cr3t"));
    }*/

    /**
     * 基于LDAP进行认证
     * passwordCompare : 通过密码对比进行认证
     * passwordEncoder ：指定加密策略
     * passwordAttribute ： 密码被保存在不同的属性中，可通过passwordAttribute来声明密码属性
     * contextSource.url : 引用远程ldap
     * contextSource.root : 嵌入式的LDAP服务器
     * contextSource.ldif : LDIF文件用来加载数据
     * @param auth
     * @throws Exception
     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.ldapAuthentication()
//                .userSearchBase("ou=people")
//                .userSearchFilter("(uid={0})")
//                .groupSearchBase("ou=groups")
//                .groupSearchFilter("(member={0})")
////        .passwordCompare()
////        .passwordEncoder(new Md5PasswordEncoder())
////        .passwordAttribute("passcode")
//                .contextSource()
//                .root("dc=habuma,dc=com")
//                .ldif("classpath:users.ldif.ldif")
//                .url("ldap://habuma.com:389/dc=habuma,dc=com")
//        ;
//    }


    /**
     * 用户自定义
     */
//    @Autowired
//    private SpitterRepository spitterRepository;
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(new SpitterUserService(spitterRepository));
//    }

    /**
     * 拦截请求
     * antMatchers后可以进行不同的过滤处理
     *  authenticated： 允许认证过的用户访问
     *  anonymous：允许匿名用户访问
     *  。。。
     *
     * .requiresChannel()
        .antMatchers("/spitter/form").requiresSecure(); // 需要HTTPS
        不论何时，只要是对‘/spitter/form’的请求，Spring Security都会视为需要安全通道并自动将请求重定向到HTTPS上
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .logoutUrl("/signout")
                .and()
                .rememberMe()
                .tokenValiditySeconds(2419200)
                .key("spittrKey")
                .and()
                .authorizeRequests()
                .antMatchers("/spitters/me").hasRole("SPITTER")    // "/spitters/me" 需要认证
//                .antMatchers(HttpMethod.POST,"/spittles").authenticated()   //"/spittles" 中HttpMethod为POST的 需要认证
                .antMatchers(HttpMethod.POST,"/spittles").hasRole("SPITTER")   //"/spittles" 中HttpMethod为POST的 只有角色为SPITTER的用户才允许访问
                .anyRequest().permitAll()   // 其余的都不需要认证
        .and()
        .requiresChannel()
        .antMatchers("/spitter/form").requiresSecure(); // 需要HTTPS
    }
}
