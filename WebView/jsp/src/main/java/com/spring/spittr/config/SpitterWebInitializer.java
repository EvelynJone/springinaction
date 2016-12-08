package com.spring.spittr.config;

import com.spring.spittr.web.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by zhaoxl on 2016/11/28.
 */
public class SpitterWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 配置ContextLoaderListener应用上下文的配置类
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{ RootConfig.class};
    }

    /**
     *配置DispatcherServlet应用上下文的配置类
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{ WebConfig.class };
    }

    /**
     * 将DispatcherServlet映射到“/”
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{ "/" };
    }
}
