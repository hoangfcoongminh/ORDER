package com.btl.n4j.controllers.admin;

import com.btl.n4j.models.*;
import com.btl.n4j.services.FieldService;
import com.btl.n4j.services.FieldTypeService;
import com.btl.n4j.services.Field_TimeSlotService;
import com.btl.n4j.services.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class Field_TimeSlotController {

    @Autowired
    private Field_TimeSlotService fieldTimeSlotService;

    @Autowired
    private FieldService fieldService;

    @Autowired
    private TimeSlotService timeSlotService;

    @Autowired
    private FieldTypeService fieldTypeService;

    @GetMapping("/field_timeslot")
    public String index(Model model) {
        List<Field_TimeSlot> fieldTimeSlots = this.fieldTimeSlotService.getAll();
        model.addAttribute("fieldTimeSlots", fieldTimeSlots);

        return "admin/field_timeslot/index";
    }

    @GetMapping("/add-field_timeslot")
    public String add(Model model) {

        List<Field> fieldList = this.fieldService.getAll();
        model.addAttribute("fieldList", fieldList);

        List<TimeSlot> timeSlotList = this.timeSlotService.getAll();
        model.addAttribute("timeSlotList", timeSlotList);

//        List<FieldType> fieldTypeList = this.fieldTypeService.getAll();
//        model.addAttribute("fieldTypeList", fieldTypeList);

        Field_TimeSlot fieldTimeSlot = new Field_TimeSlot();
        model.addAttribute("fieldTimeSlot", fieldTimeSlot);
        return "admin/field_timeslot/add";
    }

//    @GetMapping("/api/fieldsbytype")
//    public List<Field> getFieldsByType(@RequestParam("fieldTypeId") Integer fieldTypeId) {
//        return this.fieldService.findByFieldTypeId(fieldTypeId);
//    }

    @PostMapping("/add-field_timeslot")
    public String save(@ModelAttribute("fieldTimeSlot") Field_TimeSlot fieldTimeSlot) {

        fieldTimeSlot.setAvailable(true);
        if (this.fieldTimeSlotService.createNew(fieldTimeSlot)) {
            return "redirect:/admin/field_timeslot";
        } else {
            return "admin/field_timeslot/add";
        }
    }

    @GetMapping("/edit-field_timeslot/{fieldTimeslotId}")
    public String edit(Model model, @PathVariable("fieldTimeslotId") Integer fieldTimeslotId) {

        List<Field> fieldList = this.fieldService.getAll();
        model.addAttribute("fieldList", fieldList);

        List<TimeSlot> timeSlotList = this.timeSlotService.getAll();
        model.addAttribute("timeSlotList", timeSlotList);

//        List<FieldType> fieldTypeList = this.fieldTypeService.getAll();
//        model.addAttribute("fieldTypeList", fieldTypeList);

        Field_TimeSlot fieldTimeSlot = this.fieldTimeSlotService.findByID(fieldTimeslotId);
        model.addAttribute("fieldTimeSlot", fieldTimeSlot);

        return "admin/field_timeslot/edit";
    }

    @PostMapping("/edit-field_timeslot")
    public String update(@ModelAttribute("fieldTimeSlot") Field_TimeSlot fieldTimeSlot) {

        if (this.fieldTimeSlotService.update(fieldTimeSlot)) {
            return "redirect:/admin/field_timeslot";
        } else {
            return "admin/field_timeslot/edit";
        }
    }

    @GetMapping("/delete-field_timeslot/{fieldTimeslotId}")
    public String delete(@PathVariable("fieldTimeslotId") Integer fieldTimeslotId) {

        if (this.fieldTimeSlotService.delete(fieldTimeslotId)) {
            return "redirect:/admin/field_timeslot";
        } else {
            return "redirect:/admin/field_timeslot";
        }
    }

}
