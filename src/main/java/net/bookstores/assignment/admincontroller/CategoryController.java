package net.bookstores.assignment.admincontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.bookstores.assignment.dao.CategoryDao;
import net.bookstores.assignment.entities.Category;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    CategoryDao categoryDao;

    @GetMapping("/index")
    public String index(Model model) {
        List<Category> list = categoryDao.findAll();
        model.addAttribute("list", list);
        return "admin/categories/index";
    }

    @GetMapping("/create")
    public String createForm() {
        return "admin/categories/insert";
    }

    @PostMapping("/create")
    public String create(Model model, @RequestParam("name") String name) {
        Category category = Category.builder().name(name).build();
        categoryDao.save(category);
        return "redirect:/admin/category/index";
    }

    @GetMapping("/edit")
    public String updateForm(@RequestParam("id") Integer id, Model model) {
        Optional<Category> category = categoryDao.findById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get()); // Loại bỏ Optional
            return "admin/categories/update";
        } else {
            return "redirect:/error";
        }
    }

    @PostMapping("/update")
    public String update(@RequestParam("categoryId") Integer categoryId, @RequestParam("name") String name) {
        Category category = Category.builder().categoryId(categoryId).name(name).build();
        categoryDao.save(category);
        return "redirect:/admin/category/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Integer id) {
        categoryDao.deleteById(id);
        return "redirect:/admin/category/index";
    }

}
