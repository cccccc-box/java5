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

import net.bookstores.assignment.dao.UserDao;
import net.bookstores.assignment.entities.User;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    UserDao userDao;

    @GetMapping("/index")
    public String index(Model model) {
        List<User> usersList = userDao.findAll();
        model.addAttribute("list", usersList);
        return "admin/users/t";
    }

    @GetMapping("/create")
    public String createForm() {
        return "/admin/users/insert";
    }

    @PostMapping("/create")
    public String create(Model model, @RequestParam("fullName") String fullName, @RequestParam("email") String email,
            @RequestParam("password") String password, @RequestParam("phone") String phone,
            @RequestParam("address") String address, @RequestParam("role") Boolean role,
            @RequestParam("active") Boolean active) {
        User user = User.builder().fullName(fullName).email(email).password(password).phone(phone).address(address)
                .role(role).active(active).build();
        userDao.save(user);
        return "redirect:/admin/user/index";
    }

    @GetMapping("/edit")
    public String updateForm(@RequestParam("id") Integer id, Model model) {
        Optional<User> user = userDao.findById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get()); // Loại bỏ Optional
            return "admin/users/update";
        } else {
            return "redirect:/error";
        }
    }

    @PostMapping("/update")
    public String update(Model model, @RequestParam("userId") Integer userId, @RequestParam("fullName") String fullName,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("address") String address,
            @RequestParam("role") Boolean role,
            @RequestParam("active") Boolean active) {
        Optional<User> userP = userDao.findById(userId);
        if (userP.isPresent()) {
            User user = User.builder().userId(userId).fullName(fullName).password(userP.get().getPassword())
                    .email(email).phone(phone)
                    .address(address)
                    .role(role).active(active).build();
            userDao.save(user);
        }
        return "redirect:/admin/user/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Integer id) {
        userDao.deleteById(id);
        return "redirect:/admin/user/index";
    }
}
