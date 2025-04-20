package net.bookstores.assignment.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DiscountID")
    private Integer discountId;

    @NotBlank(message = "Mã giảm giá không được để trống")
    @Column(name = "Code", unique = true, nullable = false)
    private String code;

    @NotBlank(message = "Mô tả không được để trống")
    @Column(name = "Description", nullable = false)
    private String description;

    @NotNull(message = "Ngày bắt đầu không được để trống")

    @Column(name = "StartDate", nullable = false)
    private LocalDate startDate;

    @NotNull(message = "Ngày kết thúc không được để trống")
    @Column(name = "EndDate", nullable = false)
    private LocalDate endDate;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 0, message = "Số lượng mã giảm giá không được nhỏ hơn 0")
    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @NotNull(message = "Phần trăm giảm giá không được để trống")
    @Min(value = 0, message = "Phần trăm không được nhỏ hơn 0")
    @Max(value = 100, message = "Phần trăm không được lớn hơn 100")
    @Column(name = "DiscountPercentage", nullable = false)
    private Integer discountPercentage;

    @Builder.Default
    @Column(name = "IsActive")
    private Boolean isActive = true;

    @Column(name = "CreateAt")
    private LocalDate createAt;

    @OneToMany(mappedBy = "discount")
    private List<Order> orders;

    @AssertTrue(message = "Ngày kết thúc phải sau ngày bắt đầu")
    public boolean isValidDateRange() {
        if (startDate == null || endDate == null) {
            return true;
        }
        return startDate.isBefore(endDate); // Điều kiện bắt buộc startDate < endDate
    }
    
    @PrePersist
    public void prePersist() {
        if (this.createAt == null) {
            this.createAt = LocalDate.now(); // auto gán ngày tạo nếu null
        }
    }
}
