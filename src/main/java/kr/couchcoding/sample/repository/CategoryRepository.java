package kr.couchcoding.sample.repository;

import kr.couchcoding.sample.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    Page<Category> findByNameContains(Pageable pageable, String name);
}
