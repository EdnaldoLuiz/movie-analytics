package com.ednaldoluiz.moviedash.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/test")
@Tag(name = "Controller de teste")
public class TestController {

    @GetMapping("/hello")
    public String allMovies() {
        LocalDateTime now = LocalDateTime.now();
        log.info("Hello World! {}", now);
        return "Hello World!";
    }

    @GetMapping("/logs")
    public String logs() {
        LocalDateTime now = LocalDateTime.now();
        log.info("Logssssss! {}", now);
        return "Bem vindo aos logs!";
    }
}
