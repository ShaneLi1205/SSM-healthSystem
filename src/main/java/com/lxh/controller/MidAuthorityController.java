package com.lxh.controller;

import com.alibaba.fastjson.JSON;
import com.lxh.bean.ResultInfo;
import com.lxh.bean.StatusCode;
import com.lxh.pojo.ArticleInfo;
import com.lxh.pojo.Worker;
import com.lxh.service.ArticleService;
import com.lxh.service.WorkerService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: LXH
 * @Date: 2021/7/21 9:28
 */
@RestController
public class MidAuthorityController {

    private WorkerService workerService;
    private ArticleService articleService;

    public MidAuthorityController(@Qualifier("workerServiceImpl") WorkerService workerService, @Qualifier("articleServiceImpl") ArticleService articleService) {
        this.workerService = workerService;
        this.articleService = articleService;
    }

    /**
     * 获得工作者状态
     * @param workerId 工作者ID
     * @return 工作者状态
     */
    @RequestMapping("/getWorkerState")
    public String getWorkerState(Integer workerId){
        String jsonStr;
        Worker worker = workerService.getWorkerBanTime(workerId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = simpleDateFormat.format(new Date());
        String currentBanTime = worker.getWorkerBanTime();
        if (currentTime.compareTo(currentBanTime) >= 0){
            jsonStr = JSON.toJSONString(new ResultInfo<>(true,StatusCode.WORKER_STATE_NORMAL.getMessage(),worker.getWorkerBanTime()));
        } else {
            jsonStr = JSON.toJSONString(new ResultInfo<>(false,StatusCode.WORKER_STATE_BANNED.getMessage(),worker.getWorkerBanTime()));
        }
        return jsonStr;
    }

    /**
     * 工作者发布文章
     * @param articleInfo 发布的文章信息
     * @return 成功与否
     */
    @RequestMapping("/releaseArticle")
    public String releaseArticle(ArticleInfo articleInfo){
        String jsonStr;

        //截取部分用作页面展示
        if (articleInfo.getArticleSummary().length() >= 50){
            articleInfo.setArticleSummary(articleInfo.getArticleSummary().substring(0,51));
        } else {
            articleInfo.setArticleSummary(articleInfo.getArticleSummary());
        }
        //保存到数据库
        if (articleService.saveNewArticleInfo(articleInfo)){
            jsonStr = JSON.toJSONString(new ResultInfo<>(true, StatusCode.SAVE_ARTICLE_SUCCESS.getMessage()));
        } else {
            jsonStr = JSON.toJSONString(new ResultInfo<>(true, StatusCode.SAVE_ARTICLE_FAIL.getMessage()));
        }
        return jsonStr;
    }
}
