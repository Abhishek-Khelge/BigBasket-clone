package com.masai.BigBasketReplica.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    private Integer userId;
    private LocalDate placedDate;
    private LocalTime placedTime;
    private String deliveredStatus;
    private LocalDate deliveredDate;
    private LocalTime deliveredTime;
    private Double totalPrice;
    private String modeOfPay;
    private String paymentStatus;
    private Integer deliveryAddress;

    @OneToMany(mappedBy = "orders")
    private List<OrdersDetails> ordersDetailsList = new ArrayList<>();

    public void setOrderDetailList(OrdersDetails ordersDetails){
        this.ordersDetailsList.add(ordersDetails);
    }
}
