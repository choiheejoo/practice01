package practice.practice01.repository;

import practice.practice01.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByNo(Long no);
    Optional<Member> findById(String id);
    List<Member> findAll();
}
