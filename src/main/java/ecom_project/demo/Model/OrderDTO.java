package ecom_project.demo.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private String item;
    private String date;
    private String price;

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.item = order.getItem();
        this.date = order.getDate().toString(); // convertim LocalDate în String
        this.price = order.getPrice();
    }
}
