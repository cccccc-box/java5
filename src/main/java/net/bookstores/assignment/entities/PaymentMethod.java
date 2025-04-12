package net.bookstores.assignment.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PaymentMethods")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaymentMethodID")
    private Integer paymentMethodID;

    @Column(name = "MethodName", nullable = false, unique = true)
    private String methodName;

    @Column(name = "Active")
    private Boolean active;

    @Column(name = "Description")
    private String description;

    @OneToMany(mappedBy = "paymentMethod")
    private List<Order> orders;
}
