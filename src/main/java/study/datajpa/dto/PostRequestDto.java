package study.datajpa.dto;

import lombok.Data;
import study.datajpa.entity.Post;
import study.datajpa.enums.PostStatus;

@Data
public class PostRequestDto {

    private String title;

    private String content;

    private PostStatus status;

    /***
     * dto에서는 entity를 받아서 dto로 변환하는 메서드만 작성한다.
     * 그니까 requstDto나 modifyRequestDto는 메서드를 작성하지 않아도 된다.
     * response dto만 entity를 받아서 dto로 변환하는 메서드를 작성하자.
     */
}
