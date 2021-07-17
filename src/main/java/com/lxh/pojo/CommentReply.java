package com.lxh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author LXH
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
