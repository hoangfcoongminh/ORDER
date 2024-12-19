package com.btl.n4j.controllers.user;

import com.btl.n4j.models.*;
import com.btl.n4j.repository.UserRepository;
import com.btl.n4j.services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FieldService fieldService;

    @Autowired
    private FieldTypeService fieldTypeService;

    @Autowired
    private Field_TimeSlotService fieldTimeSlotService;

    @GetMapping({"/", "/home"})
    public String index(HttpSession session, Model model) {

        User loggedUser = (User) session.getAttribute("loggedInUser");

        model.addAttribute("loggedUser", loggedUser);

        List<FieldType> fieldTypeList = this.fieldTypeService.getAll();
        model.addAttribute("fieldTypeList", fieldTypeList);

        return "index";
    }


    @GetMapping("/search")
    public String search(Model model, @Param("keyword") String keyword) {

        if(keyword != null ) {
            List<FieldType> fieldTypeList = this.fieldTypeService.getAll();
            model.addAttribute("fieldTypeList", fieldTypeList);

            List<Field> fieldList = this.fieldService.searchField(keyword);
            model.addAttribute("fieldList", fieldList);

            model.addAttribute("keyword", keyword);

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

        List<TimeSlot> timeSlotList = this.fieldTimeSlotService.findTimeSlotAvaibleByFieldId(fieldId);
        model.addAttribute("timeSlotList", timeSlotList);

        return "detail";
    }

    @GetMapping("/infor-user/{username}")
    public String edit(@PathVariable String username, Model model) {

        User user = this.userRepository.findByUsername(username);
        model.addAttribute("user", user);

        List<FieldType> fieldTypeList = this.fieldTypeService.getAll();
        model.addAttribute("fieldTypeList", fieldTypeList);

        return "userinfo";
    }

    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute("user") User user,
                             BindingResult result, @RequestParam("newPassword") String newPassword,
                             RedirectAttributes redirectAttributes) {
        try {
            User existingUser = userRepository.findByUsername(user.getUsername());
            if(newPassword != null && newPassword != ""){
                existingUser.setPassword(new BCryptPasswordEncoder().encode(newPassword));
            }
            existingUser.setFullName(user.getFullName());
            existingUser.setPhoneNumber(user.getPhoneNumber());
            existingUser.setAddress(user.getAddress());
            existingUser.setEmail(user.getEmail());

            userRepository.save(existingUser);
            redirectAttributes.addFlashAttribute("success", "Cập nhật thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi cập nhật!");
        }

        return "redirect:/infor-user/" + user.getUsername();
    }
}
