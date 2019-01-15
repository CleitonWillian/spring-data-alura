package br.com.alura.forum;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class RepositoryConfiguration {

	@Bean
	public DataSource dataSourceMySQL(Environment environment) {

		return DataSourceBuilder.create().url(environment.getProperty("mysql.url"))
				.username(environment.getProperty("mysql.user")).password(environment.getProperty("mysql.pass"))
				.build();
	}

}
