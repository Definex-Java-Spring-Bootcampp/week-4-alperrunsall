package dev.patika.errorlogpersister.repository;

import dev.patika.errorlogpersister.model.ErrorMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ErrorMessageRepository extends MongoRepository<ErrorMessage, Integer>
{
}
