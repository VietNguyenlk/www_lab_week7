package com.example.www_lab_7.backend.pks;

import lombok.Getter;
import lombok.Setter;
import com.example.www_lab_7.backend.models.Order;
import com.example.www_lab_7.backend.models.Product;

import java.io.Serializable;

@Setter @Getter
public class OrderDetailPK implements Serializable {
    private Order order;
    private Product product;
}
