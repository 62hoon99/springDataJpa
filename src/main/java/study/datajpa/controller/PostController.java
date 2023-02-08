package study.datajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.datajpa.dto.PostDetailsDto;
import study.datajpa.dto.PostModifyRequestDto;
import study.datajpa.dto.PostRequestDto;
import study.datajpa.service.PostService;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public PostDetailsDto findPost(@PathVariable Long id) {
        return postService.findPost(id);
    }

    @PostMapping
    public PostDetailsDto savePost(@RequestBody PostRequestDto requestDto) {
        System.out.println("requestDto.getTitle() = " + requestDto.getTitle());
        return postService.savePost(requestDto, 1L);
    }

    @PatchMapping("/{id}")
    public PostDetailsDto modifyPost(@PathVariable Long id, @RequestBody PostModifyRequestDto modifyRequestDto) {
        return postService.modifyPost(modifyRequestDto, id);
    }
}
