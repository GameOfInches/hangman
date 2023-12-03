/**

 * File: DatabaseConfig.java

 * Author: Krasimir Konstantinov

 * Date: 28/11/2023

 */
package com.backend.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://172.31.0.1:3306/Hangman");
        dataSource.setUsername("HangmanDefault");
        dataSource.setPassword("asdasd");
        return dataSource;
    }
}
