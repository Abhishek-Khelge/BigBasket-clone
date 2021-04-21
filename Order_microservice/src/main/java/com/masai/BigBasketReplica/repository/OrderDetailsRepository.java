package com.masai.BigBasketReplica.repository;

import com.masai.BigBasketReplica.entity.OrdersDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrdersDetails,Integer> {
}
