package online.flyingfish.api.service;

import online.flyingfish.api.model.Book;

import java.util.List;

public interface BookService {

    void addBook(Book book);

    void delBookById(int id);

    void updateBookById(Book book);

    List<Book> getBook();

    Book getBookInfoById(int id);
}
