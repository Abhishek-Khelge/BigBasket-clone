package com.masai.BigBasketReplica.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "users","items"})
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer basketId;
    private Integer quantity;
    private Integer userId;
    private Integer itemId;

    public Basket(Integer quantity, Integer userId, Integer itemId) {
        this.quantity = quantity;
        this.userId = userId;
        this.itemId = itemId;
    }
}
