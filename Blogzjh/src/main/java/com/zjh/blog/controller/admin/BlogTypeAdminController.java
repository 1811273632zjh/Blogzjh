package com.zjh.blog.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.zjh.blog.domain.BlogType;
import com.zjh.blog.service.BlogService;
import com.zjh.blog.service.BlogTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther：zjh
 * @Data：2019/11/4 23:35
 * Version 1.0
 */

@RestController
@RequestMapping(value = "/admin/blogType")
public class BlogTypeAdminController {

    private static final Logger log = LoggerFactory.getLogger(BlogTypeAdminController.class);

    @Autowired
    private BlogTypeService blogTypeService;
    @Autowired
    private BlogService blogService;

    //根据id 查询博客类别   测试
    @RequestMapping("/selectById")
    public List<BlogType> selectById(Integer id, Model model){
        //return blogTypeService.selectById(id);
        return null;
    }

    //新增 和 修改 博客类别
    @RequestMapping("/save")
    public String save(BlogType blogType, HttpServletRequest httpServletRequest){
        log.info("当前更新博客类别");
        System.out.println("type_name:"+blogType.getTypeName()+"，order_num:"+blogType.getOrderNum());
        int resultTotal = 0;
        if(blogType.getId() == null){
            resultTotal = blogTypeService.addBlogType(blogType);
        }else {
            resultTotal = blogTypeService.updateBlogType(blogType);
        }
        JSONObject result = new JSONObject();
        if(resultTotal > 0){
            result.put("success",true);
        }else {
            result.put("success",false);
        }
        //刷新application
        ServletContext application = httpServletRequest.getServletContext();
        application.removeAttribute("blogTypeList");
        return result.toJSONString();
    }

    //根据id删除博客类别
    @RequestMapping("/delete")
    public String delete(String ids,HttpServletRequest httpServletRequest){
        log.info("当前请求删除博客类型");
        String[] idsStr = ids.split(",");
        JSONObject result = new JSONObject();
        for (int i = 0; i < idsStr.length; i++){
            int id = Integer.parseInt(idsStr[i]);
            //判断博客
            blogTypeService.deleteBlogType(id);
        }
        // 返回一个json对象，带有一个boolean值，写入输出流response中
        result.put("success",0);
        ServletContext application = httpServletRequest.getServletContext();
        // 清空ServletContext，触发ServletContextAttributeListener
        application.removeAttribute("blogTypeList");
        return result.toJSONString();
    }
}
