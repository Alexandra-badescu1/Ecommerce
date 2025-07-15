package ecom_project.demo.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;


    private String imageName;

    private String imageType;

    @Lob
    private byte[] imageDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(unique = true, nullable = false)
    private String username;

    @PrePersist
    public void generateUsername() {
        if (this.username == null || this.username.isEmpty()) {
            this.username = (firstName + lastName).toLowerCase().replaceAll("\\s+", "");
        }
    }

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

}
