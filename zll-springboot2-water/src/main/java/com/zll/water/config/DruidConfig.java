package com.zll.water.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 被@Configuration注解修饰的类可以替换xml配置文件
 * @Configuration用于构建Spring，启动Spring容器
 */
@Configuration
public class DruidConfig {

    /**
     * Druid数据源注入到Spring容器
     * @ConfigurationProperties(prefix = "spring.datasource")表示获取yml配置文件中前缀为spring.datasource的所有属性值
     * 被@Bean注解的方法被AnnotationConfigWebApplicationContext类扫描，将方法返回的对象注入到Spring容器
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource createDruidDatasource() {
        return new DruidDataSource();
    }

    /**
     * 向Spring容器注入Druid监控。通过StatViewServlet通过ServletRegistrationBean注入到Spring容器。
     * StatViewServlet用于Druid监控，展示的统计信息
     * @return
     */
    @Bean
    public ServletRegistrationBean ServletRegistrationBean() {
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean =
                new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        // 设置初始化参数，登录的key都是固定的
        Map<String,String> initParamsMap =  new ConcurrentHashMap<>();
        initParamsMap.put("loginUsername","admin");
        initParamsMap.put("loginPassword","123456");
        // 白名单，允许所有ip地址登录
        initParamsMap.put("allow","");
        servletRegistrationBean.setInitParameters(initParamsMap);

        return servletRegistrationBean;
    }

    /**
     * 过滤不需要拦截的资源
     * @return
     */
    @Bean
    public FilterRegistrationBean createFilterRegistrationBean() {
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean =
                new FilterRegistrationBean<>(new WebStatFilter());

        filterRegistrationBean.addUrlPatterns("/**");
        filterRegistrationBean.addInitParameter("exclusions", "*.js, *css, *.png, /druid/*");
        return filterRegistrationBean;
    }
}
