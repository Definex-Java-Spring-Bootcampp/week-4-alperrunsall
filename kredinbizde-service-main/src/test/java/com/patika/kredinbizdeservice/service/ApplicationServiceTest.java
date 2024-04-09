package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.client.dto.request.AkbankApplicationRequest;
import com.patika.kredinbizdeservice.converter.ApplicationConverter;
import com.patika.kredinbizdeservice.dto.request.ApplicationRequest;
import com.patika.kredinbizdeservice.enums.ApplicationStatus;
import com.patika.kredinbizdeservice.model.Application;
import com.patika.kredinbizdeservice.model.User;
import com.patika.kredinbizdeservice.repository.ApplicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceTest {

    @InjectMocks
    private ApplicationService applicationService;

    @Mock
    private ApplicationRepository applicationRepository;
    @Mock
    private UserService userService;
    @Mock
    private ApplicationConverter applicationConverter;

    @Test
    public void should_application_create_successfully(){

        // Arrange
        ApplicationRequest request = prepareRequest();

        User user = prepareUser();

        when(userService.getByEmail(request.getEmail())).thenReturn(user);

        Application application = prepareApplication();

        when(applicationConverter.toApplication(request, user)).thenReturn(application);
        when(applicationRepository.save(any(Application.class))).thenReturn(application);

        // Act
        Application createdApplication = applicationService.createApplication(request);

        // Assert
        assertNotNull(createdApplication); // Dönüş değeri null olmamalı
        assertEquals(application, createdApplication); // Dönüş değeri beklenen application nesnesi olmalı
        verify(applicationRepository, times(1)).save(application);
    }

    @Test
    void prepareAkbankApplicationRequest_ValidUser_CorrectUserIdSet() {
        AkbankApplicationRequest akbankRequest = applicationService.prepareAkbankApplicationRequest(prepareUser());

        assertEquals(1L, akbankRequest.getUserId());
    }

    private ApplicationRequest prepareRequest(){
        ApplicationRequest applicationRequest = new ApplicationRequest();

        applicationRequest.setEmail(prepareUser().getEmail());

        return applicationRequest;
    }

    private Application prepareApplication(){
        Application application = new Application();
        application.setUser(prepareUser());
        application.setId(1L);
        application.setCreatedDate(LocalDate.now());
        application.setUpdatedDate(LocalDate.now());
        application.setApplicationStatus(ApplicationStatus.IN_PROGRESS);

        return application;
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
