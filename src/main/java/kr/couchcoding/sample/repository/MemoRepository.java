package kr.couchcoding.sample.repository;

import kr.couchcoding.sample.domain.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    Page<Memo> findByNameContains(Pageable pageable, String name);

}
