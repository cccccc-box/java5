package net.bookstores.assignment.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "Reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReviewID")
    private Integer reviewId;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "BookID")
    private Book book;

    @Min(value = 1, message = "Đánh giá phải từ 1 đến 5")
    @Column(name = "Rating")
    private Integer rating;

    @Column(name = "ImagePath")
    private String imagePath;

    @NotBlank(message = "Đánh giá không được để trống")
    @Column(name = "Comment")
    private String comment;

    @Column(name = "CreatedAt")
    private Date createAt;
}
