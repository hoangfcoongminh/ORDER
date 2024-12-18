package com.btl.n4j.controllers.admin;

import com.btl.n4j.models.Category;
import com.btl.n4j.models.TimeSlot;
import com.btl.n4j.services.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TimeSlotController {

    @Autowired
    private TimeSlotService timeSlotService;

    @GetMapping("/timeslot")
    public String index(Model model) {

        List<TimeSlot> timeSlotList = this.timeSlotService.getAll();
        model.addAttribute("timeSlotList", timeSlotList);

        return "admin/timeslot/index";
    }

    @GetMapping("/add-timeslot")
    public String add(Model model) {

        TimeSlot timeSlot = new TimeSlot();
        model.addAttribute("timeSlot", timeSlot);
        return "admin/timeslot/add";
    }

    @PostMapping("/add-timeslot")
    public String save(@ModelAttribute("timeSlot") TimeSlot timeSlot, @RequestParam("startTime") String startTimeStr, @RequestParam("endTime") String endTimeStr) {

        Time startTime = Time.valueOf(startTimeStr);
        Time endTime = Time.valueOf(endTimeStr);

        timeSlot.setStartTime(startTime);
        timeSlot.setEndTime(endTime);

        if (this.timeSlotService.createNew(timeSlot)) {
            return "redirect:/admin/timeslot";
        } else {
            return "admin/timeslot/add";
        }
    }

    @GetMapping("/edit-timeslot/{timeSlotId}")
    public String edit(Model model, @PathVariable("timeSlotId") Integer timeSlotId) {

        TimeSlot timeSlot = this.timeSlotService.findByID(timeSlotId);
        model.addAttribute("timeSlot", timeSlot);
        return "admin/timeslot/edit";
    }

    @PostMapping("/edit-timeslot")
    public String update(@ModelAttribute("timeSlot") TimeSlot timeSlot) {

        if (this.timeSlotService.update(timeSlot)) {
            return "redirect:/admin/timeslot";
        } else {
            return "admin/timeslot/edit";
        }
    }

    @GetMapping("/delete-timeslot/{timeSlotId}")
    public String delete(@PathVariable("timeSlotId") Integer timeSlotId) {

        if (this.timeSlotService.delete(timeSlotId)) {
            return "redirect:/admin/timeslot";
        } else {
            return "redirect:/admin/timeslot";
        }
    }
}
