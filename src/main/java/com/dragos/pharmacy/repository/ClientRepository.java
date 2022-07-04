package com.dragos.pharmacy.repository;

import com.dragos.pharmacy.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    //CRUD - CREATE UPDATE + ALTELE
    @Query("SELECT u FROM client u WHERE u.age >= ?1")
    List<Client> ageGreaterThan(Integer age);

//    @Modifying
//    @Query("TRUNCATE TABLE ?1")
//    void emptyTable(String table);
    /*
        @Query("SELECT u FROM client u WHERE u.age >= ?1, ?2, 3?") <---- referintele din linia18
        List<Client> ageGreaterThan(Integer age, int a, int b);
     */
}
