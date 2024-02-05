package uk.oakacademy.taskservice.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import uk.oakacademy.taskservice.entities.TaskDetail;

import java.util.List;

public interface TaskDetailRepository extends ElasticsearchRepository<TaskDetail,String> {
    public List<TaskDetail> findByTaskDescriptionContains(String description);
    public List<TaskDetail> findTaskDetailByEmployeeNameStartingWith(String text);
//    public List<TaskDetail> findByTaskTitleContaining(String description);
}
