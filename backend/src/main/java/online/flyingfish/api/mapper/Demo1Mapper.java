package online.flyingfish.api.mapper;

import online.flyingfish.api.model.Demo1;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Demo1Mapper {
    @Select("select * from demo1")
    List<Demo1> getDemo1();
}
