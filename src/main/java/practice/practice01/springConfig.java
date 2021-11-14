package practice.practice01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import practice.practice01.repository.JdbcTemplateRepository;
import practice.practice01.repository.MemberRepository;
import practice.practice01.service.MemberService;

import javax.sql.DataSource;

@Configuration
public class springConfig {

        private final DataSource dataSource;

        public springConfig(DataSource dataSource) {
            this.dataSource = dataSource;
        }

//        @Bean
//        public MemberService memberService() {
//            return new MemberService(memberRepository());
//        }

//        @Bean
//        public MemberRepository memberRepository() {
//           return new JdbcTemplateRepository(dataSource);
//        }
}
