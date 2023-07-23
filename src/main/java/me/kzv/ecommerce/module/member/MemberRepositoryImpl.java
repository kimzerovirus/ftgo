package me.kzv.ecommerce.module.member;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MemberRepositoryImpl implements MemberRepository {
    private final JdbcTemplate template;

//    @Override
//    public User save(User user) {
//        String sql = "insert into member(name, email, password) values (?, ?, ?)";
//        template.update(sql, user.getName(), user.getEmail(), user.getPassword());
//        return user;
//    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(template);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("idx");

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());
        parameters.put("email", member.getEmail());
        parameters.put("password", member.getPassword());
        parameters.put("created_at", member.getCreatedAt());

        Long idx = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters)).longValue();
        member.insertAutoIncrementedIdx(idx);

        return member;
    }

    @Override
    public void update(Member member) {
        String sql = "UPDATE member SET passwd=? WHERE idx=?";
        template.update(sql, member.getPassword(), member.getIdx());
    }

    @Override
    public Optional<Member> findById(long idx) {
        String sql = "SELECT * FROM member WHERE seq=?";
        List<Member> results = template.query(sql, mapper, idx);
//        return Optional.ofNullable(results.isEmpty() ? null : results.get(0));
        return results.stream().findAny(); // findAny 와 findFirst 차이 -> first 는 스트림의 순서를 고려하여 첫번째 요소를 반환하지만 any 는 가장 먼저 찾은 요소를 반환해서 병렬 환경일 경우 매번 값이 달라질 수 있다.
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        String sql = "SELECT * FROM member WHERE email=?";
        List<Member> results = template.query(sql, mapper, email);
        return results.stream().findAny();
    }

    private static RowMapper<Member> mapper = (rs, rowNum) -> Member.builder()
            .idx(rs.getLong("idx"))
            .email(rs.getString("email"))
            .name(rs.getString("name"))
            .password(rs.getString("password"))
            .createdAt(rs.getTimestamp("created_at").toLocalDateTime()).build();
}
