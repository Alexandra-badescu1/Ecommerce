package ecom_project.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDTO {
    private String name;
    private String email;
    private List<OrderDTO> orders;

    public UserProfileDTO(User user) {
        this.name = user.getFirstName() + " " + user.getLastName();
        this.email = user.getEmail();
        this.orders = user.getOrders().stream()
                .map(OrderDTO::new)
                .toList();
    }
}
