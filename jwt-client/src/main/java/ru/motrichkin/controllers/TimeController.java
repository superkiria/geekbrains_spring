package ru.motrichkin.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.motrichkin.contract.SessionUser;

import java.util.Date;

@RestController
@RequestMapping("/time")
public class TimeController {

    static private int count = 0;

    @GetMapping
    String getTime(@AuthenticationPrincipal SessionUser sessionUser) {
        count++;
        return (new Date()).toString() + ": " + count + "\n";
    }
}
