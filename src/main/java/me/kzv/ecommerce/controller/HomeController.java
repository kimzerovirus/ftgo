package me.kzv.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import me.kzv.ecommerce.domain.entity.Category;
import me.kzv.ecommerce.service.CategoryService;
import me.kzv.ecommerce.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final CategoryService categoryService;
    private final ItemService itemService;

    @GetMapping("/")
    public String home(Model model){
        List<Category> categoryList = categoryService.getCategoryListByVisible();
        model.addAttribute("categoryList", categoryList);

        return "index";
    }
}
