package uk.oakacademy.notificationservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.oakacademy.notificationservice.entities.Notification;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
