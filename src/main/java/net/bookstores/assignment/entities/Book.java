package net.bookstores.assignment.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookId")
    private Integer bookId;

    @NotBlank(message = "Tiêu đề sách không được để trống")
    @Column(name = "Title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "AuthorId")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "PublisherId")
    private Publisher publisher;

    @Min(value = 1000, message = "Giá sách không thể nhỏ hơn 1000đ")
    @Column(name = "Price", nullable = false)
    private Float price;

    @Column(name = "DiscountPercentage", nullable = false)
    private Float discountPercentage;

    @Min(value = 1000, message = "Giá sau khi giảm không thể nhỏ hơn 1000đ")
    @Column(name = "DiscountedPrice", nullable = false)
    private Float discountedPrice;

    @AssertTrue(message = "Giá sau khi giảm không thể lớn hơn giá gốc")
    public boolean isDiscountedPriceValid() {
        return discountedPrice <= price;
    }

    @Min(value = 0, message = "Số lượng sách không thể nhỏ hơn 0")
    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @Column(name = "Description", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @NotBlank(message = "File ảnh không được trống")
    @Column(name = "ImagePath", length = 500)
    private String imagePath;

    @OneToMany(mappedBy = "book")
    private List<Cart> carts;

    @OneToMany(mappedBy = "book")
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "book")
    private List<Review> reviews;

    @OneToMany(mappedBy = "book")
    private List<Order> orders;
}
