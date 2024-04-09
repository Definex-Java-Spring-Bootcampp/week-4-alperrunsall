package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.client.AkbankServiceClient;
import com.patika.kredinbizdeservice.client.GarantiServiceClient;
import com.patika.kredinbizdeservice.client.dto.request.AkbankApplicationRequest;
import com.patika.kredinbizdeservice.client.dto.request.GarantiApplicationRequest;
import com.patika.kredinbizdeservice.converter.ApplicationConverter;
import com.patika.kredinbizdeservice.dto.request.ApplicationRequest;
import com.patika.kredinbizdeservice.model.Application;
import com.patika.kredinbizdeservice.model.User;
import com.patika.kredinbizdeservice.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationConverter applicationConverter;
    private final UserService userService;
    private final AkbankServiceClient akbankServiceClient;
    private final GarantiServiceClient garantiServiceClient;

    public Application createApplication(ApplicationRequest request) {

        User user = userService.getByEmail(request.getEmail());

        Application application = applicationConverter.toApplication(request, user);

        Application savedApplication = applicationRepository.save(application);

        switch (request.getBank()){
            case AKBANK -> {
                akbankServiceClient.createApplication(prepareAkbankApplicationRequest(user));
            }
            case GARANTI -> {
                garantiServiceClient.createApplication(prepareGarantiApplicationRequest(user));
            }
        }

        // TODO akbankApplicationResponse response kontrol edilmeli.

        return savedApplication;
    }



    public AkbankApplicationRequest prepareAkbankApplicationRequest(User user) {
        AkbankApplicationRequest applicationRequest = new AkbankApplicationRequest();

        applicationRequest.setUserId(1L);

        return applicationRequest;
    }
    private GarantiApplicationRequest prepareGarantiApplicationRequest(User user) {
        GarantiApplicationRequest applicationRequest = new GarantiApplicationRequest();

        applicationRequest.setUserId(1L);

        return applicationRequest;
    }
}
