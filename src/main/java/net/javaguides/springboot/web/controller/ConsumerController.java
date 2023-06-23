package net.javaguides.springboot.web.controller;


import net.javaguides.springboot.exception.ConsumerNotFoundException;
import net.javaguides.springboot.model.Consumer;
import net.javaguides.springboot.service.impl.ConsumerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ConsumerController {
    @Autowired private ConsumerServiceImpl service;

    @GetMapping("/users")
    public String showUserList(Model model){
        List<Consumer> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/users/getByName")
    public String showUserListByName(
            Model model,
            @RequestParam(value = "keyWord", defaultValue = "") String keyWord){
        List<Consumer> listUsers = service.listAllByName(keyWord);
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("keyWord", keyWord);
        return "users";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model){
        model.addAttribute("user", new Consumer());
        model.addAttribute("pageTitle", "Add New Consumer");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(Consumer user, RedirectAttributes ra){
        service.save(user);
        ra.addFlashAttribute("message", "the consumer has been saved successfully");
        return "redirect:/users";
    }

    @GetMapping("users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra ){
        try {
            Consumer user = service.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit Consumer (ID: " + id + ")");
            return "user_form";
        } catch (ConsumerNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }
    }

    @GetMapping("users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra ){
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The Consumer ID " + id +" has been deleted.");
        } catch (ConsumerNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";
    }

    @RequestMapping(
            value = "/a",
            method = RequestMethod.GET,
            produces="application/json"
    )
    @ResponseBody
    public List<Consumer> get() {
        return service.listAll();
    }

}
