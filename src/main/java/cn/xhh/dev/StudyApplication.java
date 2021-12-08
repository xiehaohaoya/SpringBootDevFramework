package cn.xhh.dev;

import cn.xhh.dev.servlet.listener.MyListener;
import cn.xhh.dev.servlet.MyServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * 1.@ServletComponentScan：启动对自定义servlet的扫描
 * 2.添加(exclude = {DataSourceAutoConfiguration.class})，解决在配置Druid多数据源时，出现循环依赖的问题
 * @author Administrator
 */
@ServletComponentScan
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class StudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudyApplication.class, args);
    }

    /**
     * 将自定义的servlet添加到spring boot容器中，当配置了urlMappings后，之前在servlet中配置的urlPatterns就不生效了
     * @return bean
     */
    @Bean
    public ServletRegistrationBean<MyServlet> getServletRegistrationBean() {
        ServletRegistrationBean<MyServlet> bean = new ServletRegistrationBean<>(new MyServlet(), "/s2");
        bean.setLoadOnStartup(1);
        return bean;
    }

    @Bean
    public ServletListenerRegistrationBean<MyListener> getServletListenerRegistrationBean() {
        ServletListenerRegistrationBean<MyListener> bean = new ServletListenerRegistrationBean<>();
        bean.setListener(new MyListener());
        System.out.println("listener");
        return bean;
    }

}
