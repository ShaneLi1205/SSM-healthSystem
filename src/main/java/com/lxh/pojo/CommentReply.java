package com.lxh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 评论信息实体类
 * @author LXH
 * @date 2021/7/18 9:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentReply {
    private int commentReplyId;
    private int useId;
    private int workerId;
    private int articleCommentId;
    private String commentReplyContent;
    private String userName;
}
