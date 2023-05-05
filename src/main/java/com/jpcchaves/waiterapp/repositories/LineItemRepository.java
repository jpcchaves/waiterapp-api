package com.jpcchaves.waiterapp.repositories;

import com.jpcchaves.waiterapp.entities.LineItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LineItemRepository extends JpaRepository<LineItem, Long> {
    List<LineItem> getLineItemsByProduct_Id(Long productId);
}
