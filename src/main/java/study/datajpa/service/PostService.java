package study.datajpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.PostDetailsDto;
import study.datajpa.dto.PostModifyRequestDto;
import study.datajpa.dto.PostRequestDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.Post;
import study.datajpa.repository.MemberRepository;
import study.datajpa.repository.PostRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final MemberRepository memberRepository;

    public PostDetailsDto findPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        return PostDetailsDto.from(post);
    }

    @Transactional
    public PostDetailsDto savePost(PostRequestDto postRequestDto,
                                   Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        Post post = Post.from(postRequestDto, member);
        Post savedPost = postRepository.save(post);

        return PostDetailsDto.from(savedPost);
    }

    @Transactional
    public PostDetailsDto modifyPost(PostModifyRequestDto modifyRequestDto, Long id) {
        Post post = postRepository.findById(id).get();
        post.update(modifyRequestDto);
        return PostDetailsDto.from(post);
    }
}
