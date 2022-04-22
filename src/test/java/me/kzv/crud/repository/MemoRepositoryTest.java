package me.kzv.crud.repository;

import me.kzv.crud.entity.Memo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTest {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass() {
        System.out.println(memoRepository.getClass().getName());
    }

    @Test
    public void testInsertDummies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder()
                    .memoText("Sample..." + i)
                    .build();

            memoRepository.save(memo);
        });
    }

    @Test
    public void testSelect() {
        Long mno = 100L;

        Optional<Memo> result = memoRepository.findById(mno);
        System.out.println("=========");
        if (result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo);
        }
    }

    @Transactional
    @Test
    public void testSelect2() {
        Long mno = 100L;

        Memo memo = memoRepository.getOne(mno); //deprecated
        System.out.println("==============");
        System.out.println(memo);

    }

    @Test
    public void testUpdate() {
        Memo memo = Memo.builder().mno(100L).memoText("Update Text").build();
        System.out.println(memoRepository.save(memo));
        // select 후 해당 아이디가 존재한다면 update를 존재하지 않는다면 create를 실행한다.
    }

    @Test
    public void testDelete() {
        Long mno = 100L;

        memoRepository.deleteById(mno);
    }

    @Test
    public void testPageDefault() {
        // 1페이지 10개
        PageRequest pageable = PageRequest.of(0, 10);
        Page<Memo> result = memoRepository.findAll(pageable);

        System.out.println(result);
        System.out.println("======================================");
        // 총 몇 페이지?
        System.out.println("Total Page" + result.getTotalPages());
        // 전체 개수
        System.out.println("Total Count" + result.getTotalElements());
        // 현재 페이지 번호 0~
        System.out.println("Page Number" + result.getNumber());
        // 페이지당 가져오는 데이터 수
        System.out.println("Page Size" + result.getSize());
        // 다음페이지 존재 여부
        System.out.println("Has Next Page?" + result.hasNext());
        // 시작(0)페이지 여부
        System.out.println("Is First Page?" + result.isFirst());
    }

    @Test
    public void testSort(){
        Sort sort1 = Sort.by("mno").descending();
        PageRequest page = PageRequest.of(0, 10, sort1);
        Page<Memo> result = memoRepository.findAll(page);

        result.get().forEach(memo -> {
            System.out.println(memo);
        });
    }

    // 스트림은 데이터 소스를 추상화하고, 데이터를 다루는데 자주 사용되는 메서드들을 정의해 놓았다. get
    // 스트림은 소스로부터 데이터를 읽기만 할뿐 변경은 X
}