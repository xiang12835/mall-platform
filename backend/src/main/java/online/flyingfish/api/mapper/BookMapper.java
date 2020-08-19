package online.flyingfish.api.mapper;

import online.flyingfish.api.model.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookMapper {

    @Insert({"insert into book(id, name,  book_price) values(#{id}, #{name}, #{bookPrice})"})
    void addBook(Book book);

    @Delete("delete from book where id=#{id}")
    void delBookById(@Param("id") int id);

    @Update("update book set name = #{name}, price = #{price} where id = #{id}")
    void updateBookById(@Param("name") String name, @Param("price") double price, @Param("id") int id);

    @Select("select * from book")
    List<Book> getBook();

    @Select("select id, name, book_price from book where id = #{id}")
    Book getBookInfoById(@Param("id") int id);

}
