package com.btl.n4j.controllers.admin;

import com.btl.n4j.models.Field;
import com.btl.n4j.models.FieldType;
import com.btl.n4j.services.FieldService;
import com.btl.n4j.services.FieldTypeService;
import com.btl.n4j.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @Autowired
    private FieldTypeService fieldTypeService;

    @Autowired
    private StorageService storageService;

    @RequestMapping("/field")
    public String index(Model model) {
        List<Field> fieldList = this.fieldService.getAll();
        model.addAttribute("fieldList", fieldList);
        return "admin/field/index";
    }

    @GetMapping("/add-field")
    public String add(Model model) {
        Field field = new Field();
        model.addAttribute("field", field);

        List<FieldType> fieldTypeList = this.fieldTypeService.getAll();
        model.addAttribute("fieldTypeList", fieldTypeList);

        return "admin/field/add";
    }

    @PostMapping("/add-field")
    public String save(@ModelAttribute("field") Field field, @RequestParam("fileImage") MultipartFile file) {

        this.storageService.store(file);
        String imageName = file.getOriginalFilename();
        field.setFieldImage(imageName);

        if (this.fieldService.createNew(field)) {
            return "redirect:/admin/field";
        } else {
            return "admin/field/add";
        }
    }

    @GetMapping("/edit-field/{fieldId}")
    public String edit(Model model, @PathVariable("fieldId") Integer fieldId) {

        Field field = this.fieldService.findByID(fieldId);
        model.addAttribute("field", field);

        List<FieldType> fieldTypeList = this.fieldTypeService.getAll();
        model.addAttribute("fieldTypeList", fieldTypeList);

        return "admin/field/edit";
    }

    @PostMapping("/edit-field")
    public String update(@ModelAttribute("field") Field field, @RequestParam(value = "fileImage", required = false) MultipartFile file) {

        try {
            if (file != null && !file.isEmpty()) {
                this.storageService.store(file);
                String imageName = file.getOriginalFilename();
                field.setFieldImage(imageName);
            } else {
                Field oldField = this.fieldService.findByID(field.getFieldId());
                field.setFieldImage(oldField.getFieldImage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (this.fieldService.update(field)) {
            return "redirect:/admin/field";
        } else {
            return "admin/field/edit";
        }
    }

    @GetMapping("/delete-field/{fieldId}")
    public String delete(@PathVariable("fieldId") Integer fieldId) {

        if (this.fieldService.delete(fieldId)) {
            return "redirect:/admin/field";
        } else {
            return "redirect:/admin/field";
        }
    }
}
