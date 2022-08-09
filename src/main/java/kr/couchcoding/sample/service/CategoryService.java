package kr.couchcoding.sample.service;

import kr.couchcoding.sample.controller.dto.CategoryDTO;
import kr.couchcoding.sample.domain.Category;
import kr.couchcoding.sample.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Category createCategory(CategoryDTO categoryDTO) {
        Optional<Category> findOne = categoryRepository.findByName(categoryDTO.getName());
        if (findOne.isPresent()) {
            throw new ResponseStatusException(BAD_REQUEST, "중복된 이름입니다.");
        }

        Category category = Category.builder()
                .name(categoryDTO.getName())
                .build();

        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "카테고리가 존재하지 않습니다."));
    }

    public Page<Category> getCategories(Pageable pageable, String keyword) {
        if (keyword == null) {
            return categoryRepository.findAll(pageable);
        } else {
            return categoryRepository.findByNameContains(pageable, keyword);
        }
    }
}
