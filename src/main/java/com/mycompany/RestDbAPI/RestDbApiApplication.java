package com.mycompany.RestDbAPI;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
    DataSourceAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class})
@EnableTransactionManagement
public class RestDbApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestDbApiApplication.class, args);
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(H2)
                .addScript("create.sql")
                //.addScript("update.sql")
                .build();
    }//end dataSource

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager htm = new HibernateTransactionManager();

        htm.setSessionFactory(sessionFactory().getObject());

        return htm;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean lsb = new LocalSessionFactoryBean();

        lsb.setDataSource(dataSource());
        lsb.setHibernateProperties(hibernateProperties());
        lsb.setPackagesToScan("com.mycompany.RestDbAPI.model");

        return lsb;
    }

    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();

        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.setProperty("hibernate.max fetch depth", "3");
        properties.setProperty("hibernate. jdЬc. fetch _ size", "50");
        properties.setProperty("hibernate.jdЬc.batch_size", "10");
        properties.setProperty("hibernate.show_sql", "true");

        return properties;
    }
}
