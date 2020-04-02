package com.zjh.blog.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjh.blog.domain.Notice;
import com.zjh.blog.domain.PageBean;
import com.zjh.blog.service.NoticeService;
import net.sf.ehcache.search.expression.Not;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Auther：zjh
 * @Description：公告管理
 * @Data：2020/3/18 17:31
 * Version 1.0
 */
@RestController
@RequestMapping("/admin/notice")
public class NoticeController {

    private static final Logger log = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    private NoticeService noticeService;

    /**
      * @Description: 公告分页
      * @Param: page、limit
      * @return: JSONObject
      */
    @RequestMapping("/list")
    public Object listNotice(@RequestParam(value = "page",required = false) String page,
                             @RequestParam(value = "limit",required = false) String limit){
        log.info("当前请求公告管理页面。。。");
        //该处没有使用pageBean所用的（start，end）
        //创建分页bean
        PageBean<Notice> pageBean = new PageBean<Notice>(Integer.parseInt(page), Integer.parseInt(limit));
        pageBean = noticeService.listNotice(pageBean);
        String jsonArray = JSON.toJSONString(pageBean.getResult());
        JSONArray array = JSONArray.parseArray(jsonArray);
        JSONObject result = new JSONObject();

        //将序列化结果放入json对象中
        result.put("data",array);
        result.put("code",0);
        result.put("count",pageBean.getTotal());

        return result;
    }

    /**
      * @Description:  添加公告
      * @Param: notice
      * @return:
      */
    public String saveNotice(Notice notice){
        log.info("当前请求公告添加页面。。。");
        int resultTotal = 0;    //接受返回结果为空
        if (notice.getId() == null){ //如果查不到id，则新增
            resultTotal = noticeService.saveNotice(notice);
        } else {        //否则进行修改
            resultTotal = noticeService.updateNotice(notice);
        }
        JSONObject result = new JSONObject();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result.toJSONString();
    }

}
