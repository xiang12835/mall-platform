//package online.flyingfish.api.controller;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import online.flyingfish.api.model.Demo1;
//import online.flyingfish.api.service.Demo1Service;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api_eg")
//@Api("样例 api")
//public class Demo1Controller {
//    @Autowired
//    private Demo1Service demo1Service;
//
//    @ApiOperation("样例信息")
//    @RequestMapping(value ="/demo1", method = RequestMethod.GET)
//    public List<Demo1> Demo1Qry() {
//        return demo1Service.getDemo1();
//    }
//}
