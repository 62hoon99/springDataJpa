package study.datajpa.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import study.datajpa.dto.PostModifyRequestDto;
import study.datajpa.dto.PostRequestDto;
import study.datajpa.enums.PostStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@DynamicUpdate
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

    private PostStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @CreatedDate
    private LocalDateTime createdAt;

    /***
     * entity에서는 dto를 받아서 entity를 반환하는 메서드만 작성한다.
     */

    private Post(String title, String content, PostStatus status, Member member) {
        this.title = title;
        this.content = content;
        this.status = status;
        this.member = member;
    }

    public static Post from(PostRequestDto requestDto, Member member) {
        return new Post(
                requestDto.getTitle(),
                requestDto.getContent(),
                requestDto.getStatus(),
                member);
    }

    public void update(PostModifyRequestDto modifyRequestDto) {
        this.title = modifyRequestDto.getTitle();
        this.content = modifyRequestDto.getContent();
    }
}
