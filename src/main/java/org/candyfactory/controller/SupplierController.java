package org.candyfactory.controller;

import org.candyfactory.dao.SupplierDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {
    private final SupplierDao supplierDao;

    public SupplierController(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("suppliers", supplierDao.index());
        return "supplier/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("supplier", supplierDao.show(id));
        return "supplier/show";
    }

}
