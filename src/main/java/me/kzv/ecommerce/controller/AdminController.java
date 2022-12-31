package me.kzv.ecommerce.controller;

import me.kzv.ecommerce.domain.entity.Category;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String adminHome(){
        return "admin/index";
    }

    @GetMapping("/admin/manage/category")
    public String manageCategory(){
        return "admin/manage/category";
    }

    @ResponseBody
    @GetMapping("/api/admin/manage/category")
    public void getAllCategoryList(){

    }

    @PostMapping("/api/admin/manage/category/edit")
    public void editAllCategoryList(@RequestBody List<Category> categoryList){

    }

}
