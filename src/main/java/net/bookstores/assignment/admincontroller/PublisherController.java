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

import net.bookstores.assignment.dao.PublisherDao;
import net.bookstores.assignment.entities.Publisher;

@Controller
@RequestMapping("/admin/publisher")
public class PublisherController {
    @Autowired
    PublisherDao publisherDao;

    @GetMapping("/index")
    public String index(Model model) {
        List<Publisher> list = publisherDao.findAll();
        model.addAttribute("list", list);
        return "admin/publishers/index";
    }

    @GetMapping("/create")
    public String createForm() {
        return "admin/publishers/insert";
    }

    @PostMapping("/create")
    public String create(Model model, @RequestParam("name") String name) {
        Publisher publisher = Publisher.builder().name(name).build();
        publisherDao.save(publisher);
        return "redirect:/admin/publisher/index";
    }

    @GetMapping("/edit")
    public String updateForm(@RequestParam("id") Integer id, Model model) {
        Optional<Publisher> publisher = publisherDao.findById(id);
        if (publisher.isPresent()) {
            model.addAttribute("publisher", publisher.get()); // Loại bỏ Optional
            return "admin/publishers/update";
        } else {
            return "redirect:/error";
        }
    }

    @PostMapping("/update")
    public String update(@RequestParam("publisherId") Integer publisherId, @RequestParam("name") String name) {
        Publisher publisher = Publisher.builder().publisherId(publisherId).name(name).build();
        publisherDao.save(publisher);
        return "redirect:/admin/publisher/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Integer id) {
        publisherDao.deleteById(id);
        return "redirect:/admin/publisher/index";
    }
}
