package com.example.shoppingserver;
import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.example.shoppingserver"})
public class ShoppingServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingServerApplication.class, args);
    }

/*    @Bean
    Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }*/

    @Bean
    Hibernate5JakartaModule hibernate5Module() {
        return new Hibernate5JakartaModule();
    }

}
