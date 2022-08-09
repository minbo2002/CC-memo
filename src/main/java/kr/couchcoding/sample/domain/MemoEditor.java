package kr.couchcoding.sample.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemoEditor {

    private final Category category;
    private final String name;
    private final String content;

    @Builder
    public MemoEditor(Category category, String name, String content) {
        this.category = category;
        this.name = name;
        this.content = content;
    }
}
