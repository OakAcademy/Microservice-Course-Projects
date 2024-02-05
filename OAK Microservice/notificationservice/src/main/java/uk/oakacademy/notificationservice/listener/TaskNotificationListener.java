package uk.oakacademy.notificationservice.listener;

import ch.qos.logback.core.joran.action.NOPAction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import uk.oakacademy.notificationservice.dto.TaskNotificationDTO;
import uk.oakacademy.notificationservice.entities.Notification;
import uk.oakacademy.notificationservice.repositories.NotificationRepository;

@Service
@RequiredArgsConstructor
public class TaskNotificationListener {

    private final NotificationRepository notificationRepository;
    @RabbitListener(queues = "oakqueue")
    public void handleMessage(String message) throws JsonProcessingException {
        TaskNotificationDTO taskNotificationDTO=new ObjectMapper().readValue(message,TaskNotificationDTO.class);
        Notification notification=new Notification();
        notification.setTaskDescription(taskNotificationDTO.getTaskId());
        notification.setTaskTitle(taskNotificationDTO.getTaskTitle());
        notification.setEmployeeId(taskNotificationDTO.getEmployeeId());
        notification.setTaskId(taskNotificationDTO.getTaskId());
        notificationRepository.save(notification);
        System.out.println("Message received from Task Service");
        System.out.println(taskNotificationDTO.toString());

    }

}
