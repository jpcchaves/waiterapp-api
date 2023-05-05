package com.jpcchaves.waiterapp.repositories;

import com.jpcchaves.waiterapp.entities.LineItem;
import com.jpcchaves.waiterapp.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Set<Order> findAllByLineItemsIn(Set<LineItem> lineItems);
}
