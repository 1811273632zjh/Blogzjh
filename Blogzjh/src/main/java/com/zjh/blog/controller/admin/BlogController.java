package com.zjh.blog.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zjh.blog.domain.Blog;
import com.zjh.blog.domain.PageBean;
import com.zjh.blog.lucene.BlogIndex;
import com.zjh.blog.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

/**
 * @Auther：zjh
 * @Data：2019/11/6 9:38
 * Version 1.0
 */
@RestController
@RequestMapping("/admin/blog")
public class BlogController {

    private static final Logger log = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogIndex blogIndex;

    @RequestMapping("/getById")
    public String getById(String id){
        Blog blog = blogService.getById(Integer.parseInt(id));
        String jsonStr = JSONObject.toJSONString(blog);
        JSONObject result = JSONObject.parseObject(jsonStr);
        result.put("success",true);
        return result.toJSONString();
    }

    @RequestMapping("/save")
    public String saveBlog(Blog blog) throws IOException {
        log.info("当前请求保存/修改操作。。。");
        int resultTotal = 0;
        //判断id 是否为空，不为空，进行能修改，为空，新增
        if (blog.getId() != null) {
            Date time = new Date();
            blog.setReleasedate(time);
            //更新
            resultTotal = blogService.updateBlog(blog);
            //更新索引
            blogIndex.updateIndex(blog);
        } else {
            //新增
            resultTotal = blogService.saveBlog(blog);
            //新增索引
            blogIndex.addIndex(blog);
        }
        JSONObject result = new JSONObject();
        if (resultTotal > 0){
            result.put("success",true);
        }else {
            result.put("success",false);
        }
        return result.toJSONString();
    }

    @RequestMapping("/delete")
    public String deleteBlog(String ids) throws  IOException{
        log.info("当前请求删除操作。。");
        String[] idsStr = ids.split(",");
        for (int i = 0;i < idsStr.length; i++){
            int id = Integer.parseInt(idsStr[i]);
            //先删除博客所关联的评论，删除博客

            blogService.deleteBlog(id);
            //删除索引
            blogIndex.deleteIndex(String.valueOf(id));

        }
        JSONObject result = new JSONObject();
        result.put("success",true);
        return result.toJSONString();
    }

    @RequestMapping("/listBlog")
    public String listBlog(String page,String limit,String title,Blog s_blog){
        log.info("当前请求分页博客。。。");
        PageBean<Blog> pageBean = null;
        //创建JSON 对象
        JSONObject result = new JSONObject();
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        String jsonStr = null;
        //处理title 作为一个参数进行的查询
        if(title != null && !"".equals(title)){
            //根据标题模糊查询
            pageBean = new PageBean<Blog>(Integer.parseInt(page),Integer.parseInt(limit));  //构建分页bean
            pageBean = blogService.listBlog(title,pageBean);        //根据分页条件进行查询
            if (pageBean.getResult() != null){                      //查询到有关标题 数据
                jsonStr = JSONObject.toJSONString(pageBean.getResult(),
                        SerializerFeature.DisableCircularReferenceDetect,
                        SerializerFeature.WriteDateUseDateFormat);
            }else {
                result.put("count",0);      //无数据
                result.put("code",0);       //封装接口，成功返回0
                return result.toJSONString();
            }
        }else {     //title 为空，正常分页
            pageBean = new PageBean<Blog>(Integer.parseInt(page),Integer.parseInt(limit));
            pageBean = blogService.listBlog(s_blog.getTitle(), pageBean);
            //禁止对象循环引用
            jsonStr = JSONObject.toJSONString(pageBean.getResult(),
                    SerializerFeature.DisableCircularReferenceDetect,
                    SerializerFeature.WriteDateUseDateFormat);
        }
        //把json 字符串转换成得到json 数组
        JSONArray array = JSON.parseArray(jsonStr);
        result.put("data",array);
        result.put("count",array);
        result.put("code",0);       //封装接口，成功返回0

        //返回一个 Blog
        return result.toJSONString();
    }

}
