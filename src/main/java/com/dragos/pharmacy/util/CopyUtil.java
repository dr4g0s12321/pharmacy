package com.dragos.pharmacy.util;

import com.dragos.pharmacy.model.Drug;

public class CopyUtil {
    public static void copy(Drug source, Drug destination) {
        if (source.getPrescription() != null) {
            destination.setPrescription(source.getPrescription());
        }
        if (source.getPrice() != null) {
            destination.setPrice(source.getPrice());
        }
        if (source.getSupply() != null) {
            destination.setSupply(source.getSupply());
        }
        if (source.getName() != null) {
            destination.setName(source.getName());
        }
        if (source.getUnitsSell() != null) {
            destination.setUnitsSell(source.getUnitsSell());
        }
        if (source.getProducer() != null) {
            destination.setProducer(source.getProducer());
        }
    }
//    public static Drug veverita(Drug source, DrugDTO destination, String field) {
//        if (field.equalsIgnoreCase("price")) {
//            Double aux = source.getPrice() + destination.getValue();
//            source.setPrice(aux);
//            drugRepository.save(source);
//            return source;
//        }
//        if (field.equalsIgnoreCase("supply")) {
//            Integer aux = source.getSupply() + (int) destination.getValue();
//            source.setSupply(aux);
//            drugRepository.save(source);
//            return source;
//        }
//    }
}
