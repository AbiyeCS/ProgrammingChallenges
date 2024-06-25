package com.example.demo.Repository;

import com.example.demo.Model.Loan;
import com.example.demo.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
    List<Loan> findByMember(Member memberId);
}
