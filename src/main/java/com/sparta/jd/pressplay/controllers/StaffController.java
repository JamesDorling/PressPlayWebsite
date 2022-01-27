package com.sparta.jd.pressplay.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaffController {

    public StaffController() {
    }

    @GetMapping("/staff-room")
    public String goToStaffRoom() {
        return "staff-room";
    }
}
