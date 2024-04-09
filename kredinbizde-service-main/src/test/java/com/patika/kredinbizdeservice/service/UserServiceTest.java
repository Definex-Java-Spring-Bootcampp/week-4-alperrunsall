package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.exceptions.KredinbizdeException;
import com.patika.kredinbizdeservice.model.User;
import com.patika.kredinbizdeservice.producer.NotificationProducer;
import com.patika.kredinbizdeservice.producer.dto.NotificationDTO;
import com.patika.kredinbizdeservice.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private NotificationProducer notificationProducer;

    @Test
    public void should_create_user_successfully(){

        //given
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(prepareUser());

        //when
        User userResponse = userService.save(prepareUser());

        //then

        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getName()).isEqualTo("test");
        assertThat(userResponse.getSurname()).isEqualTo(prepareUser().getSurname());
        assertThat(userResponse.getEmail()).isEqualTo(prepareUser().getEmail());
        assertThat(userResponse.getPassword()).isEqualTo(prepareUser().getPassword());
        assertThat(userResponse.getIsActive()).isTrue();

        verify(userRepository,times(1)).save(Mockito.any(User.class));
        verify(notificationProducer,times(1)).sendNotification(Mockito.any(NotificationDTO.class));
    }

    @Test
    public void should_return_user_by_email_successfully(){
        //given
        Mockito.when(userRepository.findByEmail("test@gmail.com")).thenReturn(Optional.of(prepareUser()));

        //when
        User userResponse = userService.getByEmail("test@gmail.com");

        //then
        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getName()).isEqualTo("test");
        assertThat(userResponse.getSurname()).isEqualTo(prepareUser().getSurname());
        assertThat(userResponse.getEmail()).isEqualTo(prepareUser().getEmail());
        assertThat(userResponse.getPassword()).isEqualTo(prepareUser().getPassword());
        assertThat(userResponse.getIsActive()).isTrue();

        verify(userRepository,times(1)).findByEmail("test@gmail.com");
    }

    @Test
    public void should_throw_kredinbizdeException_whenUserNotFound() {
        //given

        //when
        Throwable throwable = catchThrowable(() -> userService.getByEmail("test@gmail.com"));

        //then
        assertThat(throwable).isInstanceOf(KredinbizdeException.class);
        assertThat(throwable.getMessage()).isEqualTo("user bulunamadı!");
    }

    @Test
    public void should_throw_kredinbizdeException_whenUserNotFound2() {
        //given

        //when
        Assertions.assertThrows(KredinbizdeException.class, () -> userService.getByEmail("test"), "user bulunamadı!");

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
