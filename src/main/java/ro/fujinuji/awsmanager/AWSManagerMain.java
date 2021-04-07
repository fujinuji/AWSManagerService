package ro.fujinuji.awsmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("ro.fujinuji.awsmanager.repository")
public class AWSManagerMain {
    public static void main(String[] args) {
        SpringApplication.run(AWSManagerMain.class, args);
    }
}
