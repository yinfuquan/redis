package com.example.first.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.ResourceServlet;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.example.first.dal.bettlsql.SqlKitHolder;
import org.beetl.core.resource.WebAppResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.DefaultNameConversion;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    @Bean
    public DataSource dataSource(){
        return new DruidDataSource();
    }
    @Bean
    public ServletRegistrationBean statView(){
        ServletRegistrationBean bean=new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map<String,String> map=new HashMap<>();
//        ResourceServlet
        map.put("loginUsername","admin");
        map.put("loginPassword","123456");
        map.put("resetEnable", "false");
        bean.setInitParameters(map);
        return  bean;
    }
    @Bean
    public FilterRegistrationBean filter(){
        FilterRegistrationBean bean=new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> map=new HashMap<>();
        map.put("exclusions","*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        bean.setInitParameters(map);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return  bean;
    }
//
//    @Bean
//    public BeetlSqlCustomize beetlSqlCustomize(){
//        return  new BeetlSqlCustomize(){
//            @Override
//            public void customize(SqlManagerFactoryBean sqlManagerFactoryBean){
//                if (sqlManagerFactoryBean.getFunctions().isEmpty()) {
//                    sqlManagerFactoryBean.setFunctions(new HashMap<String, Function>());
//                }
//                sqlManagerFactoryBean.getFunctions().put("isEmpty", new org.beetl.ext.fn.EmptyExpressionFunction());
//                sqlManagerFactoryBean.getFunctions().put("isNotEmpty", new org.beetl.ext.fn.IsNotEmptyExpressionFunction());
//                sqlManagerFactoryBean.getFunctions().put("isBlank", new org.beetl.ext.fn.EmptyExpressionFunction());
//                sqlManagerFactoryBean.getFunctions().put("isNotBlank", new org.beetl.ext.fn.IsNotEmptyExpressionFunction());
//            }
//        };
//    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        PlatformTransactionManager txManager = new DataSourceTransactionManager(dataSource);
        return txManager;
    }
    @Bean(name = "sqlManagerFactoryBean")
    @Primary
    public SqlManagerFactoryBean getSqlManagerFactoryBean() {
        SqlManagerFactoryBean factory = new SqlManagerFactoryBean();
        BeetlSqlDataSource source = new BeetlSqlDataSource();
        source.setMasterSource(dataSource());
        factory.setCs(source);
        factory.setDbStyle(new MySqlStyle());
        factory.setNc(new DefaultNameConversion());
        factory.setSqlLoader(new ClasspathLoader("/sql"));
        //sql文件路径
        return factory;
    }
    @Bean(initMethod = "init", name = "beetlConfig")
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        ResourcePatternResolver patternResolver = ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());
        // WebAppResourceLoader 配置root路径是关键
        //WebAppResourceLoader webAppResourceLoader = new WebAppResourceLoader(patternResolver.getResource("classpath:/sql").getFile().getPath());
        WebAppResourceLoader webAppResourceLoader = new WebAppResourceLoader();
        beetlGroupUtilConfiguration.setResourceLoader(webAppResourceLoader);

        //读取配置文件信息
        return beetlGroupUtilConfiguration;
    }

    @Bean(name = "beetlViewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setOrder(0);
        beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
        return beetlSpringViewResolver;
    }

    @Bean
    public SqlKitHolder sqlKitHolder() {
        return new SqlKitHolder();
    }
}
