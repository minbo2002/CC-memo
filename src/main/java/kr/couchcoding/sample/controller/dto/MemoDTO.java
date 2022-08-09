package kr.couchcoding.sample.controller.dto;

import kr.couchcoding.sample.domain.Category;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemoDTO {

    Category category;

    @NotBlank(message = "제목을 입력해주세요.")
    String name;

    @NotBlank(message = "내용을 입력해주세요.")
    String content;

}
