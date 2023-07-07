package com.postexpress.Postrexpress.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "packages")
@Getter
@Setter
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;

    @ManyToOne
    @JoinColumn(name = "addresser_id")
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
