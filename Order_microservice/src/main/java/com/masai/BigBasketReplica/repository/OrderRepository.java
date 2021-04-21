package com.masai.BigBasketReplica.repository;

import com.masai.BigBasketReplica.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer> {
  //  List<Orders> findOrdersByUsers(Users users);
}
