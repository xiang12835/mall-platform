package online.flyingfish.api.service.impl;

import online.flyingfish.api.model.Book;
import online.flyingfish.api.mapper.BookMapper;
import online.flyingfish.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public void addBook(Book book) {
        bookMapper.addBook(book);
    }

    @Override
    public void delBookById(int id) {
        bookMapper.delBookById(id);
    }

    @Override
    public void updateBookById(Book book) {
        bookMapper.updateBookById(book.getName(),book.getPrice(),book.getId());
    }

    @Override
    public List<Book> getBook() {
        return bookMapper.getBook();
    }

    @Override
    public Book getBookInfoById(int id) { return  bookMapper.getBookInfoById(id); }

}
