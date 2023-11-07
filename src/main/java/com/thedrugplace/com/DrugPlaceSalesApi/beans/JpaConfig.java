//package com.thedrugplace.com.DrugPlaceSalesApi.beans;
//
//import jakarta.persistence.EntityManagerFactory;
//import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//
//import javax.activation.DataSource;
//import java.beans.PropertyVetoException;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class JpaConfig {
//    private static String prop_db_driver_class = "spring.db.driver";
//    private static String prop_db_url = "spring.datasource.url";
//    private static String prop_db_user = "spring.datasource.username";
//    private static String prop_db_pass = "spring.datasource.password";
//
//    @Autowired
//    private Environment env;
//
//    @Bean(name="entityManagerFactoryDef")
//    @Primary
//    public LocalContainerEntityManagerFactoryBean defaultEntityManager() {
//        Map map = new HashMap();
//        map.put("hibernate.default_schema", env.getProperty("spring.datasource.username"));
//        map.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
//        LocalContainerEntityManagerFactoryBean em = createEntityManagerFactoryBuilder(jpaVendorProperties())
//                .dataSource(primaryDataSource()).persistenceUnit("default").properties(map).build();
//        em.setPackagesToScan("com.simple.entity");
//        em.afterPropertiesSet();
//        return em;
//    }
//}