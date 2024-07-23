package org.candyfactory.controller;

import org.candyfactory.dao.ProductTypeDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/producttype")
public class ProductTypeController {
    private final ProductTypeDao productTypeDao;

    public ProductTypeController(ProductTypeDao productTypeDao) {
        this.productTypeDao = productTypeDao;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("producttype", productTypeDao.index());
        return "producttype/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("producttype", productTypeDao.show(id));
        return "producttype/show";
    }

}
