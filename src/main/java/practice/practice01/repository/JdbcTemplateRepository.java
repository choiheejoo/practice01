package practice.practice01.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import practice.practice01.domain.Member;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class JdbcTemplateRepository implements MemberRepository {

    private static JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member2").usingGeneratedKeyColumns("no");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", member.getId());
        parameters.put("pw", member.getPw());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setNo(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findByNo(Long no) {
        List<Member> result = jdbcTemplate.query("select * from member2 where no=?", memberRowMapper(), no);
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findById(String id) {
        List<Member> result = jdbcTemplate.query("select * from member2 where id=?", memberRowMapper(),id);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member2", memberRowMapper());
    }

    private RowMapper<Member> memberRowMapper(){
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setNo(rs.getLong("no"));
            member.setId(rs.getString("id"));
            member.setPw(rs.getString("pw"));
            return member;
        };
    }
}