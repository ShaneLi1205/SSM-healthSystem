package com.lxh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxh.bean.Constant;
import com.lxh.bean.ResultInfo;
import com.lxh.bean.StatusCode;
import com.lxh.pojo.Admin;
import com.lxh.service.AdminService;
import com.lxh.service.ArticleService;
import com.lxh.service.ReportService;
import com.lxh.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: LXH
 * @Date: 2021/7/21 9:28
 */
@RestController
public class HighAuthorityController {

    private AdminService adminService;
    private ArticleService articleService;
    private ReportService reportService;
    @Autowired
    private WorkerService workerService;
    public HighAuthorityController(AdminService adminService, ArticleService articleService, ReportService reportService) {
        this.adminService = adminService;
        this.articleService = articleService;
        this.reportService = reportService;
    }

    @RequestMapping("/updateArticleClass")
    public String updateArticleClass(int originalClassId,int updateClassId){
        ResultInfo<Object> resultInfo;
        Map<String,Integer> map = new HashMap<>(2);
        map.put(Constant.ORIGINAL_CLASS_ID,originalClassId);
        map.put(Constant.UPDATE_CLASS_ID,updateClassId);
        if (articleService.updateArticleClass(map)){
            resultInfo = new ResultInfo(true,StatusCode.ACTION_SUCCESS.getMessage());
        } else {
            resultInfo = new ResultInfo(false,StatusCode.ACTION_FAIL.getMessage());
        }
        return JSON.toJSONString(resultInfo);
    }
    @RequestMapping("/deleteArticleClass")
    public String deleteArticleClass(int articleClassId){
        ResultInfo<Object> resultInfo;

        if (articleService.deleteArticleClass(articleClassId)){
            resultInfo = new ResultInfo(true,StatusCode.DELETE_SUCCESS.getMessage());
        } else {
            resultInfo = new ResultInfo(false,StatusCode.DELETE_FAIL.getMessage());
        }
        return JSON.toJSONString(resultInfo);
    }
    @RequestMapping("/saveArticleClass")
    public String saveArticleClass(String articleClassName){
        ResultInfo<Object> resultInfo;
        if (articleService.saveArticleClass(articleClassName)){
            resultInfo = new ResultInfo(true,StatusCode.SAVE_DATA_SUCCESS.getMessage());
        } else {
            resultInfo = new ResultInfo(false,StatusCode.SAVE_DATA_FAIL.getMessage());
        }
        return JSON.toJSONString(resultInfo);
    }
    @RequestMapping("/checkArticleClassName")
    public String checkArticleClassName(String articleClassName){
        ResultInfo<Object> resultInfo;
        if (articleService.checkArticleClassName(articleClassName)){
            resultInfo = new ResultInfo(true,StatusCode.ARTICLE_CLASS_NAME_EXIST.getMessage());
        } else {
            resultInfo = new ResultInfo(false,StatusCode.ARTICLE_CLASS_NAME_AVAILABLE.getMessage());
        }
        return JSON.toJSONString(resultInfo);
    }
    @RequestMapping("/listReport")
    public String listReport(){
        return JSON.toJSONString(reportService.listAllReport());
    }
    @RequestMapping("/saveBanWorker")
    public String saveBanWorker(int workerId,int banDays){
        ResultInfo<Object> resultInfo;
        Map<String,Integer> map = new HashMap<>(2);
        map.put(Constant.WORKER_ID,workerId);
        map.put(Constant.BAN_DAYS,banDays);
        System.out.println(workerId+"================ "+banDays);
        if (workerService.saveWorkerBanTime(map)){
            resultInfo = new ResultInfo(true,StatusCode.BAN_SUCCESS.getMessage());
        } else {
            resultInfo = new ResultInfo(false,StatusCode.BAN_FAIL.getMessage());
        }
        return JSON.toJSONString(resultInfo);
    }
    @RequestMapping("/deleteReport")
    public String deleteReport(int reportId){
        ResultInfo<Object> resultInfo;
        if (reportService.deleteReport(reportId)){
            resultInfo = new ResultInfo(true,StatusCode.DELETE_SUCCESS.getMessage());
        } else {
            resultInfo = new ResultInfo(false,StatusCode.DELETE_FAIL.getMessage());
        }
        return JSON.toJSONString(resultInfo);
    }
}
