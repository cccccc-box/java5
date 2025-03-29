package net.bookstores.assignment.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderId")
    private Integer orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BookId", referencedColumnName = "BookId")
    private Book book;

    @Column(name = "Price")
    private Float price;

    @Column(name = "Amount")
    private Integer amount;

    @Column(name = "DiscountPercentage")
    private Float discountPercentage;

    @Column(name = "DiscountedPrice")
    private Float discountedPrice;

    @Column(name = "Address", nullable = false)
    private String address;

    @Column(name = "Active")
    private Boolean active;

    @Column(name = "ProviderId")
    private Integer providerId;

    @Column(name = "DiscountId")
    private Integer DiscountId;

    @Column(name = "CreateDate")
    private java.util.Date createDate;

    @ManyToOne
    @JoinColumn(name = "ProviderID")
    private ShippingProvider shippingProvider;

    @ManyToOne
    @JoinColumn(name = "DiscountID")
    private Discount discount;
}
