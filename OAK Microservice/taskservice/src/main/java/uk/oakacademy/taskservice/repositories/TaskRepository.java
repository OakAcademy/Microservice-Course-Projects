package uk.oakacademy.taskservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.oakacademy.taskservice.entities.Task;
//@Repository
public interface TaskRepository extends JpaRepository<Task,String> {
}
