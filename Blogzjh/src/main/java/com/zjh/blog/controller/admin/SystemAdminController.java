package com.zjh.blog.controller.admin;

import com.zjh.blog.lucene.BlogIndex;
import com.zjh.blog.service.BlogService;
import com.zjh.blog.service.BlogTypeService;
import com.zjh.blog.service.BloggerService;
import com.zjh.blog.service.LinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther：zjh
 * @Description：管理员Controller层
 * @Data：2020/1/17 11:14
 * Version 1.0
 */
@RestController
@RequestMapping("/admin/system")
public class SystemAdminController {

    private static final Logger log = LoggerFactory.getLogger(SystemAdminController.class);

    @Autowired
    private BloggerService bloggerService;
    @Autowired
    private BlogTypeService blogTypeService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private LinkService linkService;
    @Autowired
    private BlogIndex blogIndex;


}
