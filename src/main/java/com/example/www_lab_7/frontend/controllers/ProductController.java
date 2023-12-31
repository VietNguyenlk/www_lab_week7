package com.example.www_lab_7.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.www_lab_7.backend.models.Product;
import  com.example.www_lab_7.backend.repositories.ProductRepository;
import  com.example.www_lab_7.backend.services.ProductServices;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
//@RequestMapping("/admin")
public class ProductController {
    @Autowired
    private ProductServices productServices;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public String showProductListPaging(
            HttpSession session,
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        Page<Product> candidatePage = productServices.findPaginated(currentPage - 1,
                pageSize, "name", "asc");

        model.addAttribute("productPage", candidatePage);

        int totalPages = candidatePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "product/listing";
    }

    @GetMapping("/products/show-add-form")
    public String add(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "product/add-form";
    }

    @PostMapping("/products/add")
    public String addCandidate(
            @ModelAttribute("product") Product product,
            BindingResult result, Model model) {
        productRepository.save(product);
        return "redirect:/products";
    }

    //    @DeleteMapping("/products/delete/{id}")
    @GetMapping("/products/delete/{id}")
    public String addCandidate(@PathVariable("id") long id) {
        Product product = productRepository.findById(id).orElse(new Product());
        productRepository.delete(product);
        return "redirect:/products";
    }

}
