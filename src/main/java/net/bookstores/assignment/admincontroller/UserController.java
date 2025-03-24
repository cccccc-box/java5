package net.bookstores.assignment.admincontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.bookstores.assignment.entities.User;
import net.bookstores.assignment.service.UserService;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String index(Model model) {
        List<User> usersList = userService.getAllUsers();
        model.addAttribute("list", usersList);
        return "admin/users/t";
    }

    @GetMapping("/create")
    public String createForm() {
        return "/admin/users/insert";
    }

    @PostMapping("/create")
    public String create(Model model, 
                         @RequestParam("fullName") String fullName, 
                         @RequestParam("email") String email,
                         @RequestParam("password") String password, 
                         @RequestParam("phone") String phone,
                         @RequestParam("address") String address, 
                         @RequestParam("role") Boolean role,
                         @RequestParam("active") Boolean active) {
        userService.createUser(fullName, email, password, phone, address, role, active);
        return "redirect:/admin/user/index";
    }

    @GetMapping("/edit")
    public String updateForm(@RequestParam("id") Integer id, Model model) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "admin/users/update";
        } else {
            return "redirect:/error";
        }
    }

    @PostMapping("/update")
    public String update(@RequestParam("userId") Integer userId, 
                         @RequestParam("fullName") String fullName,
                         @RequestParam("email") String email, 
                         @RequestParam("phone") String phone,
                         @RequestParam("address") String address, 
                         @RequestParam("role") Boolean role,
                         @RequestParam("active") Boolean active) {
        userService.updateUser(userId, fullName, email, phone, address, role, active);
        return "redirect:/admin/user/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin/user/index";
    }
}
