package kr.couchcoding.sample.domain;

import kr.couchcoding.sample.controller.dto.MemoDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Memo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    Category category;

    @Column(length = 100)
    String name;

    @Column(columnDefinition = "TEXT")
    String content;

    @Builder
    public Memo(Category category, String name, String content) {
        this.category = category;
        this.name = name;
        this.content = content;
    }

    public MemoEditor.MemoEditorBuilder toEditor() {
        return MemoEditor.builder()
                .category(category)
                .name(name)
                .content(content);
    }

    public void edit(MemoEditor memoEditor) {
        this.category = memoEditor.getCategory();
        this.name = memoEditor.getName();
        this.content = memoEditor.getContent();
    }
}
