package com.postexpress.Postrexpress.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "packages")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "recipient")
    private User recipient;

    @ManyToMany
    @JoinColumn(name = "recipients")
    private List<User> recipients;

    @ManyToOne
    @JoinColumn(name = "addresser")
    private User addresser;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Override
    public String toString() {
        return "Package{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", recipient=" + recipient +
                ", addresser=" + addresser +
                ", status=" + status +
                '}';
    }
}
