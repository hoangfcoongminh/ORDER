package com.btl.n4j.controllers.user;

import com.btl.n4j.models.Field;
import com.btl.n4j.services.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private FieldService fieldService;

    @RequestMapping("")
    public String index(Model model) {

        List<Field> fieldList = this.fieldService.getAll();
        model.addAttribute("fieldList", fieldList);

        return "index";
    }

    @GetMapping("/detail-field/{fieldId}")
    public String detail(Model model, @PathVariable("fieldId") Integer fieldId) {

        Field field = this.fieldService.findByID(fieldId);
        model.addAttribute("field", field);

        return "fielddetail";
    }
}
