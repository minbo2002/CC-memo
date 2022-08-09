package kr.couchcoding.sample.controller;

import kr.couchcoding.sample.controller.dto.MemoDTO;
import kr.couchcoding.sample.domain.Memo;
import kr.couchcoding.sample.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/memo")
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("")
    public Memo createMemo(@RequestBody MemoDTO memoDTO) {
        return memoService.createMemo(memoDTO);
    }

    @GetMapping("/{memoId}")
    public Memo getMemo(@PathVariable Long memoId) {
        return memoService.getMemoById(memoId);
    }

    @GetMapping("")
    public Page<Memo> getMemoList(Pageable pageable, @RequestParam String keyword) {
        return memoService.getMemoList(pageable, keyword);
    }

    @PatchMapping("/{memoId}")
    public void editMemo(@PathVariable Long memoId, @RequestBody MemoDTO memoDTO) {
        memoService.edit(memoId, memoDTO);
    }

    @DeleteMapping("/{memoId}")
    public void deleteMemo(@PathVariable Long memoId) {
        memoService.delete(memoId);
    }
}
