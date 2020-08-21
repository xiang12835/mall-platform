package online.flyingfish.api.service.impl;

import online.flyingfish.api.mapper.Demo1Mapper;
import online.flyingfish.api.model.Demo1;
import online.flyingfish.api.service.Demo1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Demo1ServiceImpl implements Demo1Service {
    @Autowired
    private Demo1Mapper demo1Mapper;

    @Override
    public List<Demo1> getDemo1() {
        return demo1Mapper.getDemo1();
    }
}
