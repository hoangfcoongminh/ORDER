package com.btl.n4j.controllers.admin;

import com.btl.n4j.models.Booking;
import com.btl.n4j.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/booking")
    public String index(Model model) {
        List<Booking> bookingList = this.bookingService.getAll();
        model.addAttribute("bookingList", bookingList);
        return "admin/booking/index";
    }
}
