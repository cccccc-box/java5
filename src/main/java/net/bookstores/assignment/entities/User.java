package net.bookstores.assignment.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private Integer userId;

    @Column(name = "FullName", nullable = false)
    private String fullName;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Phone", length = 20)
    private String phone;

    @Column(name = "Address")
    private String address;

    @Builder.Default
    @Column(name = "Role", nullable = false)
    private Boolean role = false; // Dùng Boolean vì BIT lưu true/false

    @Builder.Default
    @Column(name = "Active", nullable = true)
    private Boolean active = true; // Giá trị mặc định là 1 (true)
}
