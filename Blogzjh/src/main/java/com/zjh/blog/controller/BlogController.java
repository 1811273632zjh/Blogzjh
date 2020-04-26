package com.zjh.blog.controller;

import com.zjh.blog.commons.AddressUtils;
import com.zjh.blog.commons.PVFinalCount;
import com.zjh.blog.commons.StringUtil;
import com.zjh.blog.commons.TreeMapComparatorForkinds;
import com.zjh.blog.domain.Blog;
import com.zjh.blog.domain.BlogType;
import com.zjh.blog.domain.Blogger;
import com.zjh.blog.lucene.BlogIndex;
import com.zjh.blog.service.BlogService;
import com.zjh.blog.service.BlogTypeService;
import com.zjh.blog.service.BloggerService;
import com.zjh.blog.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.*;

/**
 * @Auther：zjh
 * @Description：
 * @Data：2020/4/2 15:43
 * Version 1.0
 */
@Controller
@RequestMapping("/blog")
public class BlogController {

    private static final Logger log = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogIndex blogIndex;
    @Autowired
    private BloggerService bloggerService;
    @Autowired
    private BlogTypeService blogTypeService;


    public ModelAndView details(@PathVariable("id") Integer id, HttpServletRequest request){
        PVFinalCount.Count.incrementAndGet();

        ModelAndView modelAndView = new ModelAndView();
        //获取id
        Blog blog = blogService.getById(id);
        if (blog == null){
            modelAndView .setViewName("error/404");
            return modelAndView;
        }
        blog.setReleaseDateStr(new SimpleDateFormat("YYYY/MM/dd HH:MM:SS").format(blog.getReleasedate()));
        //获取关键字
        String keyWords = blog.getKeyword();
        if (StringUtil.isNotEmpty(keyWords)){
            String[] StrArray = keyWords.split(" ");
            List<String> keyWordList = StringUtil.filterWhite(Arrays.asList(StrArray));
            modelAndView.addObject("blogKeys", keyWordList);
        } else {
            modelAndView.addObject("blogKeys",null);
        }
        //修改图片大小
        ServletContext application = RequestContextUtils.findWebApplicationContext(request).getServletContext();
        Map<Integer, Long> hashMap = new HashMap<>();
        Map<String, Map<Integer, Long>> userMap = null;
        String ip = AddressUtils.getRealIp(request);
        synchronized (this){
            if (application.getAttribute("userMap") != null){
                //得到userMap
                userMap = (Map<String, Map<Integer, Long>>) application.getAttribute("userMap");
                //userMap中没有ip，此时一个关系
                if (userMap.get(ip) == null || "".equals(userMap.get(ip))){
                    //把ip设置进去
                    hashMap.put(blog.getId(), System.currentTimeMillis());
                    //把时间设置进去
                    userMap.put(ip, hashMap);
                    application.setAttribute("userMap",userMap);
                    blog.setClickhit(blog.getClickhit() + 1);
                    //更新博客数据到数据库
                    blogService.updateBlog(blog);
                    log.info("访客：" + ip + "第1次阅读文章");
                } else {
                    //此时可能有多个ip于blogId有关系，需要遍历 UserMap
                    int i = userMap.size();
                    while (i > 0){
                        //遍历map
                        if (userMap.containsKey(ip)){   //如果存在ip
                            //取该ip下的blogId的时间
                            Map<Integer, Long> hashMap2 = userMap.get(ip);
                            //遍历blog 与 当前 blog 是否一致
                            int j = hashMap2.size();
                            while (j > 0){
                                //如果是同一个ip
                                if (hashMap2.containsKey(blog.getId())){
                                    //获取该id对于的时间进行修改
                                    long time = hashMap2.get(blog.getId());     //获取上次访问的时间
                                    if (System.currentTimeMillis() - time >= 1000 * 60 * 60 * 24){
                                        //已经过期，可以继续 +1
                                        log.info("访客：" + ip + "非第一次阅读该文章，但阅读时间 已 超过24小时！！");
                                        blog.setClickhit(blog.getClickhit() + 1);
                                        blogService.updateBlog(blog);
                                        //重新覆盖该文章的时间
                                        hashMap2.put(blog.getId(), System.currentTimeMillis());
                                        //修改全局map
                                        userMap.put(ip, hashMap2);
                                        application.setAttribute("userMap", userMap);
                                    } else {
                                        //阅读时间没有超过一天
                                        log.info("访客：" + ip + "非第一次阅读该文章，但阅读时间 未 超过24小时！！");
                                        //但是时间需要修改
                                        hashMap2.put(blog.getId(), System.currentTimeMillis());
                                        userMap.put(ip, hashMap2);
                                        application.setAttribute("userMap", userMap);
                                    }
                                } else {
                                    //不是同一篇文章，添加
                                    hashMap2.put(blog.getId(), System.currentTimeMillis());
                                    userMap.put(ip, hashMap2);
                                    application.setAttribute("userMap", userMap);
                                    blog.setClickhit(blog.getClickhit() + 1);
                                    blogService.updateBlog(blog);
                                }
                                j--;
                            }
                        } else {
                            //第一次用户
                            hashMap.put(blog.getId(), System.currentTimeMillis());
                            userMap.put(ip,hashMap);
                            application.setAttribute("userMap",userMap);
                            blog.setClickhit(blog.getClickhit() + 1);
                            blogService.updateBlog(blog);
                        }
                        i--;
                    }
                }
            }
        }

        modelAndView.addObject("blog", blog);

/*	List<Comment> commentList = commentService.queryCommentsByBlogId(blog
				.getId());
		for (Comment message : commentList) {
			message.setAddress(message.getAddress() + "网友");
		}*/
        Blogger blogger = bloggerService.getBloggerData();
        Map<String, Object> mapForComment = new HashMap<String, Object>();
        List<Blog> bloglist = blogService.listBlog(mapForComment);// 所有博客数据
        //Collections.sort(bloglist);// 以评论量排序，取出state=1的评论和博客文章比较。
        Map<String, Integer> m = new IdentityHashMap<String, Integer>();
        for (Blog b : bloglist) {
            String[] strings = b.getKeyword().split(" ");
            for (String string : strings) {
                m.put(string, b.getId());// 保存所有id-关键字 可重复。
            }
        }
        if (m.size() > 20) {
            // Set<Integer> mapForrandom=
            // ProdRandom.getRandom(m.size());//生成随机数的范围0~m.size()
            // Iterator<Entry<String, Integer>> entries =
            // m.entrySet().iterator(); //得到所有标签
            // while (entries.hasNext()) {
            // Entry<String, Integer> entry = entries.next();
            //
            // }

        }
        // 大于1的时候，才可以比较
        TreeMapComparatorForkinds tForkinds = null;
        TreeMap<String, Object> sorted_map = null;
        if (m.size() > 1) {
            tForkinds = new TreeMapComparatorForkinds(m);
            sorted_map = new TreeMap<String, Object>(tForkinds);// 使用自己实现的比较器来构造treeMap
            sorted_map.putAll(m);
            modelAndView.addObject("keysMap", sorted_map);
        } else {
            modelAndView.addObject("keysMap", m); // 只有一个关键字不需要排序。
        }
        List<Blog> blogList = new ArrayList<Blog>();
        // 选出评论量最高的10篇文章
        if (bloglist.size() > 10) {
            for (int i = 0; i < 10; i++) {
                Blog blogTemp = bloglist.get(i);
                blogList.add(blogTemp);
            }
            modelAndView.addObject("blogList", blogList);

        } else {
            modelAndView.addObject("blogList", bloglist); // 评论排行 应该只需要state==1
        }
        List<BlogType> blogTypeList = blogTypeService.getBlogTypeData();
        /*modelAndView.addObject("commentList", commentList);*/
        modelAndView.addObject("blogger", blogger);
        modelAndView.addObject("blogTypeList", blogTypeList);
        modelAndView.setViewName("indexViews/detail");
        return modelAndView;

    }
}