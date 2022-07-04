package be.saxomoose.webshop.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "authorities", indexes = @Index(name = "ix_auth_username", columnList = "username, authority"))
public class Authority
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @ManyToOne
    @JoinColumn(name = "username", columnDefinition = "VARCHAR(255) NOT NULL", foreignKey = @ForeignKey(name = "fk_authorities_users", foreignKeyDefinition = "FOREIGN KEY(`username`) REFERENCES users(username)"))
    private Account account;

    @NotNull
    @Length(max = 50)
    private String authority;
}
