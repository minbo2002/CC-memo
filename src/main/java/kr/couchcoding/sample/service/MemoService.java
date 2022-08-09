package kr.couchcoding.sample.service;

import kr.couchcoding.sample.controller.dto.MemoDTO;
import kr.couchcoding.sample.domain.Memo;
import kr.couchcoding.sample.domain.MemoEditor;
import kr.couchcoding.sample.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public Memo createMemo(MemoDTO memoDTO) {
        return memoRepository.save(Memo.builder()
                .category(memoDTO.getCategory())
                .name(memoDTO.getName())
                .content(memoDTO.getContent())
                .build());
    }

    public Memo getMemoById(Long id) {
        return memoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "메모가 존재하지 않습니다."));
    }

    public Page<Memo> getMemoList(Pageable pageable, String keyword) {
        if (keyword == null) {
            return memoRepository.findAll(pageable);
        } else {
            return memoRepository.findByNameContains(pageable, keyword);
        }
    }

    @Transactional
    public void edit(Long id, MemoDTO memoDTO) {
        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "메모가 존재하지 않습니다."));

        MemoEditor.MemoEditorBuilder editorBuilder = memo.toEditor();

        memo.edit(editorBuilder
                .category(memoDTO.getCategory())
                .name(memoDTO.getName())
                .content(memoDTO.getContent())
                .build());
    }


    public void delete(Long id) {
        memoRepository.delete(memoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "메모가 존재하지 않습니다.")));
    }
}
