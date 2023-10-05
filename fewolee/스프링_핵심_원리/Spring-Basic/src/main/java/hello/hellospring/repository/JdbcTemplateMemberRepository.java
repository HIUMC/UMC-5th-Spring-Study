package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository{

    private final JdbcTemplate jdbcTemplate;

    //생성자1개인경우 Autowired 생략 가능
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        //SimpleJdbcInsert : insert문 짤 피요 없도록 도와주는 라이브러리
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //템플릿 메소드 패턴을 사용하여 jdbcTemplate
        List<Member> result =  jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);
        //findAny() : 해당 스트림에서 첫번째 요소를 참조하는 Optional객체 반환
        //findFirst()와 차이 : findAny()는 병렬 스트림(parallel)에서 정확한 연산결과 받을 수 있음
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        //템플릿 메소드 패턴을 사용하여 jdbcTemplate
        List<Member> result =  jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
        //findAny() : 해당 스트림에서 첫번째 요소를 참조하는 Optional객체 반환
        //findFirst()와 차이 : findAny()는 병렬 스트림(parallel)에서 정확한 연산결과 받을 수 있음
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //템플릿 메소드 패턴을 사용하여 jdbcTemplate
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }

    private RowMapper<Member> memberRowMapper() {
        //익명 구현 객체(1회용 사용시)
//        return new RowMapper<Member>() {
//            @Override
//            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Member member = new Member();
//                member.setId(rs.getLong("id"));
//                member.setName(rs.getString("name"));
//
//                return member;
//            }
//        }

        //람다로 변경(alt+enter)
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));

            return member;
        };
    }
}
