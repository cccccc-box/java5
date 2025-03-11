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
@Table(name = "Authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AuthorID")
    private Integer authorId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Bio", columnDefinition = "NVARCHAR(MAX)")
    private String bio;

    @Column(name = "ImagePath", length = 500)
    private String imagePath;

    @OneToMany(mappedBy = "author")
    private List<Book> books;
}
