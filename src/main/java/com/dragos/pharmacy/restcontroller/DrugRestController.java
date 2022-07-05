package com.dragos.pharmacy.restcontroller;

import com.dragos.pharmacy.exception.DrugNotFoundException;
import com.dragos.pharmacy.exception.DrugUpdateInvalid;
import com.dragos.pharmacy.model.Drug;
import com.dragos.pharmacy.dto.DrugDTO;
import com.dragos.pharmacy.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/drugs")
public class DrugRestController {
    @Autowired
    private DrugService drugService;

    @GetMapping
    public List<Drug> findAll() {
        return drugService.findAll();
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
    public Drug save(final @RequestBody Drug drug) {
        return drugService.save(drug);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) throws DrugNotFoundException {
        return drugService.delete(id);
    }

    @PutMapping
    public Drug update(final @RequestBody Drug drug) throws DrugNotFoundException{
        return drugService.update(drug);
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
