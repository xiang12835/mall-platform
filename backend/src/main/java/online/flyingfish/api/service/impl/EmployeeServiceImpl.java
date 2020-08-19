package online.flyingfish.api.service.impl;

import online.flyingfish.api.mapper.EmployeeMapper;
import online.flyingfish.api.model.Employee;
import online.flyingfish.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee getEmployeeById(int id) {
        return employeeMapper.getEmployeeById(id);
    }
}
