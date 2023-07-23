package me.kzv.ecommerce.module.member;

import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    void update(Member member);

    Optional<Member> findById(long idx);

    Optional<Member> findByEmail(String email);
}
