package org.example.worktrack.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class DashBoardController {
    @GetMapping("/")
    public String landingPage() {
        return "landing";
    }
}
