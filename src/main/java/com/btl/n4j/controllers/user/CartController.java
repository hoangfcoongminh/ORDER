package com.btl.n4j.controllers.user;

import com.btl.n4j.models.FieldType;
import com.btl.n4j.services.FieldTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class CartController {

    @Autowired
    private FieldTypeService fieldTypeService;

    @GetMapping("/cart")
    public String index(Model model) {
        List<FieldType> fieldTypeList = this.fieldTypeService.getAll();
        model.addAttribute("fieldTypeList", fieldTypeList);

        return "cart";
    }
}
