package study.datajpa.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.dto.MemberDto;
import study.datajpa.dto.PostDetailsDto;
import study.datajpa.dto.PostModifyRequestDto;
import study.datajpa.entity.Member;
import study.datajpa.repository.MemberRepository;
import study.datajpa.service.PostService;

import javax.annotation.PostConstruct;

@RestController
public class HelloController {
    private final MemberRepository memberRepository;

    public HelloController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/members")
    public Page<MemberDto> list(Pageable pageable) {
        Page<Member> page = memberRepository.findAll(pageable);
        return page.map(m -> new MemberDto(m.getId(), m.getUsername(), null));
    }

    @PostConstruct
    public void init() {
        for(int i = 0; i < 100; i ++) {
            memberRepository.save(new Member("user" + i, i));
        }
    }
}
