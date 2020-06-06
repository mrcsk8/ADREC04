/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarea04_marcos;

import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author MARCOS
 */
public class HandlerXml {
  
    List<Registros> lista;
    public List<Registros> lectorXml(String file){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            RegistrosTabla registros = new RegistrosTabla();
            saxParser.parse(file, registros);
            lista = registros.getLista();
        
    } catch(IOException | SAXException | ParserConfigurationException   ex){
            ex.printStackTrace();
        }
      return lista;     
    }
  
}
