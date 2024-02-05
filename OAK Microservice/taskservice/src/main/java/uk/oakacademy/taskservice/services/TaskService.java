package uk.oakacademy.taskservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import uk.oakacademy.taskservice.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    public TaskDTO saveTask( TaskDTO taskDTO) throws JsonProcessingException;
    public TaskDTO updateTask( String Id,TaskDTO taskDTO);
    public TaskDTO getById(String Id);
    public TaskDTO deleteById(String Id);
    public List<TaskDTO> getAllTasks();
    public List<TaskDTO> getTaskPagination(int pagesize,int pageno);
}
