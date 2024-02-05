package uk.oakacademy.taskservice.services;

import org.springframework.stereotype.Service;
import uk.oakacademy.taskservice.dto.TaskDetailDTO;

import java.util.List;


public interface TaskDetailService {

    public TaskDetailDTO save(TaskDetailDTO taskDetailDTO);
    public TaskDetailDTO getTaskDetailbyId(String Id);
    public List<TaskDetailDTO> getAllTaskDetails();
    public TaskDetailDTO updateTaskDetail(TaskDetailDTO taskDetailDTO);
    public TaskDetailDTO deleteTaskDetail(String Id);
    public List<TaskDetailDTO> getWithContainDescription(String description);
    public List<TaskDetailDTO> getWithStartsName(String text);

}
