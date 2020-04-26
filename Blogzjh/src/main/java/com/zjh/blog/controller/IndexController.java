package com.zjh.blog.controller;

import com.zjh.blog.commons.AddressUtils;
import com.zjh.blog.commons.PVFinalCount;
import com.zjh.blog.commons.StringUtil;
import com.zjh.blog.domain.*;
import com.zjh.blog.service.*;
import org.apache.shiro.SecurityUtils;
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

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther：zjh
 * @Description 主页Controller
 * @Data：2019/11/4 23:34
 * Version 1.0
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private BlogService blogService;
    @Autowired
    private BloggerService bloggerService;
    @Autowired
    private LinkService linkService;
    @Autowired
    private BlogTypeService blogTypeService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private PictureService pictureService;

    @RequestMapping("/home.html")
    public String index(@RequestParam(value = "releaseDateStr", required = false) String releaseDateStr,
                        @RequestParam(value = "page", required = false) String page,
                        @RequestParam(value = "typeId", required = false) String typeId,
                        HttpServletRequest request){
        PVFinalCount.Count.incrementAndGet();

        ServletContext application = RequestContextUtils.findWebApplicationContext(request).getServletContext();
        if (StringUtil.isEmpty(page)){
            page = "1";
        }
        if (request.getParameter("flag") == null){
            application.setAttribute("flag","no");
            if (application.getAttribute("type") != null){
                application.removeAttribute("releaseDateStr");
                application.removeAttribute("type");
            }
        }
        log.info("当前请求index主页。。。。");
        //获取分页bean
        PageBean<Blog> pageBean = new PageBean<Blog>(Integer.parseInt(page),10);

        //map中封装起始页和每页的记录，按条件分类
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", pageBean.getStart());
        map.put("end", pageBean.getEnd());
        map.put("typeId", typeId);
        map.put("releaseDataStr", releaseDateStr);
        map.put("orderBy", "releaseDate");
        //获取博客信息
        List<Blog> blogList = blogService.listBlog(map);
        if (releaseDateStr != null){
            Map<String, Object> map2 = new HashMap<>();
            map2.put("releaseDateStr", releaseDateStr);
            //给前台总的记录数
            pageBean.setTotal(blogService.listBlog(map2).size());
        } else if (typeId != null){
            Map<String, Object> map3 = new HashMap<String, Object>();
            map3.put("typeId", typeId);
            //给前台的总记录数
            pageBean.setTotal(blogService.listBlog(map3).size());
        } else {
            pageBean.setTotal(blogService.listBlog(new HashMap<String, Object>()).size());
        }
        pageBean.setResult(blogList);
        for (Blog blog : blogList){
            List<String> imageList = blog.getImageList();
            String blogInfo = blog.getContent();    //获取博客内容
            Document doc = Jsoup.parse(blogInfo);   //将博客内容（网页的html）转为jsoup的Document
            Elements jpgs = doc.select("img[src1=(jpg|jpeg|bmp|png)$]");    //获取<img>标签中所有后缀为jpg格式的元素
            for (int i = 0; i < jpgs.size(); i++){
                Element jpg = jpgs.get(i);  //获取到单个元素
                System.out.println(jpg.toString().replace(">", "style='width:180px;height:50px;'>"));
                //把图片信息存到imageList中
                imageList.add(jpg.toString().replace(">","style='width:184px;hight:97px;'>"));
                if (i == 2){
                    break;  //只存3张图片信息
                }
            }
        }
        //分页
        List<Link> linkList = linkService.getTotalData();
        Blogger blogger = bloggerService.getBloggerData();
        List<Blog> blogCountList = blogService.countList();
        //开始装载公告信息
        List<Notice> list = noticeService.getAllNotices();
        application.setAttribute("blogger",blogger);
        SecurityUtils.getSubject().getSession().setAttribute("blogger",blogger);

        application.setAttribute("linkList",linkList);
        application.setAttribute("blogCountList",blogCountList);    //日期分档博客信息
        application.setAttribute("pageBean",pageBean);              //必须实时刷新
        application.setAttribute("notice",list);
        application.setAttribute("articleCount",blogService.getTotal(new HashMap<String, Object>()));//统计信息
        //文章总数
        Map<String, Object> map3 = new HashMap<>();
        map3.put("state", 1);       //审核
        application.setAttribute("messageList", messageService.getTotal(map3));
        application.setAttribute("noticeCount",list.size());
        application.setAttribute("pictureCount",pictureService.getTotalCount());

        //获取博客总访问量
        Map<String, Object> mapBlogClick = new HashMap<>();
        List<Blog> blogClick = blogService.listBlog(mapBlogClick);
        int blogClickCount = 0;
        for (Blog blog : blogClick){
            blogClickCount += blog.getClickhit();
        }
        application.setAttribute("blogClickCount", blogClickCount);
        application.setAttribute("ip",AddressUtils.getRealIp(request));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();         //创建登录时间
        String str = sdf.format(date);
        application.setAttribute("str", str);
        application.setAttribute("countPV", PVFinalCount.Count.get());
        //保存参数
        if (releaseDateStr != null) {
            application.setAttribute("releaseDateStr", releaseDateStr);
            if (application.getAttribute("typr") != null || application.getAttribute("flag") != null){
                application.removeAttribute("type");
                application.removeAttribute("flag");
            }
        }
        if (typeId != null){
            application.setAttribute("type",typeId);
            if (application.getAttribute("flag") != null || application.getAttribute("flag") != null){
                application.removeAttribute("releaseDateStr");
                application.removeAttribute("flag");
            }
        }
        return "indexViews/home";
    }
}

