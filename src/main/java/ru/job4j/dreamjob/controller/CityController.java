package ru.job4j.dreamjob.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.services.CityService;

import javax.servlet.http.HttpSession;

@ThreadSafe
@Controller
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/cities")
    public String cities(Model model, HttpSession session) {
        getUserFromSession(model, session);
        model.addAttribute("cities", cityService.getAllCities());
        return "cities";
    }

    @GetMapping("/addCity")
    public String addCity(Model model, HttpSession session) {
        getUserFromSession(model, session);
        return "addCity";
    }

    @GetMapping("/formAddCity")
    public String formAddCity(Model model) {
        return "addCity";
    }

    @PostMapping("/saveCity")
    public String saveCity(@ModelAttribute City city) {
        cityService.add(city);
        return "redirect:/cities";
    }

    @GetMapping("/formUpdateCity/{cityId}")
    public String formUpdateCity(Model model, @PathVariable("cityId") int id, HttpSession session) {
        getUserFromSession(model, session);
        model.addAttribute("city", cityService.findById(id));
        return "updateCity";
    }

    @PostMapping("/updateCity")
    public String updateCity(@ModelAttribute City city) {
        cityService.updateCity(city);
        return "redirect:/cities";
    }

    @PostMapping("/createCity")
    public String createCity(@ModelAttribute City city) {
        cityService.add(city);
        return "redirect:/cities";
    }

    private void getUserFromSession(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        model.addAttribute("user", user);
    }
}
