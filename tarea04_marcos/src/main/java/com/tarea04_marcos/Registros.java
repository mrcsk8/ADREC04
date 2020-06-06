/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarea04_marcos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author MARCOS
 */
public class Registros {
  
    private String dateRep;
    private String day;
    private int cases;
    private String countriesAndTerritories;
    private int deaths;
    
    public void setDateRep(String dateRep) {
        this.dateRep = dateRep;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public String getCountriesAndTerritories() {
        return countriesAndTerritories;
    }

    public void setCountriesAndTerritories(String countriesAndTerritories) {
        this.countriesAndTerritories = countriesAndTerritories;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
  
    public Date getDateRep() throws ParseException {
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");       
            return formateador.parse(dateRep);
    }
    
    public String getSDateRep() {        
        return dateRep;
    }
  
}
