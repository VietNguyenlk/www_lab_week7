package com.example.www_lab_7.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.example.www_lab_7.backend.models.Order;
import com.example.www_lab_7.backend.models.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Order>, JpaSpecificationExecutor<OrderDetail> {
}