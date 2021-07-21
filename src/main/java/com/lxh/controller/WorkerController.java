package com.lxh.controller;

import com.alibaba.fastjson.JSONObject;
import com.lxh.bean.Constant;
import com.lxh.bean.ResultInfo;
import com.lxh.bean.StatusCode;
import com.lxh.pojo.User;
import com.lxh.pojo.Worker;
import com.lxh.service.WorkerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: LXH
 * @Date: 2021/7/21 9:28
 */
@RestController
public class WorkerController {

    private WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @RequestMapping("/workerLogin")
    public String workerLogin(Worker worker, HttpServletRequest request){
        Worker workerInfo = workerService.getWorker(worker);
        ResultInfo<Object> resultInfo;
        if (workerInfo != null){
            request.getSession().setAttribute(Constant.WORKER_SESSION, request.getSession().getId());
            request.getSession().setAttribute(Constant.WORKER_OBJ, workerInfo);
            resultInfo = new ResultInfo<>(true, StatusCode.LOGIN_SUCCESS.getMessage());
        } else {
            resultInfo = new ResultInfo<>(false,StatusCode.LOGIN_FAIL.getMessage());
        }
        return JSONObject.toJSONString(resultInfo);
    }

}
