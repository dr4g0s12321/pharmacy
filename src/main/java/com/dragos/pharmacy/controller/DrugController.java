package com.dragos.pharmacy.controller;

import com.dragos.pharmacy.dto.DrugDTO;
import com.dragos.pharmacy.exception.DrugNotFoundException;
import com.dragos.pharmacy.exception.DrugUpdateInvalid;
import com.dragos.pharmacy.model.Drug;
import com.dragos.pharmacy.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/drugs")
public class DrugController {
    @Autowired
    private DrugService drugService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("drugs", drugService.findAll());
        model.addAttribute("drug", new Drug());
        return "drug";
    }

    @GetMapping("/{id}")
    public Drug findOne(@PathVariable Long id) {
        try {
            return drugService.findOne(id);
        } catch (
                DrugNotFoundException exp
        ) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, exp.getMessage(), exp
            );
        }

    }

    @PostMapping
    public String save(Drug drug, BindingResult result, Model model) {
        drugService.save(drug);
        return "redirect:/drugs";
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) throws DrugNotFoundException {
        return drugService.delete(id);
    }

    @PutMapping
    public String update(Drug drug, BindingResult result, Model model) throws DrugNotFoundException{
        drugService.update(drug);
        return "redirect:/drugs";
    }

    @PutMapping("/increase/{field}")
    public Drug increase(final @PathVariable String field, final @RequestBody DrugDTO drugDTO) throws DrugNotFoundException {
        return drugService.increase(drugDTO, field);
    }

    @PutMapping("/decrease/{field}")
    public Drug decrease(final @PathVariable String field, final @RequestBody DrugDTO drugDTO) throws DrugNotFoundException, DrugUpdateInvalid {
        return drugService.decrease(drugDTO, field);
    }


}
