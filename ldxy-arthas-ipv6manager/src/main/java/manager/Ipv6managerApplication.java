package manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@Slf4j
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Ipv6managerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ipv6managerApplication.class, args);
        log.info("Ipv6 Manager System Starting……");
    }

}
