package com.grupo2.PMSEYJ.core.database;

import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {

    private static final Properties properties = new Properties();

    static {
        try (InputStream is = DatabaseConfig.class
                .getClassLoader()
                .getResourceAsStream("application.properties")) {

            if (is == null) {
                throw new RuntimeException("No se encontró application.properties");
            }
            properties.load(is);

        } catch (Exception e) {
            throw new RuntimeException("Error cargando configuración", e);
        }
    }

    public static String getUrl() {
        return properties.getProperty("db.url");
    }

    public static String getUser() {
        return properties.getProperty("db.user");
    }

    public static String getPassword() {
        return properties.getProperty("db.password");
    }
}
