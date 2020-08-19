package online.flyingfish.api.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import online.flyingfish.api.model.Employee;
import online.flyingfish.api.service.EmployeeService;
import online.flyingfish.common.Result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/employee")
@Api("用户api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @ApiOperation("获取单个用户信息")
    @ApiImplicitParam(name = "empId", value = "员工编号", required = true, dataType = "Integer")
    @RequestMapping(value ="/getOne", method = RequestMethod.GET)
    @ResponseBody
    public Result getOneEmployee(@RequestParam(value="empId") int empId) {
        Employee employee = employeeService.getEmployeeById(empId);
        return Result.success(employee);
    }
}
