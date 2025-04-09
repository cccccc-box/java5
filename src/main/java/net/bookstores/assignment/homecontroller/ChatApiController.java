package net.bookstores.assignment.homecontroller;

import org.springframework.web.bind.annotation.*;

import net.bookstores.assignment.service.OpenRouterService;

@RestController
@RequestMapping("/api/chat")
public class ChatApiController {

    private final OpenRouterService deepSeekService;

    public ChatApiController(OpenRouterService deepSeekService) {
        this.deepSeekService = deepSeekService;
    }

    @PostMapping
    public String chat(@RequestBody ChatRequest request) {
        return deepSeekService.chat(request.getMessage());
    }

    public static class ChatRequest {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}