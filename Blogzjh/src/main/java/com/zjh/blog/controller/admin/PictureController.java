package com.zjh.blog.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjh.blog.domain.PageBean;
import com.zjh.blog.domain.Picture;
import com.zjh.blog.service.GreatService;
import com.zjh.blog.service.PictureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther：zjh
 * @Description：
 * @Data：2020/2/27 11:34
 * Version 1.0
 */
@RestController
@RequestMapping("/admin/picture")
public class PictureController {

    private static final Logger log = LoggerFactory.getLogger(PictureController.class);

    @Autowired
    private PictureService pictureService;
    @Autowired
    private GreatService greatService;

    /**
      * @Description: 分页查询
      * @Param: page limit
      * @return:
      */
    @RequestMapping("/list")
    public String listNotic(@RequestParam(value = "page",required = false) String page,
                            @RequestParam(value = "limit",required = false) String limit){
        log.info("当前请求照片管理页面。。。");
        //定义分页bean
        PageBean<Picture> pageBean = new PageBean<Picture>(Integer.parseInt(page),Integer.parseInt(limit));
        //使用阿里巴巴的fastJson 创建JSONObject
        String jsonArray = JSON.toJSONString(pageBean.getResult());
        JSONArray array = JSONArray.parseArray(jsonArray);
        //使用阿里巴巴的fastJSON创建JSONObject
        JSONObject result = new JSONObject();
        //将序列话结果放入json中
        result.put("data",array);
        result.put("code",0);
        result.put("count",pageBean.getTotal());

        return result.toJSONString();
    }

    /**
      * @Description: 添加图片
      * @Param: picture
      * @return:
      */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveNotice(Picture picture){
        log.info("当前请求添加图片。。。");
        int resultTotal = 0;    //接收返回结果记录数
        if (picture.getId() == null){   //没有id，添加
            resultTotal = pictureService.addPicture(picture);
        }else {         //有id，修改
            resultTotal = pictureService.updatePicture(picture);
        }
        JSONObject result = new JSONObject();
        if (resultTotal > 0){
            result.put("success",true);
        } else {
            result.put("success",false);
        }
        return result.toJSONString();
    }

    public String deleteNotice(@RequestParam(value = "ids",required = false) String ids){
        log.info("当前请求删除图片。。。");
        //分割字符串得到id数组
        String[] idsStr = ids.split(",");
        JSONObject result = new JSONObject();
        int j = 0;
        for (int i = 0; i < idsStr.length; i++){
            int id = Integer.parseInt(idsStr[i]);
            //如果删除的id有点击量的话，则使用imageID将对应所有的great关系删除
            if (pictureService.getPictureByid(id).getClick() > 0){
                greatService.deleteByImageId(id);
            }
            pictureService.deletePicture(id);
            j++;
        }
        //全部删除成功
        if (j == idsStr.length){
            result.put("success",true);
        } else {
            result.put("success",false);
        }

        return result.toJSONString();
    }
}
