package uk.oakacademy.employeeservice.services;

import org.springframework.data.domain.Page;
import uk.oakacademy.employeeservice.dto.EmployeeDTO;
import uk.oakacademy.employeeservice.entities.Employee;

import java.util.List;

public interface EmployeeService {
    public List<EmployeeDTO> getAll();
    public EmployeeDTO getById(String Id);
    public EmployeeDTO save(EmployeeDTO employeeDTO);
    public EmployeeDTO delete(String Id);
    public EmployeeDTO update(EmployeeDTO employeeDTO);
    public Page<EmployeeDTO> findPagination(int pagesize,int pageno,String sortField,String sortDirection);
}
