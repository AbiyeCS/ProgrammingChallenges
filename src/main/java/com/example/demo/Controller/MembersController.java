package com.example.demo.Controller;

import com.example.demo.Model.Member;
import com.example.demo.Repository.MemberRepository;
import com.example.demo.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class MembersController {

    @Autowired
    MemberService memberService;

    @PostMapping("/member")
    public ResponseEntity<?> newMember(@RequestBody Member member) {
        return ResponseEntity.ok(memberService.addMember(member));
    }

    @GetMapping("/members")
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/member/{id}")
    public Member getMemberById(@PathVariable int id) {
        return memberService.getMember(id);
    }

    @PutMapping("/member/{id}")
    public Member updateMember(@PathVariable int id, @RequestBody Member member) {
        return memberService.updateMember(member, id);
    }

    @DeleteMapping("/member/{id}")
    String deleteMember(@PathVariable int id) {
        return memberService.deleteMember(id);
    }
}
