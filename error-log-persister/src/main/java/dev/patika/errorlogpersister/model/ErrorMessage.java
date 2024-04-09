package dev.patika.errorlogpersister.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Document("ErrorMessages")
public class ErrorMessage
{

    @Id
    @Indexed(unique = true)
    private String id;

    @Indexed
    private LocalDateTime dateCreated;

    private String message;

}
