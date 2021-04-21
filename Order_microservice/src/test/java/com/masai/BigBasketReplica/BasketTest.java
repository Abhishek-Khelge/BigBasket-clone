package com.masai.BigBasketReplica;

import com.masai.BigBasketReplica.entity.Basket;
import com.masai.BigBasketReplica.repository.BasketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class BasketTest {

    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private TestEntityManager testEntityManager;
    @Test
    public void addOneItemToBasket()
    {
        Users findUser = testEntityManager.find(Users.class,1);
        Items findItem = testEntityManager.find(Items.class,2);
        Basket basketOne = new Basket();
        basketOne.setUsers(findUser);
        basketOne.setItems(findItem);
        basketOne.setQuantity(2);
        Basket saveItem = basketRepository.save(basketOne);
        assert(saveItem.getBasketId()>0);
    }

    @Test
    public void getbasketByUser()
    {
        //Users users = new Users(5);
        //List<Basket> baskets = BasketRepository.findByUser(users);
    }
}
