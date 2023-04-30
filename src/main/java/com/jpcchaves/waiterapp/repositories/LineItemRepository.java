package com.jpcchaves.waiterapp.repositories;

import com.jpcchaves.waiterapp.entities.LineItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineItemRepository extends JpaRepository<LineItem, Long> {
}
