package com.example.www_lab_7;

import com.example.www_lab_7.backend.enums.ProductStatus;
import com.example.www_lab_7.backend.models.Product;
import com.example.www_lab_7.backend.models.ProductImage;
import com.example.www_lab_7.backend.models.ProductPrice;
import com.example.www_lab_7.backend.repositories.ProductImageRepository;
import com.example.www_lab_7.backend.repositories.ProductPriceRepository;
import com.example.www_lab_7.backend.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import net.datafaker.Faker;
import net.datafaker.providers.base.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Random;

@SpringBootApplication
public class WwwLab7Application {

    public static void main(String[] args) {
        SpringApplication.run(WwwLab7Application.class, args);
    }

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductPriceRepository productPriceRepository;
    @Autowired
    private ProductImageRepository productImageRepository;

    @Bean
    @Transactional
    CommandLineRunner createSampleProducts() {
        return args -> {
            Faker faker = new Faker();
            Random rnd = new Random();
            Device devices = faker.device();
            for (int i = 0; i < 200; i++) {

                Product product = new Product(
                        devices.modelName(),
                        faker.lorem().paragraph(30),
//                        rnd.nextInt(10)%2==0?"Kg":"piece",
                        "piece",
                        devices.manufacturer(),
                        ProductStatus.ACTIVE
                );



                ProductImage img = new ProductImage("assets/img-sample.png", "sample image");
                img.setProduct(product);

                ProductPrice price = new ProductPrice(LocalDateTime.now(), rnd.nextInt(1500), "Note #" + i);
                price.setProduct(product);

                productRepository.save(product);
                productImageRepository.save(img);
                productPriceRepository.save(price);

            }
        };
    }
}
