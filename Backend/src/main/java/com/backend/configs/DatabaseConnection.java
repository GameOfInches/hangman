/**

 * File: DatabaseConnection.java

 * Author: Krasimir Konstantinov

 * Date: 28/11/2023

 */
package com.backend.configs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;

@Component
public class DatabaseConnection implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        try {
            dataSource.getConnection();
            System.out.println("Database connection established successfully.");
        } catch (Exception e) {
            System.err.println("Error: Failed to establish a database connection.");
        }
    }
}
