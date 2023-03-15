package com.example.nvclothes.controller;

import com.example.nvclothes.entity.products.Product;
import com.example.nvclothes.service.TShirtEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class TShirtsController {

    @Autowired
    private TShirtEntityService tShirtService;

    @Autowired
    private SpringTemplateEngine templateEngine;

    private List<Product> searchedList = null;

    @GetMapping("/t-shirts")
    public ModelAndView/*ResponseEntity<String>*/ getAllProducts(ModelAndView modelAndView){
        List<Product> productList = new ArrayList<>();
        if (searchedList != null) {
            Collections.copy(searchedList, productList);
        } else {
            productList.addAll(tShirtService.getAllTShirtEntities());
        }
        modelAndView.setViewName("t-shirts");
        modelAndView.addObject("productList", productList);
        return modelAndView;
    }

    @PostMapping("/t-shirts/search")
    public ModelAndView searchProducts(@RequestParam("search") String name) {
        if (searchedList != null) {
            searchedList = tShirtService.searchWithFilter(searchedList, name);
        } else {
            searchedList = tShirtService.searchProducts(name);
        }

        ModelAndView modelAndView = new ModelAndView("t-shirts");
        modelAndView.addObject("productList", searchedList);
        return modelAndView;
    }

    @PostMapping("/t-shirts/filtered")
    public ModelAndView filterProducts(@RequestParam("productType") String productType, @RequestParam("costFrom") String costFrom,
                                       @RequestParam("costTo") String costTo,
                                       @RequestParam("size") String size, @RequestParam("brand") String brand){
        if (searchedList == null){
            searchedList.addAll(tShirtService.getAllTShirtEntities());
        }
        searchedList = tShirtService.filter(searchedList, size, costFrom, costTo,brand,productType);
        ModelAndView modelAndView = new ModelAndView("t-shirts");
        modelAndView.addObject("productList", searchedList);
        return modelAndView;
    }

    @PostMapping("/t-shirts/clear")
    public ModelAndView clearFilters(){
        searchedList.addAll(tShirtService.getAllTShirtEntities());
        ModelAndView modelAndView = new ModelAndView("t-shirts");
        modelAndView.addObject("productList", searchedList);
        return modelAndView;
    }

    @PostMapping("/t-shirts/sort")
    public ModelAndView sortProducts(@RequestParam("sortType") String sortType){
        List<Product> productList = new ArrayList<>();
        productList.addAll(tShirtService.getAllTShirtEntities());
        tShirtService.sortProducts(productList, sortType);
        ModelAndView modelAndView = new ModelAndView("t-shirts");
        modelAndView.addObject("productList", productList);
        return modelAndView;
    }

    @PostMapping("/t-shirts/add-to-cart")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ModelAndView addToCart(@RequestParam("productId") Long productId, @RequestParam("productType") String productType){
        tShirtService.addToCart(productId, productType);
        ModelAndView modelAndView = new ModelAndView("t-shirts");
        List<Product> productList = new ArrayList<>();
        productList.addAll(tShirtService.getAllTShirtEntities());
        modelAndView.addObject("productList", productList);
        return modelAndView;
    }
}
