package cache.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Class Name : RootConfig<BR>
 * Descripe : TODO(这里用一句话描述这个类的作用)<BR>
 * Create by : zhaoxl<BR>
 * DATE: 2016/12/910:01<BR>
 * Version: V1.0<BR>
 * <p/>
 * copyright 轻重府.
 */
@Configuration
@ComponentScan("cache.data")
@Import({DataConfig.class,CachingConfig.class})
public class RootConfig {
}
