package com.masai.BigBasketReplica.repository;

import com.masai.BigBasketReplica.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository <Basket, Integer>{

    @Query(value = "select * from Basket where user_Id=:userId and item_Id=:itemId", nativeQuery = true)
      Basket findBasketByItemAndUser(Integer userId,Integer itemId);

    @Query(value = "select * from Basket where user_Id=:userId",nativeQuery = true)
    List<Basket> findAllByUser(Integer userId);

    @Query(value = "delete from Basket where user_Id=:userId",nativeQuery = true)
    void deleteByUser(Integer userId);
}
