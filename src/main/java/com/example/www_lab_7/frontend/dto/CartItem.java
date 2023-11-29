package com.example.www_lab_7.frontend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.www_lab_7.backend.models.Product;

import java.io.Serializable;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class CartItem implements Serializable {
    private Product product;
    private int amount;
}
