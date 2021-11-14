package practice.practice01.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import practice.practice01.domain.Member;
import practice.practice01.repository.JdbcTemplateRepository;
import practice.practice01.repository.MemberRepository;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired JdbcTemplateRepository memberRepository;

//    @BeforeEach
//    public void beforeEach(){
//        memberRepository = new JdbcTemplateRepository();
//        memberService = new MemberService(memberRepository);
//    }

    //회원가입
    @Test
    void join() {
        //given
        Member member = new Member();
        member.setId("joo");

        //when
        Long saveNo = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveNo).get();
        assertThat(member.getId()).isEqualTo(findMember.getId());


    }

    //중복 아이디 예외
    @Test
    public void duplicateException(){

        //given
        Member member1 = new Member();
        member1.setId("id");
        member1.setPw("1234");

        Member member2 = new Member();
        member2.setId("id");
        member2.setPw("1234");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 아이디입니다.");

        //then
    }

    @Test
    void findMembers() {
    }
}