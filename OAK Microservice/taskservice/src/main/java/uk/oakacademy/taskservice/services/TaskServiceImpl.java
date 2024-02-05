package uk.oakacademy.taskservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import uk.oakacademy.taskservice.dto.EmployeeDTO;
import uk.oakacademy.taskservice.dto.TaskDTO;
import uk.oakacademy.taskservice.dto.TaskDetailDTO;
import uk.oakacademy.taskservice.dto.TaskNotificationDTO;
import uk.oakacademy.taskservice.entities.Task;
import uk.oakacademy.taskservice.producer.TaskNotificationProducer;
import uk.oakacademy.taskservice.repositories.TaskRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
    //    private final RestTemplate restTemplate;
    private final TaskDetailService taskDetailService;
    //private final WebClient webClient;
    private final APIClient apiClient;

    @Override
    public TaskDTO saveTask(TaskDTO taskDTO) throws JsonProcessingException {

        //Rest Template

//        ResponseEntity<EmployeeDTO> employeeDTOResponseEntity=
//                restTemplate.getForEntity("http://localhost:7202/employees/"+taskDTO.getAssignee(),EmployeeDTO.class);
//        EmployeeDTO employeeDTO=employeeDTOResponseEntity.getBody();
//        if(employeeDTOResponseEntity.getStatusCode().is2xxSuccessful()) {
//            taskDTO.setAssignee(employeeDTO.getId());
//            Task task = modelMapper.map(taskDTO, Task.class);
//            taskRepository.save(task);
////        TaskDTO addedtaskDTO=modelMapper.map(task,TaskDTO.class);
//            taskDTO.setId(task.getId());
//
//            TaskDetailDTO taskDetailDTO=new TaskDetailDTO();
//            taskDetailDTO.setEmployeeId(employeeDTO.getId());
//            taskDetailDTO.setEmployeeName(employeeDTO.getName());
//            taskDetailDTO.setEmployeeSurname(employeeDTO.getSurname());
//            taskDetailDTO.setTaskDescription(taskDTO.getTaskDescription());
//            taskDetailDTO.setPriority(taskDTO.getPriorityType());
//            taskDetailDTO.setStatus(taskDTO.getTaskStatus());
//            taskDetailDTO.setTaskTitle(taskDTO.getTaskTitle());
//            taskDetailDTO=taskDetailService.save(taskDetailDTO);
//
//
//            return taskDTO;
//        }
//        else
//            return new TaskDTO();

        //Web Client

//        EmployeeDTO employeeDTO=webClient.get()
//                .uri("http://localhost:7202/employees/"+taskDTO.getAssignee())
//                .retrieve()
//                .bodyToMono(EmployeeDTO.class)
//                .block();
//taskDTO.setAssignee(employeeDTO.getId());
//        Task task = modelMapper.map(taskDTO, Task.class);
//            taskRepository.save(task);
//
//            taskDTO.setId(task.getId());
//
//            TaskDetailDTO taskDetailDTO=new TaskDetailDTO();
//            taskDetailDTO.setEmployeeId(employeeDTO.getId());
//            taskDetailDTO.setEmployeeName(employeeDTO.getName());
//            taskDetailDTO.setEmployeeSurname(employeeDTO.getSurname());
//            taskDetailDTO.setTaskDescription(taskDTO.getTaskDescription());
//            taskDetailDTO.setPriority(taskDTO.getPriorityType());
//            taskDetailDTO.setStatus(taskDTO.getTaskStatus());
//            taskDetailDTO.setTaskTitle(taskDTO.getTaskTitle());
//            taskDetailDTO=taskDetailService.save(taskDetailDTO);
//
//
//            return taskDTO;

        //openFeign

        EmployeeDTO employeeDTO = apiClient.getById(taskDTO.getAssignee());
        taskDTO.setAssignee(employeeDTO.getId());
        Task task = modelMapper.map(taskDTO, Task.class);
        taskRepository.save(task);

        taskDTO.setId(task.getId());

        TaskDetailDTO taskDetailDTO = new TaskDetailDTO();
        taskDetailDTO.setEmployeeId(employeeDTO.getId());
        taskDetailDTO.setEmployeeName(employeeDTO.getName());
        taskDetailDTO.setEmployeeSurname(employeeDTO.getSurname());
        taskDetailDTO.setTaskDescription(taskDTO.getTaskDescription());
        taskDetailDTO.setPriority(taskDTO.getPriorityType());
        taskDetailDTO.setStatus(taskDTO.getTaskStatus());
        taskDetailDTO.setTaskTitle(taskDTO.getTaskTitle());
        taskDetailDTO = taskDetailService.save(taskDetailDTO);

        TaskNotificationDTO taskNotificationDTO=new TaskNotificationDTO();
        taskNotificationDTO.setTaskId(task.getId());
        taskNotificationDTO.setTaskTitle(task.getTaskTitle());
        taskNotificationDTO.setEmployeeId(employeeDTO.getId());
        taskNotificationDTO.setTaskDescription(task.getTaskDescription());
        taskNotificationProducer.sendToQueue(taskNotificationDTO);


        return taskDTO;


    }
private final TaskNotificationProducer taskNotificationProducer;
    @Override
    public TaskDTO updateTask(String Id, TaskDTO taskDTO) {
        taskDTO.setId(Id);
        Task fromUser = modelMapper.map(taskDTO, Task.class);
        Task task = taskRepository.findById(Id).orElseThrow(() -> new IllegalArgumentException());
        task.setTaskTitle(taskDTO.getTaskTitle());
        task.setTaskDescription(taskDTO.getTaskDescription());
        task.setTaskStatus(fromUser.getTaskStatus());
        task.setPriorityType(fromUser.getPriorityType());
        task.setNotes(taskDTO.getNotes());
        task.setAssignee(taskDTO.getAssignee());
        taskRepository.save(task);
        return taskDTO;

    }

    @Override
    public TaskDTO getById(String Id) {
        Task task = taskRepository.findById(Id).orElseThrow(() -> new IllegalArgumentException());
        TaskDTO taskDTO = modelMapper.map(task, TaskDTO.class);
        return taskDTO;
    }

    @Override
    public TaskDTO deleteById(String Id) {
        TaskDTO taskDTO = getById(Id);
        taskRepository.deleteById(Id);
        return taskDTO;
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskDTO> list = tasks.stream().map(task -> modelMapper.map(task, TaskDTO.class)).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<TaskDTO> getTaskPagination(int pagesize, int pageno) {

        Page<Task> tasks = taskRepository.findAll(PageRequest.of(pageno, pagesize));
        List<TaskDTO> dtolist = tasks.stream().map(task -> modelMapper.map(task, TaskDTO.class)).collect(Collectors.toList());
        return dtolist;
    }
}
