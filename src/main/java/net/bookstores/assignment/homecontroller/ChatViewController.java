package net.bookstores.assignment.homecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatViewController {
    @GetMapping("/chat")
    public String chatPage() {
        return "chat";
    }
}
