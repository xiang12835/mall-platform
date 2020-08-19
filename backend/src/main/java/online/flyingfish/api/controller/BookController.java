package online.flyingfish.api.controller;

import io.swagger.annotations.*;
import online.flyingfish.api.model.Book;
import online.flyingfish.api.service.BookService;
import online.flyingfish.common.Result.Result;
import online.flyingfish.common.Result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/book")
@Api("图书api")
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation("查询书本信息")
    @RequestMapping(value ="/list", method = RequestMethod.GET)
    @ResponseBody
    public Result BookQry() {
        List<Book> bookList = bookService.getBook();
        return Result.success(bookList);
    }

//    @ApiOperation("增加书本信息")
//    @RequestMapping(value ="/add", method = RequestMethod.POST)
//    @ResponseBody
//    public Result BookAdd(@RequestBody Book book){
//        try{
//            bookService.addBook(book);
//            return Result.success();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Result.failure(ResultCode.INTERFACE_INNER_INVOKE_ERROR);
//        }
//
//    }

    @ApiOperation("增加书本信息")
    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public Result BookAdd(@RequestParam String id,
                          @RequestParam String name,
                          @RequestParam String bookPrice){
        try{
            Book book = new Book();
            book.setId(Integer.parseInt(id));
            book.setName(name);
            book.setPrice(Double.parseDouble(bookPrice));
            bookService.addBook(book);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(ResultCode.INTERFACE_INNER_INVOKE_ERROR);
        }

    }

    @ApiOperation("修改书本信息")
    @RequestMapping(value ="/update", method = RequestMethod.PUT)
    public void BookUpdate(Book book){
        bookService.updateBookById(book);
    }

    @ApiOperation("删除书本信息")
    @ApiImplicitParam(name = "id", value = "书本编号", required = true, dataType = "Integer")
    @RequestMapping(value ="/delById", method = RequestMethod.DELETE)
    public void BookDel(@RequestParam(value="id") int id){
        bookService.delBookById(id);
    }

    @ApiOperation("根据id查询书本信息")
    @ApiImplicitParam(name = "id", value = "书本编号", required = true, dataType = "Integer")
    @RequestMapping(value = "/getBookInfo", method = RequestMethod.GET)
    @ResponseBody
    public Result getBookInfo(@RequestParam(value="id") int id) {
//        Map<String, Object> map = new HashMap<>();
//        Book book = bookService.getBookInfoById(id);
//        map.put("id", book.getId());
//        map.put("name", book.getName());
//        map.put("price", book.getPrice());
        return Result.success(bookService.getBookInfoById(id));
    }


}
