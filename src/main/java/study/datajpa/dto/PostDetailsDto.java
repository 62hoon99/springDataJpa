package study.datajpa.dto;

import lombok.Data;
import study.datajpa.entity.Post;
import study.datajpa.enums.PostStatus;

@Data
public class PostDetailsDto {
    private String title;

    private String content;

    private PostStatus status;

    private String memberName;

    private PostDetailsDto(String title, String content, PostStatus status, String memberName) {
        this.title = title;
        this.content = content;
        this.status = status;
        this.memberName = memberName;
    }

    public static PostDetailsDto from(Post post) {
        return new PostDetailsDto(post.getTitle(),
                post.getContent(),
                post.getStatus(),
                post.getMember().getUsername());
    }
}
