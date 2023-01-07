package manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;


@Slf4j
@EnableMongoAuditing//自动注入时间
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,  JpaRepositoriesAutoConfiguration.class })
public class Ipv6managerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ipv6managerApplication.class, args);
    }

}
