package net.bookstores.assignment.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenRouterService {

    @Value("${openrouter.api.key}")
    private String apiKey;

    @Value("${openrouter.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public OpenRouterService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String chat(String message) {
        String url = apiUrl + "/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("HTTP-Referer", "https://yourdomain.com"); // bắt buộc với OpenRouter
        headers.set("X-Title", "SpringBootChat"); // tên project

        // Tạo JSON request
        JSONObject userMessage = new JSONObject();
        userMessage.put("role", "user");
        userMessage.put("content", message);

        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "openai/gpt-3.5-turbo"); // hoặc model khác mà bạn muốn
        requestBody.put("messages", new org.json.JSONArray().put(userMessage));
        requestBody.put("temperature", 0.7);

        HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                JSONObject jsonResponse = new JSONObject(response.getBody());
                return jsonResponse
                        .getJSONArray("choices")
                        .getJSONObject(0)
                        .getJSONObject("message")
                        .getString("content");
            } else {
                System.err.println("Lỗi từ API OpenRouter: " + response.getStatusCode() + " - " + response.getBody());
                return "Xin lỗi, AI không phản hồi đúng. Vui lòng thử lại sau.";
            }

        } catch (Exception e) {
            System.err.println("Lỗi kết nối đến API OpenRouter:");
            e.printStackTrace();
            return "Xin lỗi, tôi không thể xử lý yêu cầu của bạn ngay lúc này. Vui lòng thử lại sau.";
        }
    }
}
