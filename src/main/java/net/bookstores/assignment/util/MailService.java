package net.bookstores.assignment.util;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

public interface MailService {
    @Data
    @Builder
    public static class Mail {
        @Default
        private String from = "Vbooks.net <web-shop@gmail.com>";
        private String to, cc, bcc, subject, body, filenames;
    }

    void send(Mail mail);

    default void send(String to, String subject, String body) {
        Mail mail = Mail.builder().to(to).subject(subject).body(body).build();
        this.send(mail);
    }
}