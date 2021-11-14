package practice.practice01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.practice01.domain.Member;
import practice.practice01.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){
        //아이디 중복체크
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getNo();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findById(member.getId())
        .ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        });
    }

    /**
     * 전체 회원 조회
     */

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberNo){
        return memberRepository.findByNo(memberNo);
    }



}
