
package com.algerd.musicbookspringmaven.spring.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("com.algerd.musicbookspringmaven")
@PropertySource({"classpath:properties/dbconnect.properties"})
public class AppConfig {
    
    @Autowired
    Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("datasource.url"));
        dataSource.setUsername(env.getProperty("datasource.user"));
        dataSource.setPassword(env.getProperty("datasource.password"));
        try {
            String propertiesUrl = "properties/dbconfig.properties";
            InputStream input = this.getClass().getClassLoader().getResourceAsStream(propertiesUrl);
            if (input == null) {
                throw new IOException("Sorry, unable to find " + propertiesUrl);
            }        
            Properties properties = new Properties();
            properties.load(input);
            dataSource.setConnectionProperties(properties);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return dataSource;
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

}
