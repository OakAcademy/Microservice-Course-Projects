package uk.oakacademy.taskservice.controllers;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.oakacademy.taskservice.dto.TaskDetailDTO;
import uk.oakacademy.taskservice.services.TaskDetailService;

import java.util.List;

@RestController
@RequestMapping("/taskdetail")
@RequiredArgsConstructor
public class TaskDetailController {

    private final TaskDetailService taskDetailService;
    @PostMapping
    @CircuitBreaker(name = "TASK_SERVICE",fallbackMethod = "taskdetailservicefallback")
    public ResponseEntity<TaskDetailDTO> addTaskDetail(@RequestBody TaskDetailDTO taskDetailDTO)
    {
        return new ResponseEntity<TaskDetailDTO>(taskDetailService.save(taskDetailDTO), HttpStatus.CREATED);
    }
public String taskdetailservicefallback()
{
    return "hata task Detail";
}
    @GetMapping
    @CircuitBreaker(name = "TASK_SERVICE",fallbackMethod = "taskdetailservicefallback")
    public ResponseEntity<List<TaskDetailDTO>> getAllTaskDetails()
    {
        return ResponseEntity.ok(taskDetailService.getAllTaskDetails());
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name = "TASK_SERVICE",fallbackMethod = "taskdetailservicefallback")
    public ResponseEntity<TaskDetailDTO> getTaskDetailbyId(@PathVariable("id")String id)
    {
        return ResponseEntity.ok(taskDetailService.getTaskDetailbyId(id));
    }
    @PutMapping("/{id}")
    @CircuitBreaker(name = "TASK_SERVICE",fallbackMethod = "taskdetailservicefallback")
    public ResponseEntity<TaskDetailDTO> updateTaskDetail(@PathVariable String id,@RequestBody TaskDetailDTO taskDetailDTO)
    {
        TaskDetailDTO checkDTO=taskDetailService.getTaskDetailbyId(id);
        if(checkDTO!=null)
        {
            taskDetailDTO.setId(id);
            return ResponseEntity.ok(taskDetailService.updateTaskDetail(taskDetailDTO));
        }
        else
            return new ResponseEntity<TaskDetailDTO>(new TaskDetailDTO(),HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    @CircuitBreaker(name = "TASK_SERVICE",fallbackMethod = "taskdetailservicefallback")
    public ResponseEntity<TaskDetailDTO> deleteTaskDetail(@PathVariable String id)
    {
        return ResponseEntity.ok(taskDetailService.deleteTaskDetail(id));
    }

    @GetMapping("/description/{description}")
    @CircuitBreaker(name = "TASK_SERVICE",fallbackMethod = "taskdetailservicefallback")
    public ResponseEntity<List<TaskDetailDTO>> getByDescription(@PathVariable String description)
    {
        return ResponseEntity.ok(taskDetailService.getWithContainDescription(description));
    }
    @GetMapping("/name/{text}")
    @CircuitBreaker(name = "TASK_SERVICE",fallbackMethod = "taskdetailservicefallback")
    public ResponseEntity<List<TaskDetailDTO>> getByStartswithname(@PathVariable String text)
    {
        return ResponseEntity.ok(taskDetailService.getWithStartsName(text));
    }









}
