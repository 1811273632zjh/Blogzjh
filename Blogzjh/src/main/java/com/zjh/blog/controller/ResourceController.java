package com.zjh.blog.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zjh.blog.commons.AddressUtils;
import com.zjh.blog.commons.PVFinalCount;
import com.zjh.blog.domain.Great;
import com.zjh.blog.domain.PageBean;
import com.zjh.blog.domain.Picture;
import com.zjh.blog.service.GreatService;
import com.zjh.blog.service.PictureService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther：zjh
 * @Description：前台图片控制器
 * @Data：2020/4/25 17:25
 * Version 1.0
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    private static final Logger log = LoggerFactory.getLogger(RestController.class);

    @Autowired
    private PictureService pictureService;
    @Autowired
    private GreatService greatService;

    @RequestMapping(value = "/list")
    public String listMessage(@RequestParam(value = "page", required = false) String page,
                              HttpServletRequest request) {
        PVFinalCount.Count.incrementAndGet();
        log.info("当前请求相册。。。。懒加载方法");
        PageBean<Picture> pageBean = new PageBean<Picture>(Integer.parseInt(page), 10);
        System.out.println("start:" + pageBean.getStart() + "，end:" + pageBean.getEnd());
        JSONObject result = new JSONObject();
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        String jsonStr = JSONObject.toJSONString(pageBean.getResult(), SerializerFeature.WriteDateUseDateFormat,SerializerFeature.DisableCircularReferenceDetect);
        JSONArray jsonArray = JSON.parseArray(jsonStr);
        result.put("count", pageBean.getCount());
        result.put("total", pageBean.getTotal());
        result.put("currentPage", pageBean.getCurrPage());
        result.put("data", jsonArray);
        result.put("code", 0);// 封装接口，成功返回0
        String ip = AddressUtils.getRealIp(request); // 得到访问这个页面的用户的ip保存
        ServletContext application = RequestContextUtils
                .findWebApplicationContext(request)
                .getServletContext();
        application.setAttribute("ipAddress", ip);// 记住访问过相册的任何用户的ip信息
        return result.toJSONString();
    }

    @RequestMapping(value = "/isClick")
    public String isClickOver(
            @RequestParam(value = "userIp", required = true) String userIp,
            @RequestParam(value = "imageId", required = true) Integer imageId,
            HttpServletRequest httpServletRequest) {
        Great greatTest = greatService.isClick(userIp, imageId);
        Integer idClick = null;
        String idUrl = null;
        JSONObject result = new JSONObject();
        if (greatTest != null) { // 点赞失败
            synchronized (this) {
                greatService.delGreat(greatTest.getId());// 如果找到了就删除，表示取消点赞
                Picture picture = pictureService.getPictureByid(imageId);
                idClick = picture.getClick() - 1;
                idUrl = Integer.valueOf(picture.getId()) + "key";
                picture.setClick(idClick);
                pictureService.updatePicture(picture); // 更新图片
                log.info("取消赞了：" + picture.toString());
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("idClick", idClick);
            map.put("idUrl", idUrl);
            result.put("data", map);
            result.put("success", false);
            return result.toString();
        } else { // 点赞成功
            Great great = new Great();
            great.setImageId(imageId);
            great.setUserIp(userIp);
            synchronized (this) {
                greatService.saveGreat(great); // 加入一条新的数据 将图片的次数加一
                Picture picture = pictureService.getPictureByid(imageId);
                idClick = picture.getClick() + 1;
                idUrl = Integer.valueOf(picture.getId()) + "key";
                picture.setClick(idClick);
                pictureService.updatePicture(picture);
                log.info("点赞：" + picture.toString());
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("idClick", idClick);
            map.put("idUrl", idUrl);
            result.put("data", map);
            result.put("success", true);
            return result.toString();
        }

    }


}
