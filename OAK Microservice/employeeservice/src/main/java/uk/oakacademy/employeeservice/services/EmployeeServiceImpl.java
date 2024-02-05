package uk.oakacademy.employeeservice.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uk.oakacademy.employeeservice.dto.EmployeeDTO;
import uk.oakacademy.employeeservice.entities.Employee;
import uk.oakacademy.employeeservice.repositories.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<EmployeeDTO> getAll() {
        List<Employee> employees=employeeRepository.findAll();
        List<EmployeeDTO> list=employees.stream().map(employee -> modelMapper.map(employee,EmployeeDTO.class))
                .collect(Collectors.toList());

        return list;
    }

    @Override
    public EmployeeDTO getById(String Id) {

        Employee employee=employeeRepository.findById(Id).orElseThrow(()->new IllegalArgumentException());
        EmployeeDTO employeeDTO=modelMapper.map(employee,EmployeeDTO.class);
        return employeeDTO;

    }

    @Override
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        //With model mapper
        Employee employee=modelMapper.map(employeeDTO,Employee.class);
        employee=employeeRepository.save(employee);
        employeeDTO=modelMapper.map(employee,EmployeeDTO.class);
        return employeeDTO;

        //Without modelmapper
//        Employee employee=new Employee();
//        employee.setAge(employeeDTO.getAge());
//        employee.setName(employeeDTO.getName());
//        employee.setJob(employeeDTO.getJob());
//        employee.setEmail(employeeDTO.getEmail());
//        employee.setSurname(employeeDTO.getSurname());
//        employee=employeeRepository.save(employee);
//        employeeDTO.setId(employee.getId());
//        employeeDTO.setAddedDate(employee.getAddedDate());
//        return employeeDTO;

    }

    @Override
    public EmployeeDTO delete(String Id) {

        Employee employee =employeeRepository.findById(Id).orElseThrow(()->new IllegalArgumentException());
        EmployeeDTO employeeDTO=modelMapper.map(employee,EmployeeDTO.class);
       // employeeRepository.deleteById(Id);
       employeeRepository.delete(employee);
        //delete all
//        employeeRepository.deleteAll();
        return employeeDTO;
    }

    @Override
    public EmployeeDTO update(EmployeeDTO employeeDTO) {

        Employee employee=employeeRepository.findById(employeeDTO.getId()).orElseThrow(()->new IllegalArgumentException());
        employee.setName(employeeDTO.getName());
        employee.setJob(employeeDTO.getJob());
        employee.setEmail(employeeDTO.getEmail());
        employee.setAge(employeeDTO.getAge());
        employee.setSurname(employeeDTO.getSurname());
        employeeRepository.save(employee);
        return employeeDTO;
    }

    @Override
    public Page<EmployeeDTO> findPagination(int pagesize, int pageno, String sortField, String sortDirection) {
        return null;
    }
}
