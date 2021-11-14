package practice.practice01.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import practice.practice01.domain.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@WebAppConfiguration
public class JdbcTemplateRepositoryTest {

   @Autowired JdbcTemplateRepository repository;

    @Test
    public void save(){
        Member member = new Member();
        member.setId("123");

        repository.save(member);
        Member result = repository.findByNo(member.getNo()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findById(){
        Member member = new Member();
        member.setId("joo");
        repository.save(member);

        Member result = repository.findById("joo").get();
        assertThat(member).isEqualTo(member);

    }

    @Test
    public void findAll(){
        Member member = new Member();
        member.setId("33");
        repository.save(member);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(1);
    }
}
