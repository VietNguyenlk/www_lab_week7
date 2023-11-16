package com.example.www_lab_7.backend.pks;

import lombok.Getter;
import lombok.Setter;
import com.example.www_lab_7.backend.models.Product;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter @Getter
public class ProductPricePK implements Serializable {
    private Product product;
    private LocalDateTime price_date_time;
}
