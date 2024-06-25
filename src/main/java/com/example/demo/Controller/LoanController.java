package com.example.demo.Controller;

import com.example.demo.Model.Book;
import com.example.demo.Model.Loan;
import com.example.demo.Service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class LoanController {

    @Autowired
    LoanService loanService;

    @PostMapping("/loan/{memberId}/{bookId}")
    public ResponseEntity<?> addLoan(@RequestParam int memberId, @RequestParam int bookId ,@RequestBody Loan newLoan) {
        return ResponseEntity.ok(loanService.addLoan(memberId, bookId, newLoan));
    }

//    @PutMapping("/loan/{loanId}/{memberId}/{bookId}")
//    String LoanDelete(@PathVariable int loanId, @PathVariable int memberId, @PathVariable int bookId) {
//
//    }

    @GetMapping("/loan/{memberId}")
    List<Book> getBooksCurrentlyLoaned(@PathVariable int memberId){
        return loanService.findAllLoansByMemberId(memberId);
    }

    @GetMapping("/loans")
    List<Loan> getAllLoans(){
        return loanService.findAll();
    }
}
