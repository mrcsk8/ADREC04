/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarea04_marcos;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author MARCOS
 */
public class RegistrosTabla extends DefaultHandler {
  
    boolean dateRep = false;
        boolean cases = false;
        boolean countriesAndTerritories = false;
        boolean deaths = false;
        boolean day = false;
        Registros reg = new Registros();
        List<Registros> lista = new ArrayList<>();
    
        public List<Registros> getLista(){
            return lista;
        }
        
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equalsIgnoreCase("dateRep")) {
                dateRep = true;
            }
            if (qName.equalsIgnoreCase("day")) {
                day = true;
            }
            if (qName.equalsIgnoreCase("cases")) {
                cases = true;
            }
            if (qName.equalsIgnoreCase("countriesAndTerritories")) {
                countriesAndTerritories = true;
            }
           if (qName.equalsIgnoreCase("deaths")) {
                deaths = true;
            }
        }
        
        @Override
        public void characters(char ch[], int start, int length) throws SAXException {
            if (dateRep) {
                reg.setDateRep(new String(ch, start, length));
                dateRep = false;                        
            }
            if (day) {
                reg.setDay(new String(ch, start, length));
                day = false;                        
            }
            if (cases) {
                reg.setCases(Integer.parseInt(new String(ch, start, length)));
                cases = false;
            }
            if (countriesAndTerritories) {
                reg.setCountriesAndTerritories(new String(ch, start, length));
                countriesAndTerritories = false;
            }
            if (deaths) {
                reg.setDeaths(Integer.parseInt(new String(ch, start, length)));
                deaths = false;
            }
        }
        
        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equals("record")) {
                lista.add(reg);
                reg = new Registros();
            }
        }
  
}
