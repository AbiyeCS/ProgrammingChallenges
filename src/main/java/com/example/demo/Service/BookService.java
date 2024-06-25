package com.example.demo.Service;

import com.example.demo.Exception.BookDoesNotExistException;
import com.example.demo.Model.Book;
import com.example.demo.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Book getBook(Integer id){
        Optional<Book> books = bookRepository.findById(id);
        return books.orElseThrow(() -> new BookDoesNotExistException("Book does not exist"));
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book updateBook(Book newBook, Integer id){
        return bookRepository.findById(id)
                .map(book -> {
                    book.setId(newBook.getId());
                    book.setAuthor(newBook.getAuthor());
                    book.setTitle(newBook.getTitle());
                    book.setPrice(newBook.getPrice());
                    book.setPublisher(newBook.getPublisher());
                    book.setIsbn(newBook.getIsbn());
                    book.setPublicationYear(newBook.getPublicationYear());
                    book.setGenre(newBook.getGenre());
                    book.setAvailable(newBook.isAvailable());
                    bookRepository.save(book);
                    return bookRepository.save(book);
                }).orElseThrow(() -> new BookDoesNotExistException("Book Not Found"));
    }

    public String deleteBook(Integer id){
        if(!bookRepository.existsById(id)){
            throw new BookDoesNotExistException("Book Not Found");
        }
        bookRepository.deleteById(id);

        return "Book Deleted Successfully";
    }
}
