package dev.patika.errorlogpersister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController
{

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping
    public boolean test(@RequestParam String message)
    {
        kafkaTemplate.send("error_logs", message);
        return true;
    }

}
