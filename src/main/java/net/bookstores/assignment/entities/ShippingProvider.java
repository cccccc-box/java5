package net.bookstores.assignment.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "ShippingProviders")
public class ShippingProvider {
    @Id
    @Column(name = "ProviderID")
    private Integer providerId;

    @NotBlank(message = "Tên đơn vị vận chuyển không được để trống")
    @Column(name = "Name")
    private String name;

    @NotBlank(message = "Hotline không được để trống")
    @Column(name = "Hotline")
    private String hotline;

    @NotBlank(message = "Phí vận chuyển không được để trống")
    @Column(name = "ShippingFee")
    private Float shippingFee;

    @Builder.Default
    @Column(name = "IsActive")
    private Boolean isActive = true;

    @OneToMany(mappedBy = "shippingProvider")
    private List<Order> orders;
}
