package com.patika.kredinbizdeservice.converter;

import com.patika.kredinbizdeservice.dto.request.ApplicationRequest;
import com.patika.kredinbizdeservice.enums.ApplicationStatus;
import com.patika.kredinbizdeservice.model.Application;
import com.patika.kredinbizdeservice.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ApplicationConverterTest {
    @InjectMocks
    ApplicationConverter applicationConverter;
    @Test
    public void testToApplication() {
        ApplicationRequest request = prepareRequest();
        User user = prepareUser();

        Application application = applicationConverter.toApplication(request, user);

        assertNotNull(application);

        assertEquals(user, application.getUser());

        assertEquals(ApplicationStatus.INITIAL, application.getApplicationStatus());

        assertEquals(LocalDate.now(), application.getCreatedDate());
        assertEquals(LocalDate.now(), application.getUpdatedDate());
    }


    private ApplicationRequest prepareRequest(){
        ApplicationRequest applicationRequest = new ApplicationRequest();

        applicationRequest.setEmail(prepareUser().getEmail());

        return applicationRequest;
    }
    private User prepareUser(){
        User user = new User();
        user.setName("test");
        user.setSurname("test");
        user.setEmail("test@test.com");
        user.setPassword("password");
        user.setIsActive(true);

        return user;
    }

}
