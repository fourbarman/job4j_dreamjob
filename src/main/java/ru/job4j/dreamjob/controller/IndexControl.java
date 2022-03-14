package ru.job4j.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * IndexControl.
 * Control layer.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 14.03.2022.
 */
@Controller
public class IndexControl {

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
