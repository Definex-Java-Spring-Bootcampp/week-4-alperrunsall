package dev.patika.errorlogpersister.listener;

import dev.patika.errorlogpersister.model.ErrorMessage;
import dev.patika.errorlogpersister.repository.ErrorMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class ErrorMessageListener
{

    private final ErrorMessageRepository errorMessageRepository;

    @Autowired
    public ErrorMessageListener(ErrorMessageRepository errorMessageRepository)
    {
        this.errorMessageRepository = errorMessageRepository;
    }

    @KafkaListener(topics = {"error_logs"})
    public void receiveErrorMessage(String errorMessage)
    {
        log.info("Received error message: {}", errorMessage);

        final ErrorMessage message = new ErrorMessage();
        message.setMessage(errorMessage);
        message.setDateCreated(LocalDateTime.now());

        errorMessageRepository.save(message);
    }

}
