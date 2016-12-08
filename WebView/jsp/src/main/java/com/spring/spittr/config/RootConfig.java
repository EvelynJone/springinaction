package com.spring.spittr.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by zhaoxl on 2016/11/28.
 */
@Configuration
@Import(DataConfig.class)
@ComponentScan(value = "com.spring.spittr",
        excludeFilters = {@ComponentScan.Filter ( type = FilterType.ANNOTATION ,value = EnableWebMvc.class)})
public class RootConfig {
}
