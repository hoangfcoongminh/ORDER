package com.btl.n4j.controllers.user;

import com.btl.n4j.models.*;
import com.btl.n4j.services.CategoryService;
import com.btl.n4j.services.FieldService;
import com.btl.n4j.services.FieldTypeService;
import com.btl.n4j.services.Field_TimeSlotService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private FieldService fieldService;

    @Autowired
    private FieldTypeService fieldTypeService;

    @Autowired
    private Field_TimeSlotService fieldTimeSlotService;

    @GetMapping()
    public String index(HttpSession session, Model model) {

        List<FieldType> fieldTypeList = this.fieldTypeService.getAll();
        model.addAttribute("fieldTypeList", fieldTypeList);

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        model.addAttribute("loggedInUser", loggedInUser); // Thông tin người dùng hiện tại (nếu có)
        return "index";
    }

    @GetMapping("/search")
    public String search(Model model, @Param("keyword") String keyword) {

        if(keyword != null ) {
            List<FieldType> fieldTypeList = this.fieldTypeService.getAll();
            model.addAttribute("fieldTypeList", fieldTypeList);

            List<Field> fieldList = this.fieldService.searchField(keyword);
            model.addAttribute("fieldList", fieldList);

            return "search";
        }
        return "index";
    }

    @GetMapping("/fieldtype/{fieldTypedId}")
    public String fieldtype(Model model, @PathVariable("fieldTypedId") Integer fieldTypedId) {

        List<FieldType> fieldTypeList = this.fieldTypeService.getAll();
        model.addAttribute("fieldTypeList", fieldTypeList);

        List<Field> fieldList = this.fieldService.findByFieldTypeId(fieldTypedId);
        model.addAttribute("fieldList", fieldList);

        return "search";
    }

    @GetMapping("/field")
    public String field(Model model) {

        List<Field> fieldList = this.fieldService.getAll();
        model.addAttribute("fieldList", fieldList);

        List<FieldType> fieldTypeList = this.fieldTypeService.getAll();
        model.addAttribute("fieldTypeList", fieldTypeList);

        return "field";
    }

    @GetMapping("/detail-field/{fieldId}")
    public String detail(Model model, @PathVariable("fieldId") Integer fieldId) {

        Field field = this.fieldService.findByID(fieldId);
        model.addAttribute("field", field);

        List<FieldType> fieldTypeList = this.fieldTypeService.getAll();
        model.addAttribute("fieldTypeList", fieldTypeList);

        List<TimeSlot> timeSlotList = this.fieldTimeSlotService.findTimeSlotByFieldId(fieldId);
        model.addAttribute("timeSlotList", timeSlotList);

        return "detail";
    }
}
