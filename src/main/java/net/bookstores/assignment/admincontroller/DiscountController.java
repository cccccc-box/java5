// DiscountController.java
package net.bookstores.assignment.admincontroller;

import net.bookstores.assignment.entities.Discount;
import net.bookstores.assignment.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/admin/discounts")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @GetMapping
    public String listDiscounts(Model model) {
        model.addAttribute("discounts", discountService.getAllDiscounts());
        return "admin/discounts/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("discount", new Discount());
        return "admin/discounts/form";
    }

    @PostMapping("/save")
    public String saveDiscount(@Valid @ModelAttribute("discount") Discount discount, 
                               BindingResult result, 
                               RedirectAttributes redirect, 
                               Model model) {
        if (result.hasErrors()) {
            return "admin/discounts/form";
        }
        try {
            discountService.createDiscount(discount);
        } catch (DataIntegrityViolationException ex) {
            // Thêm thông báo lỗi cho trường mã giảm giá
            result.rejectValue("code", "error.discount", "Mã giảm giá '" + discount.getCode() + "' đã tồn tại!");
            return "admin/discounts/form";
        }
        redirect.addFlashAttribute("message", "Lưu mã giảm giá thành công!");
        return "redirect:/admin/discounts";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Optional<Discount> discount = discountService.getDiscountById(id);
        if (discount.isPresent()) {
            model.addAttribute("discount", discount.get());
            return "admin/discounts/form";
        } else {
            return "redirect:/admin/discounts";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteDiscount(@PathVariable Integer id, RedirectAttributes redirect) {
        discountService.deleteDiscount(id);
        redirect.addFlashAttribute("message", "Xóa mã giảm giá thành công!");
        return "redirect:/admin/discounts";
    }
}
