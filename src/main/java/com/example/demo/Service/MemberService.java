package com.example.demo.Service;

import com.example.demo.Exception.MemberDoesNotExistException;
import com.example.demo.Model.Member;
import com.example.demo.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public Member getMember(Integer id){
        Optional<Member> members = memberRepository.findById(id);
        return members.orElseThrow(() -> new MemberDoesNotExistException("Member does not exist"));
    }

    public Member addMember(Member member){
        return memberRepository.save(member);
    }

    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }

    public Member updateMember(Member newMember, Integer id){
        return memberRepository.findById(id)
                .map(member -> {
                    member.setId(newMember.getId());
                    member.setFirstName(newMember.getFirstName());
                    member.setLastName(newMember.getLastName());
                    member.setEmail(newMember.getEmail());
                    member.setPhone(newMember.getPhone());
                    member.setAddress(newMember.getAddress());
                    return memberRepository.save(member);
                }).orElseThrow(() -> new MemberDoesNotExistException("Member Not Found"));
    }

    public String deleteMember(Integer id){
        if(!memberRepository.existsById(id)){
            throw new MemberDoesNotExistException("Member Not Found");
        }
        memberRepository.deleteById(id);

        return "Member Deleted Successfully";
    }
}
