package manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Configuration
//@ComponentScan(basePackages = {"manager.infrastructure.dao"})
public class Ipv6managerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ipv6managerApplication.class, args);
    }

}
