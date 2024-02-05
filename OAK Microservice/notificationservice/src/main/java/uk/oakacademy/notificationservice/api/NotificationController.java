package uk.oakacademy.notificationservice.api;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.oakacademy.notificationservice.dto.NotificationDTO;
import uk.oakacademy.notificationservice.services.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTO> getById(@PathVariable long id)
    {
        return ResponseEntity.ok(notificationService.getById(id));
    }
    @PostMapping
    public ResponseEntity<NotificationDTO> createNotification(@RequestBody NotificationDTO notificationDTO)
    {
        return new ResponseEntity<NotificationDTO>(notificationService.save(notificationDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationDTO> updateNotification(@PathVariable long id,@RequestBody NotificationDTO notificationDTO)
    {
        return ResponseEntity.ok(notificationService.update(id,notificationDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<NotificationDTO> deleteNotification(@PathVariable long id)
    {
        return ResponseEntity.ok(notificationService.delete(id));
    }

    @GetMapping
    public ResponseEntity<List<NotificationDTO>> getAllNotifications()
    {
        return ResponseEntity.ok(notificationService.getAll());
    }










}
