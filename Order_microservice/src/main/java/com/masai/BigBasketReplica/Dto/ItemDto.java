package com.masai.BigBasketReplica.Dto;

import lombok.Data;

@Data
public class ItemDto {
    String itemName;
    Integer itemId;
    Double unitPrice;
    Integer itemQty;
    Double totalPrice;

}
