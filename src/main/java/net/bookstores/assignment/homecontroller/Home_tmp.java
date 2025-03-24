package net.bookstores.assignment.homecontroller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.bookstores.assignment.service.HomeService;

@Controller
public class Home_tmp {

    @Autowired
    private HomeService homeService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("top5", homeService.getTop5Books());
        model.addAttribute("top10", homeService.getTop10RandomBooks());
        model.addAttribute("top5Author", homeService.getTop5RandomAuthors());
        return "/home/index";
    }

    @GetMapping("/testheader")
    public String testheader() {
        return "/home/testHeader";
    }
}
