package cn.xiami.web.controller;

import cn.xiami.module.Category;
import cn.xiami.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * category的controller类
 */
@Controller("categoryController")
public class CategoryController {

    @Resource(name = "categoryService")
    CategoryService cs;

    /**
     * 查出所有的category标签，和其中一个category标签下的cinfo
     * 跳转进入cateList.jsp
     */
    @RequestMapping(value = "/category/toCateList")
    public String toCateList(Model model, @RequestParam("cateId") int cateId){

        List<Category> remenList =  cs.selectCate(1);
        List<Category> teseList = cs.selectCate(2);
        //根据歌种类的Id查出对应的歌单
        Category category = cs.selectOne(cateId);
        model.addAttribute("remenList",remenList);
        model.addAttribute("teseList",teseList);
        model.addAttribute("category",category);
        return "cateList";

    }

    /**
     * 查询所有的category标签
     * 跳转进入createList.jsp
     */
    @RequestMapping(value = "/category/toCreateCinfo")
    public String toCreateList(Model model){
        List<Category> list = cs.selectAllCate();
        model.addAttribute("cateList",list);
        return "createList";
    }


}
