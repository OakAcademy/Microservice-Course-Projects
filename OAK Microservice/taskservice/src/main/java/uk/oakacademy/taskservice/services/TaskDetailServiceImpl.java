package uk.oakacademy.taskservice.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uk.oakacademy.taskservice.dto.TaskDetailDTO;
import uk.oakacademy.taskservice.entities.TaskDetail;
import uk.oakacademy.taskservice.repositories.TaskDetailRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskDetailServiceImpl implements TaskDetailService {

    private final TaskDetailRepository taskDetailRepository;
    private final ModelMapper modelMapper;

    @Override
    public TaskDetailDTO save(TaskDetailDTO taskDetailDTO) {
        TaskDetail taskDetail = modelMapper.map(taskDetailDTO, TaskDetail.class);
        taskDetailRepository.save(taskDetail);
        taskDetailDTO = modelMapper.map(taskDetail, TaskDetailDTO.class);
        return taskDetailDTO;

    }

    @Override
    public TaskDetailDTO getTaskDetailbyId(String Id) {
        TaskDetail taskDetail=taskDetailRepository.findById(Id).orElseThrow(()->new IllegalArgumentException());
        TaskDetailDTO taskDetailDTO=modelMapper.map(taskDetail,TaskDetailDTO.class);
        return taskDetailDTO;
    }

    @Override
    public List<TaskDetailDTO> getAllTaskDetails() {
        List<TaskDetail> tasklist=new ArrayList<>();
        taskDetailRepository.findAll().forEach(tasklist::add);
        List<TaskDetailDTO> dtoList=tasklist.stream().map(taskDetail -> modelMapper.map(taskDetail,TaskDetailDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public TaskDetailDTO updateTaskDetail(TaskDetailDTO taskDetailDTO) {

        TaskDetail taskDetail =taskDetailRepository.findById(taskDetailDTO.getId()).
                orElseThrow(()->new IllegalArgumentException());
        taskDetail.setTaskTitle(taskDetailDTO.getTaskTitle());
        taskDetail.setTaskDescription(taskDetailDTO.getTaskDescription());
        taskDetail.setStatus(taskDetailDTO.getStatus());
        taskDetail.setPriority(taskDetailDTO.getPriority());
        taskDetail.setEmployeeId(taskDetailDTO.getEmployeeId());
        taskDetail.setEmployeeName(taskDetailDTO.getEmployeeName());
        taskDetail.setEmployeeSurname(taskDetailDTO.getEmployeeSurname());
        taskDetailRepository.save(taskDetail);
        return taskDetailDTO;
    }

    @Override
    public TaskDetailDTO deleteTaskDetail(String Id) {
        TaskDetail taskDetail =taskDetailRepository.findById(Id).
                orElseThrow(()->new IllegalArgumentException());
        TaskDetailDTO taskDetailDTO=modelMapper.map(taskDetail,TaskDetailDTO.class);
        taskDetailRepository.deleteById(Id);
        return taskDetailDTO;
    }

    @Override
    public List<TaskDetailDTO> getWithContainDescription(String description) {
        List<TaskDetail> taskDetails=taskDetailRepository.findByTaskDescriptionContains(description);
        List<TaskDetailDTO> dtolist=taskDetails.stream().map(taskDetail -> modelMapper.
                map(taskDetail,TaskDetailDTO.class)).collect(Collectors.toList());
        return dtolist;
    }

    @Override
    public List<TaskDetailDTO> getWithStartsName(String text) {
        List<TaskDetail> taskDetails=taskDetailRepository.findTaskDetailByEmployeeNameStartingWith(text);
        List<TaskDetailDTO> dtolist=taskDetails.stream().map(taskDetail -> modelMapper.
                map(taskDetail,TaskDetailDTO.class)).collect(Collectors.toList());
        return dtolist;
    }
}
