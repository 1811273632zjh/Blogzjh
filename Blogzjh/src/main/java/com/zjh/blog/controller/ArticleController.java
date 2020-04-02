package com.zjh.blog.controller;

import com.zjh.blog.commons.PVFinalCount;
import com.zjh.blog.commons.StringUtil;
import com.zjh.blog.domain.Blog;
import com.zjh.blog.domain.Blogger;
import com.zjh.blog.domain.Link;
import com.zjh.blog.domain.PageBean;
import com.zjh.blog.service.BlogService;
import com.zjh.blog.service.BlogTypeService;
import com.zjh.blog.service.BloggerService;
import com.zjh.blog.service.LinkService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;


import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * @Auther：zjh
 * @Description：
 * @Data：2020/4/1 9:08
 * Version 1.0
 */
@Controller
@RequestMapping("/index")
public class ArticleController {

    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private BlogService blogService;
    @Autowired
    private BloggerService bloggerService;
    @Autowired
    private LinkService linkService;
    @Autowired
    private BlogTypeService blogTypeService;

    public String index(@RequestParam(value = "page", required = false) String page,
                        @RequestParam(value = "typeId", required = false) String typeId,
                        @RequestParam(value = "releaseDateStr", required = false) String releaseDateStr,
                        HttpServletRequest request){

        PVFinalCount.Count.incrementAndGet();
        if (StringUtil.isEmpty(page)){
            page = "1";
        }
        //获取分页bean (每页显示10条数据)
        PageBean<Blog> pageBean = new PageBean<Blog>(Integer.parseInt(page),10);
        //map中封装起始页和每页的记录，按条件分类
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("start",pageBean.getStart());
        map.put("end", pageBean.getEnd());
        map.put("typeId", typeId);
        map.put("releaseDateStr", releaseDateStr);
        map.put("orderBy", "releaseDate");
        //获取博客信息
        List<Blog> blogList = blogService.listBlog(map);
        if (releaseDateStr != null){
            Map<String, Object> map2 = new HashMap<>();
            map2.put("releaseDateStr", releaseDateStr);
            //提供给前台的总记录数
            pageBean.setTotal(blogService.listBlog(map2).size());
        } else if (typeId != null){
            Map<String, Object> map3 = new HashMap<>();
            map3.put("typeId", typeId);
            pageBean.setTotal(blogService.listBlog(map3).size());
        } else {
            pageBean.setTotal(blogService.listBlog(new HashMap<String, Object>()).size());
        }
        pageBean.setResult(blogList);
        for (Blog blog : blogList) {
            List<String> imageList = blog.getImageList();
            //获取博客内容
            String blogInfo = blog.getContent();
            //将内容（网页中的某些hml）转为jsoup的Document
            Document doc = Jsoup.parse(blogInfo);
            //获取<img>标签中所有后缀为.jpg的元素
            Elements jpgs = doc.select("img[src~=(jpg|jpeg|bmp|png)$]");
            for (int i = 0; i < jpgs.size(); i++) {
                Element jpg = jpgs.get(i);
                imageList.add(jpg.toString().replace(">", " style='width: 184px;htight: 97px;'>"));
                if (i == 2) {
                    break;  //只存3张图片的信息
                }
            }
        }
        log.info("当前请求文章专栏,ip:" + request.getRemoteAddr());

        //分页
        List<Link> LinkedList = linkService.getTotalData();
        Blogger blogger = bloggerService.getBloggerData();
        ServletContext application = RequestContextUtils.findWebApplicationContext(request).getServletContext();
        Map<String,Object> readMap = new HashMap<>();
        //设置排序方式，设置方式为=${}
        readMap.put("orderBy", "clickHit");
        List<Blog> blogList2 = blogService.listBlog(readMap);
        List<Blog> blogCountList = blogService.countList();
        application.setAttribute("bloglist2",blogList2);
        application.setAttribute("blogger",blogger);
        application.setAttribute("linkList", LinkedList);
        application.setAttribute("blogCountList",blogCountList);
        application.setAttribute("pageBean",pageBean);

        return "indexViews/article";

    }
}
