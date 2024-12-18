package com.btl.n4j.controllers.admin;

import com.btl.n4j.models.Category;
import com.btl.n4j.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public String index(Model model) {
        List<Category> categoryList = this.categoryService.getAll();
        model.addAttribute("categoryList", categoryList);
        return "admin/category/index";
    }

    @GetMapping("/add-category")
    public String add(Model model) {

        Category category = new Category();
        model.addAttribute("category", category);
        return "admin/category/add";
    }

    @PostMapping("/add-category")
    public String save(@ModelAttribute("category") Category category) {

        if (this.categoryService.createNew(category)) {
            return "redirect:/admin/category";
        } else {
            return "admin/category/add";
        }
    }

    @GetMapping("/edit-category/{categoryId}")
    public String edit(Model model, @PathVariable("categoryId") Integer categoryId) {

        Category category = this.categoryService.findByID(categoryId);
        model.addAttribute("category", category);
        return "admin/category/edit";
    }

    @PostMapping("/edit-category")
    public String update(@ModelAttribute("category") Category category) {

        if (this.categoryService.update(category)) {
            return "redirect:/admin/category";
        } else {
            return "admin/category/edit";
        }
    }

    @GetMapping("/delete-category/{categoryId}")
    public String delete(@PathVariable("categoryId") Integer categoryId) {

        if (this.categoryService.delete(categoryId)) {
            return "redirect:/admin/category";
        } else {
            return "redirect:/admin/category";
        }
    }
}
