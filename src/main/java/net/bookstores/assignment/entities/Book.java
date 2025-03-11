package net.bookstores.assignment.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
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

    @Column(name = "Price", nullable = false)
    private Float price;

    @Column(name = "DiscountPercentage", nullable = false)
    private Float discountPercentage;

    @Column(name = "DiscountedPrice", nullable = false)
    private Float discountedPrice;

    @Min(0)
    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @Column(name = "Description", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(name = "ImagePath", length = 500)
    private String imagePath;
}
