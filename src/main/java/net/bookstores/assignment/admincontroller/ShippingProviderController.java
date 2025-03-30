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
import net.bookstores.assignment.entities.ShippingProvider;
import net.bookstores.assignment.service.ShippingProviderService;

@Controller
@RequestMapping("/admin/shippingprovider")
public class ShippingProviderController {
    @Autowired
    ShippingProviderService shippingProviderService;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("list", shippingProviderService.findAll());
        return "admin/shippingprovider/index";
    }

    @GetMapping("/insert")
    public String insertForm(Model model) {
        model.addAttribute("shippingProvider", new ShippingProvider());
        return "admin/shippingprovider/insert";
    }

    @PostMapping("/create")
    public String insert(Model model, @Valid @ModelAttribute("shippingProvider") ShippingProvider shippingProvider,
            Errors errors) {
        if (errors.hasErrors()) {
            return "admin/shippingprovider/insert";
        } else {
            shippingProviderService.create(shippingProvider.getName(), shippingProvider.getHotline(),
                    shippingProvider.getShippingFee(), shippingProvider.getIsActive());
        }
        return "redirect:/admin/shippingprovider/index";
    }

    @GetMapping("/edit")
    public String updateForm(Model model, @RequestParam("id") Integer id) {
        model.addAttribute("shippingProvider", shippingProviderService.findById(id).get());
        return "admin/shippingprovider/update";
    }

    @PostMapping("/update")
    public String update(Model model, @Valid @ModelAttribute("shippingProvider") ShippingProvider shippingProvider,
            Errors errors) {
        if (errors.hasErrors()) {
            return "admin/shippingprovider/update";
        } else {
            shippingProviderService.update(shippingProvider.getProviderId(), shippingProvider.getName(),
                    shippingProvider.getHotline(),
                    shippingProvider.getShippingFee(), shippingProvider.getIsActive());
        }
        return "redirect:/admin/shippingprovider/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Integer id) {
        shippingProviderService.deleteById(id);
        return "redirect:/admin/shippingprovider/index";
    }
}
