package com.btl.n4j.controllers.user;

import com.btl.n4j.models.*;
import com.btl.n4j.repository.BookingRepository;
import com.btl.n4j.services.BookingService;
import com.btl.n4j.services.FieldTypeService;
import com.btl.n4j.services.Field_TimeSlotService;
import com.btl.n4j.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;

import java.sql.Date;
import java.util.List;

@Controller
public class BookingFieldController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private FieldTypeService fieldTypeService;

    @Autowired
    private UserService userService;

    @Autowired
    private Field_TimeSlotService fieldTimeSlotService;

    @RequestMapping("/booking-field")
    public String index(Model model, @RequestParam("date") String date, @RequestParam("fieldId") Integer fieldId, @RequestParam("timeSlotId") Integer timeSlotId) {

        Date orderDate = Date.valueOf(date);

        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userDetails.getUser();

        Booking booking = new Booking();


        booking.setUser(user);
        booking.setDate(orderDate);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = formatter.format(orderDate);

        Field_TimeSlot fieldTimeSlot = this.fieldTimeSlotService.findByFieldAndTimeId(fieldId, timeSlotId);

        booking.setFieldTimeslot(fieldTimeSlot);

        List<FieldType> fieldTypeList = this.fieldTypeService.getAll();
        model.addAttribute("fieldTypeList", fieldTypeList);



        model.addAttribute("booking", booking);
        model.addAttribute("fieldTimeSlot", fieldTimeSlot);
        model.addAttribute("user", user);
        model.addAttribute("date", formattedDate);

        return "booking";
    }

    @PostMapping("/booking-field")
    public String add(@ModelAttribute("booking") Booking booking,
                      @RequestParam("dateStr") String dateStr,  // Thêm parameter này
                      Model model) {
        try {
            // Convert String to java.sql.Date
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date parsedDate = formatter.parse(dateStr);
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
            booking.setDate(sqlDate);

            // Lấy lại thông tin user từ security context
            CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userDetails.getUser();
            booking.setUser(user);

            // Lấy lại đầy đủ thông tin fieldTimeSlot
            Field_TimeSlot fullFieldTimeSlot = fieldTimeSlotService.findByID(booking.getFieldTimeslot().getFieldTimeslotId());
            booking.setFieldTimeslot(fullFieldTimeSlot);

            // Set lại total price
            booking.setTotalPrice(fullFieldTimeSlot.getTimeSlot().getPricePerHour());

            if(this.bookingService.createNew(booking)) {
                return "redirect:/";
            } else {
                model.addAttribute("error", "Chưa đặt được sân!");
                return "booking";
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi lưu booking: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
            return "booking";
        }
    }
}
