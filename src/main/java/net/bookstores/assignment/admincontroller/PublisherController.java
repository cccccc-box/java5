package net.bookstores.assignment.admincontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import net.bookstores.assignment.dao.PublisherDao;
import net.bookstores.assignment.entities.Publisher;
import net.bookstores.assignment.service.PublisherService;

@Controller
@RequestMapping("/admin/publisher")
public class PublisherController {
    @Autowired
    PublisherDao publisherDao;

    @Autowired
    PublisherService publisherService;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("list", publisherService.findAll());
        return "admin/publishers/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "admin/publishers/insert";
    }

    @PostMapping("/create")
    public String create(Model model, @Valid @ModelAttribute("publisher") Publisher publisher, Errors errors) {
        if (errors.hasErrors()) {
            return "admin/publishers/insert";// return view
        }

        try {
            publisherService.create(publisher);
            return "redirect:/admin/publisher/index";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "admin/publishers/insert";// return view
        }
    }

    @GetMapping("/edit")
    public String updateForm(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("publisher", publisherService.findById(id).get());
        return "admin/publishers/update";
    }

    @PostMapping("/update")
    public String update(Model model, @Valid @ModelAttribute Publisher publisher, Errors errors) {
        if (errors.hasErrors()) {
            return "admin/publishers/update";// return view
        }
        try {
            publisherService.update(publisher);
            return "redirect:/admin/publisher/index";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "admin/publishers/update";// return view
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Integer id) {
        publisherService.deleteById(id);
        return "redirect:/admin/publisher/index";
    }
}
