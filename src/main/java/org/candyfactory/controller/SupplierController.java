package org.candyfactory.controller;

import jakarta.validation.Valid;
import org.candyfactory.dao.SupplierDao;
import org.candyfactory.model.Owner;
import org.candyfactory.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/supplier")
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

    @GetMapping("/new")
    public String newSupplier(@ModelAttribute("supplier") Supplier supplier) {
        return "supplier/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("supplier") @Valid Supplier supplier,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "supplier/new";
        }
        supplierDao.save(supplier);
        return "redirect:/supplier";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("supplier", supplierDao.show(id));
        return "supplier/edit";
    }

    @PatchMapping("{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("supplier") @Valid Supplier supplier,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "supplier/edit";
        }
        supplierDao.update(id, supplier);
        return "redirect:/supplier";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        supplierDao.delete(id);
        return "redirect:/supplier";
    }

}
