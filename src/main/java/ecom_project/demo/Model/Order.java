package ecom_project.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private String item;
    private LocalDate date;
    private String price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters & Setters
}
