package com.zjh.blog.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.zjh.blog.domain.Link;
import com.zjh.blog.service.LinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther：zjh
 * @Description：友链控制层
 * @Data：2019/12/4 17:18
 * Version 1.0
 */
@RestController
@RequestMapping(value = "/admin/link")
public class LinkController {

    private static final Logger log = LoggerFactory.getLogger(LinkController.class);

    @Autowired
    private LinkService linkService;

    /**
      * @Description: 新增或修改操作
      * @Param: link
      * @return: json
      */
    @ResponseBody
    @RequestMapping(value = "/save")
    public String saveLink(Link link){
        log.info("当前请求更新友链。。。");

        int totalCount = 0;
        if(link.getId() != null){
            //修改操作
            totalCount = linkService.updateLink(link);
        }else{
            //新增操作
            totalCount = linkService.addLink(link);
        }
        //返回JSON数据
        JSONObject result = new JSONObject();
        if(totalCount > 0){
            result.put("success",true);
        }else{
            result.put("success",false);
        }
        return result.toJSONString();
    }

    /**
      * @Description: 删除友链操作
      * @Param: ids
      * @return: json
      */
    @ResponseBody
    @RequestMapping(value = "/delete")
    public String deleteLink(String ids){
        log.info("当前请求删除友链操作。。。");
        //将ajax提交过来进行处理，得到所需要的id，进行循环删除
        String[] idArray = ids.split(",");
        for (int i = 0;i < idArray.length; i++){
            linkService.deleteLink(Integer.parseInt(idArray[i]));
        }
        //返回JSON结果
        JSONObject result = new JSONObject();
        result.put("success",true);
        return result.toJSONString();

    }


}
