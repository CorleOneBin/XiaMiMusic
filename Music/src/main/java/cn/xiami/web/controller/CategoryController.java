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


}
