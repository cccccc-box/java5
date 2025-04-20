package net.bookstores.assignment.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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

    @Column(name = "RecipientName")
    private String recipientName;

    @Column(name = "Address")
    private String address;

    @Column(name = "Phone")
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "OrderDate")
    private Date orderDate;

    @Column(name = "TotalMerchandiseValue")
    private Float totalMerchandiseValue;

    @Column(name = "TotalAmount")
    private Float totalAmount;

    @ManyToOne
    @JoinColumn(name = "PaymentMethodID")
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "ProviderID")
    private ShippingProvider shippingProvider;

    @ManyToOne
    @JoinColumn(name = "DiscountID")
    private Discount discount;

    @Column(name = "Status")
    private Integer status;

    @OneToMany(mappedBy = "order")
    private List<Review> review;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetail;

}
