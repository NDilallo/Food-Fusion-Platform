package com.FoodFusion.FoodFusionPlatform.controller.homePage;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.FoodFusion.FoodFusionPlatform.rdbm.homePage.Notification;
import com.FoodFusion.FoodFusionPlatform.services.homePage.NotificationServices;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class NotificationRestControllerTest {
    private static final String NOTIFICATION_URL = "/api/notification";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationServices notificationServices;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        // Setup any initial configurations or mocks
    }

    @Test
    public void getAllNotifications() throws Exception {
        // when - action
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get(NOTIFICATION_URL));

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk());
        // Add more expectations based on your requirements
    }

    @Test
    public void getNotificationById() throws Exception {
        // given - setup or precondition
        Notification notification = new Notification();
        notification.setId(1L);
        notification.setUserId(1L);
        notification.setNotificationType("liked");

        Mockito.when(notificationServices.getById(1L)).thenReturn(Optional.of(notification));

        // when - action
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get(NOTIFICATION_URL + "/1"));

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.notificationType", is("liked")));
    }

    @Test
    public void addNotification() throws Exception {
        // given - setup or precondition
        Notification notification = new Notification();
        notification.setUserId(1L);
        notification.setNotificationType("liked");

        Notification savedNotification = new Notification();
        savedNotification.setId(1L);
        savedNotification.setUserId(1L);
        savedNotification.setNotificationType("liked");

        Mockito.when(notificationServices.save(Mockito.any(Notification.class))).thenReturn(savedNotification);

        String notificationAsJson = objectMapper.writeValueAsString(notification);

        // when - action
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post(NOTIFICATION_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(notificationAsJson));

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", is(1)));
    }

    @Test
    public void updateNotification() throws Exception {
        // given - setup or precondition
        Notification notification = new Notification();
        notification.setUserId(1L);
        notification.setNotificationType("updated");

        Mockito.when(notificationServices.getById(1L)).thenReturn(Optional.of(notification));
        Mockito.when(notificationServices.save(Mockito.any(Notification.class))).thenReturn(notification);

        String notificationAsJson = objectMapper.writeValueAsString(notification);

        // when - action
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put(NOTIFICATION_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(notificationAsJson));

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deleteNotification() throws Exception {
        // given - setup or precondition
        Notification notification = new Notification();
        notification.setId(1L);
        notification.setUserId(1L);
        notification.setNotificationType("liked");

        Mockito.when(notificationServices.getById(1L)).thenReturn(Optional.of(notification));
        Mockito.doNothing().when(notificationServices).delete(1L);

        // when - action
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete(NOTIFICATION_URL + "/1"));

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void addNotificationValidationFail() throws Exception {
        // given - setup or precondition
        Notification notification = new Notification();
        notification.setNotificationType("liked");

        String notificationAsJson = objectMapper.writeValueAsString(notification);

        // when - action
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post(NOTIFICATION_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(notificationAsJson));

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", is("User link must be set")));
    }
}
