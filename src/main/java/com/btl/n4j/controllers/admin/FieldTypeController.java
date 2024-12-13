package com.btl.n4j.controllers.admin;

import com.btl.n4j.models.Category;
import com.btl.n4j.models.FieldType;
import com.btl.n4j.services.CategoryService;
import com.btl.n4j.services.FieldTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

@RequestMapping("/admin")
public class FieldTypeController {
    @Autowired
    private FieldTypeService fieldTypeService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/fieldtype")
    public String index(Model model) {
        List<FieldType> fieldTypeList = this.fieldTypeService.getAll();
        model.addAttribute("fieldTypeList", fieldTypeList);
        return "admin/fieldtype/index";
    }

    @GetMapping("/add-fieldtype")
    public String add(Model model) {
        FieldType fieldType = new FieldType();
        model.addAttribute("fieldType", fieldType);

        List<Category> categoryList = this.categoryService.getAll();

        model.addAttribute("categoryList", categoryList);
        return "admin/fieldtype/add";
    }

    @PostMapping("/add-fieldtype")
    public String save(@ModelAttribute("fieldType") FieldType fieldType) {
        if (this.fieldTypeService.createNew(fieldType)) {
            return "redirect:/admin/fieldtype";
        } else {
            return "admin/fieldtype/add";
        }
    }

    @GetMapping("/edit-fieldtype/{fieldTypeId}")
    public String edit(Model model, @PathVariable("fieldTypeId") Integer fieldTypeId) {
        FieldType fieldType = this.fieldTypeService.findByID(fieldTypeId);
        model.addAttribute("fieldType", fieldType);

        List<Category> categoryList = this.categoryService.getAll();

        model.addAttribute("categoryList", categoryList);
        return "admin/fieldtype/edit";
    }

    @PostMapping("/edit-fieldtype")
    public String update(@ModelAttribute("fieldType") FieldType fieldType) {
        if (this.fieldTypeService.update(fieldType)) {
            return "redirect:/admin/fieldtype";
        } else {
            return "admin/fieldtype/edit";
        }
    }

    @GetMapping("/delete-fieldtype/{fieldTypeId}")
    public String delete(@PathVariable("fieldTypeId") Integer fieldTypeId) {
        if (this.fieldTypeService.delete(fieldTypeId)) {
            return "redirect:/admin/fieldtype";
        } else {
            return "redirect:/admin/fieldtype";
        }
    }
}
