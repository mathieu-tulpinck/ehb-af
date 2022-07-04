package be.saxomoose.webshop.models;

import be.saxomoose.webshop.enums.Role;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Collection;

@Entity
@Table(name = "users")
public class Account
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "account", orphanRemoval = true)
    // Required to avoid the creation of a joint table.
    private Collection<Authority> authorities;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private Collection<Order> orders;

    @NotNull
    @Column(unique = true)
    @Length(max = 255)
    private String username;

    @NotNull
    @Length(max = 100)
    private String password;

    @NotNull
    @Column(columnDefinition = "TINYINT DEFAULT 1")
    private boolean enabled;

    @Transient
    private Role role;

    //    @CreatedDate
//    @Column(name = "created_at")
//    private Instant createdDate;
//
//    @LastModifiedDate
//    @Column(name = "updated_at")
//    private Instant lastModifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }


}