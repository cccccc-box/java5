// Discount.java
package net.bookstores.assignment.entities;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(name = "Code", unique = true)
    private String code;

    @NotBlank(message = "Mô tả không được để trống")
    @Column(name = "Description")
    private String description;

    @NotNull(message = "Ngày bắt đầu không được để trống")
    @Column(name = "StartDate")
    private LocalDate startDate;

    @NotNull(message = "Ngày kết thúc không được để trống")
    @Column(name = "EndDate")
    private LocalDate endDate;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 0, message = "Số lượng mã giảm giá không được nhỏ hơn 0")
    @Column(name = "Quantity")
    private Integer quantity;

    @Builder.Default
    @Column(name = "IsActive")
    private Boolean isActive = true;

    @Column(name = "CreateAt")
    private LocalDate createAt;

    @OneToMany(mappedBy = "discount")
    private List<Order> orders;

    @AssertTrue(message = "Ngày bắt đầu phải trước ngày kết thúc")
    public boolean isValidDateRange() {
        if (startDate == null || endDate == null) {
            return true;
        }
        return startDate.isBefore(endDate);
    }
}
