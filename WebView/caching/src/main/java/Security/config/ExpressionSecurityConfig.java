package Security.config;

import Security.SpittlePermissionEvaluator;
import Security.service.ExpressionSecuredSpittleService;
import Security.service.SpittleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class ExpressionSecurityConfig extends GlobalMethodSecurityConfiguration {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
    .inMemoryAuthentication()
      .withUser("user").password("password").roles("USER");
  }
  
  @Bean
  public SpittleService spitterService() {
    return new ExpressionSecuredSpittleService();
  }

  /**
   * 启动许可计算器的使用
   * @return
     */
  @Override
  protected MethodSecurityExpressionHandler createExpressionHandler() {
    DefaultMethodSecurityExpressionHandler handler = new DefaultMethodSecurityExpressionHandler();
    handler.setPermissionEvaluator(new SpittlePermissionEvaluator());
    return handler;
  }
}
