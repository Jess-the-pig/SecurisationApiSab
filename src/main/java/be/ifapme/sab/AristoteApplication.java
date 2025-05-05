package be.ifapme.sab;

import be.ifapme.sab.config.security.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class AristoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(AristoteApplication.class, args);
	}

}
