package com.lxh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * 文章评论表实体类
 * @author LXH
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleComment {
    private int articleCommentId;
    private int articleId;
    private int userId;
    private int workerId;
    private String articleCommentContent;
    private String articleCommentTime;
    private String workerName;
    private String userName;
    private ArrayList<CommentReply> commentReplies = null;
}
