package dev.patika.errorlogpersister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
@EnableMongoRepositories(basePackages = {"dev.patika.errorlogpersister.repository"})
public class ErrorLogPersisterApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(ErrorLogPersisterApplication.class, args);
    }

}
