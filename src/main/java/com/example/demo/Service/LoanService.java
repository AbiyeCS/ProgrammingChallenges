package com.example.demo.Service;

import com.example.demo.Exception.BookDoesNotExistException;
import com.example.demo.Exception.LoanDoesNotExistException;
import com.example.demo.Exception.MemberDoesNotExistException;
import com.example.demo.Model.Loan;
import com.example.demo.Model.Book;
import com.example.demo.Model.Member;
import com.example.demo.Repository.LoanRepository;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    public List<Loan> findAll(){
        return loanRepository.findAll();
    }

    public Loan addLoan(int memberId, int bookId, Loan newLoan){
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isEmpty()) {
            throw new BookDoesNotExistException("Book is unavailable for loaning.");
        }

        Optional<Member> memberOptional = memberRepository.findById(memberId);
        if (memberOptional.isEmpty()) {
            throw new MemberDoesNotExistException("Book is unavailable for loaning.");
        }

        newLoan.setBook(bookOptional.get());
        newLoan.setMember(memberOptional.get());
        newLoan.setLoanDate(new Date());

        // Set the return date to one week from today
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date()); // Set calendar to today's date
        calendar.add(Calendar.WEEK_OF_YEAR, 1); // Add one week
        newLoan.setReturnDate(calendar.getTime());

//        Book book = bookOptional.map(book1 -> {
//            book1.setAvailable(false);
//        }).orElseThrow(() -> new BookDoesNotExistException("Book Not Found"));
//        bookRepository.save(book);

        return loanRepository.save(newLoan);
    }

//    public Loan LoanReturned(int loanId, int bookId, int memberId){
//        Optional<Loan> loanOptional = loanRepository.findById(loanId);
//        Optional<Book> bookOptional = bookRepository.findById(bookId);
//
//        if (loanOptional.isEmpty()) {
//            throw new LoanDoesNotExistException("Could not find the loan.");
//        }
//
//        return LoanRepository.find
//
//    }

    public List<Book> findAllLoansByMemberId(int memberId){
        Member member = memberRepository.findById(memberId).get();
        List<Loan> loans = loanRepository.findByMember(member);
        List<Book> books = new ArrayList<>();

        // Iterate over each loan and add the book to the list
        for (Loan loan : loans) {
            Book book = loan.getBook();
            if (!books.contains(book)) { // Check to prevent adding duplicate books
                books.add(book);
            }
        }
        return books;
    }
}
