package com.dragos.pharmacy.service;

import com.dragos.pharmacy.dto.DrugDTO;
import com.dragos.pharmacy.exception.DrugNotFoundException;
import com.dragos.pharmacy.exception.DrugUpdateInvalid;
import com.dragos.pharmacy.model.Drug;
import com.dragos.pharmacy.repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.dragos.pharmacy.util.CopyUtil.copy;


@Service
public class DrugService {
    @Autowired
    private DrugRepository drugRepository;

    public Drug findOne(Long id) throws DrugNotFoundException {
        Optional<Drug> res = drugRepository.findById(id);
        if (res.isPresent()) {
            return res.get();
        }
        throw new DrugNotFoundException("Drug unavailable.");
    }

    public List<Drug> findAll() {
        return drugRepository.findAll();
    }

    public Drug save(Drug drug) {
        drug.setUnitsSell(0);
        return drugRepository.save(drug);
    }

    public boolean delete(Long id) throws DrugNotFoundException {
        if (drugRepository.existsById(id)) {
            drugRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Drug update(Drug drug) throws DrugNotFoundException {
        if (drug.getId() != null) {
            Optional<Drug> dbOptDrug = drugRepository.findById(drug.getId());
            if (dbOptDrug.isPresent()) {
                Drug dbDrug = dbOptDrug.get();
                copy(drug, dbDrug);
                drugRepository.save(dbDrug);
               return dbDrug;
            }

        }
        throw new DrugNotFoundException("Invalid drug ID!");
    }
        /*
    #increase supply
    #decrease supply
     */

    public Drug increase(DrugDTO drugDTO, String field) throws DrugNotFoundException {
        Long id = drugDTO.getId();
        Drug drug;
        if (id != null) {
            Optional<Drug> dbOptDrug = drugRepository.findById(id);

            if (dbOptDrug.isPresent()){
                drug = dbOptDrug.get();
            } else {
                throw new DrugNotFoundException("Invalid drug ID");
            }

            if (field.equalsIgnoreCase("price")) {
                Double aux = drug.getPrice() + drugDTO.getValue();
                drug.setPrice(aux);
                drugRepository.save(drug);
                return drug;
            }
            if (field.equalsIgnoreCase("supply")) {
                Integer aux = drug.getSupply() + (int) drugDTO.getValue();
                drug.setSupply(aux);
                drugRepository.save(drug);
                return drug;
            }
        }

        throw new DrugNotFoundException("Drug ID can not be null");
    }

    public Drug decrease(DrugDTO drugDTO, String field) throws DrugNotFoundException, DrugUpdateInvalid {
        Long id = drugDTO.getId();
        Drug drug;
        if (id != null) {
            Optional<Drug> dbOptDrug = drugRepository.findById(id);
            if (dbOptDrug.isPresent()){
                drug = dbOptDrug.get();
            } else {
                throw new DrugNotFoundException("Invalid drug ID");
            }
            if (field.equalsIgnoreCase("price")) {
                double aux = drug.getPrice() - drugDTO.getValue();
                if (aux < 0) {
                    throw new DrugUpdateInvalid("Invalid update!");
                }
                drug.setPrice(aux);
                drugRepository.save(drug);
                return drug;
            }
            if (field.equalsIgnoreCase("supply")) {
                int aux = drug.getSupply() - (int) drugDTO.getValue();
                if (aux < 0) {
                    throw new DrugUpdateInvalid("Invalid update!");
                }
                drug.setSupply(aux);
                drugRepository.save(drug);
                return drug;
            }
        }
        throw new DrugNotFoundException("Drug ID can not be null");
    }

}
