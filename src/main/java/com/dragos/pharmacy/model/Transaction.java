package com.dragos.pharmacy.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="id", insertable = false, updatable = false)
    private Client client;

//    @ManyToOne
//    @JoinColumn(name="id", insertable = false, updatable = false)
//    private Drug drug;
    @OneToMany(mappedBy = "transactions")
    private Set<Drug> drugs;

    @Column(name="BuyUnits")
    private Integer buyUnits;

    @Column(name="TransactionDate")
    @CreationTimestamp
    private Date transactionDate;



    public Transaction() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Integer getBuyUnits() {
        return buyUnits;
    }

    public void setBuyUnits(Integer buyUnits) {
        this.buyUnits = buyUnits;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(Set<Drug> drugs) {
        this.drugs = drugs;
    }
}
