package org.candyfactory.controller;

import jakarta.validation.Valid;
import org.candyfactory.dao.ProductTypeDao;
import org.candyfactory.model.ProductType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/new")
    public String newProductType(@ModelAttribute("producttype") ProductType productType) {
        return "producttype/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid ProductType productType,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "producttype/new";

        productTypeDao.save(productType);
        return "redirect:/producttype";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("producttype", productTypeDao.show(id));
        return "producttype/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("producttype") @Valid ProductType productType,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "producttype/edit";

        productTypeDao.update(id, productType);
        return "redirect:/producttype";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        productTypeDao.delete(id);
        return "redirect:/producttype";
    }

}
