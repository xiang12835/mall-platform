package online.flyingfish.api.service;

import online.flyingfish.api.model.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface EmployeeAccountService {
    List<EmployeeAccountBase> getEmployeeAccountBase();
//    List<EmployeeAccountCountMonth> getEmployeeAccountCountMonth();
//    List<EmployeeAccountCountYear> getEmployeeAccountCountYear();
    List<BigDecimal> getEmployeeAccountCountMonth(int user_id);
    BigDecimal getEmployeeAccountCountYear(int user_id);

    List<EmployeeAccountBase> sortEmployeeAccountBase(String sortStr, String sortOpt);



}
