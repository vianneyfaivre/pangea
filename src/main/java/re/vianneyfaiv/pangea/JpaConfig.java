package re.vianneyfaiv.pangea;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
public class JpaConfig {

	@Bean(name = "mainDataSource")
	public DataSource createMainDataSource() {
		JdbcDataSource ds = new JdbcDataSource();
		ds.setURL("jdbc:h2:file:P:/db/customerDB;AUTO_SERVER=TRUE");

		return ds;
	}
}
