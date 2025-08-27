package io.github.cursodatajava.libraryapi.config;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.beans.factory.annotation.Value;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driver;

    //@Bean
    /*
    * FORMA SIMPLES DE CONEXAO
    * */
    public DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driver);
        return ds;
    }
    /*
    * CONFIGURACAO HIKARY
    *
    * */
    // HIKARI FORMA CORRETA
    @Bean
    public DataSource hikariDataSource(){
        HikariConfig config = new HikariConfig();
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);
        config.setPoolName("library-db-pool");
        config.setMaxLifetime(600000); //tempo maximo de conexao 600 mil ms
        config.setConnectionTimeout(100000); // o tempo que ele ira ficar tentando conexao, caso nao consiga ate este tempo podemos lancar erro
        config.setConnectionTestQuery("select 1"); // query de teste, testar se esta se conectando com o banco

        config.setMaximumPoolSize(10); //maximo de conexoes liberadas
        config.setMinimumIdle(1); // minimo de conexoes liberadas, tamanho inicial do pool
        return new HikariDataSource(config);
    }
}
