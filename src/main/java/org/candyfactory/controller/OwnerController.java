package org.candyfactory.controller;

import jakarta.validation.Valid;
import org.candyfactory.dao.OwnerDao;
import org.candyfactory.model.Owner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerDao ownerDao;

    public OwnerController(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("owners", ownerDao.index());
        return "owner/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("owner", ownerDao.show(id));
        return "owner/show";
    }

    @GetMapping("/new")
    public String newOwner(@ModelAttribute("owner") Owner owner) {
        return "owner/new";
    }

    @PostMapping
    public String create(@ModelAttribute("owner") @Valid Owner owner, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "owner/new";
        }
        ownerDao.save(owner);
        return "redirect:/owner";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("owner", ownerDao.show(id));
        return "owner/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("owner") @Valid Owner owner, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "owner/edit";
        }
        ownerDao.update(id, owner);
        return "redirect:/owner";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        ownerDao.delete(id);
        return "redirect:/owner";
    }
}
