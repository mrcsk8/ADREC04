/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarea04_marcos;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.*;
import javax.persistence.Temporal;

@Entity
@Table(name="Record")

/**
 *
 * @author MARCOS
 */
public class Record implements Serializable{
  
   @Id
    @Column(name="id",nullable = false, length = 20)
    private int id;
    @Column(name="dateRep")
    @Temporal(javax.persistence.TemporalType.DATE)
    Date dateRep;
    @Column(name="day")
    private String day; 
    @Column(name="cases")
    private int cases;
    @Column(name="countriesAndTerritories")
    private String countriesAndTerritories;
    @Column(name="deaths")
    private int deaths;
    
    public Record(){
    }

    public Record(int id, Date dateRep, String day, int cases, String countriesAndTerritories, int deaths) {
        this.id = id;
        this.dateRep = dateRep;
        this.day = day;
        this.cases = cases;
        this.countriesAndTerritories = countriesAndTerritories;
        this.deaths = deaths;
    }   

    Record(String id, Date dateRep, String day, String countriesAndTerritories, int deaths) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 
  
}
