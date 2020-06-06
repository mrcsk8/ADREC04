/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarea04_marcos;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author MARCOS
 */
public class Main {
  
   static boolean salir = false;
    static Scanner lector = new Scanner(System.in);
    static int opc = 0;
    static String aux = "";
    static Session session = HibernateUtil.getSessionFactory().openSession();
    
    public static void main(String args[]) throws ParseException{
        HandlerXml fichero =new HandlerXml();
        List<Registros> lista;
        lista = fichero.lectorXml("coronavirus.xml");    
        Transaction tran = null;
        int id = 0;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            tran = session.beginTransaction();
            for (int i=0; i<lista.size(); i++) {
                id++;
                if(i % 100 == 0 ) { 
                        session.flush();
                        session.clear();
                    }
                Registros reg = lista.get(i);
                Record record = new Record(id, reg.getDateRep(), reg.getDay(), reg.getCases(), reg.getCountriesAndTerritories(), reg.getDeaths());
                session.save(record);
            }
            tran.commit();
        }catch(HibernateException e){
            e.printStackTrace();
        }
        
        /////MENU///////
         do {
            System.out.println("_____________Bienvenido a la tarea 03 de Acceso a Datos_____________\n                       |_Gestion de datos_|\n");
            System.out.println("1.- Ver datos de paises por numero de casos.\n2.- Ver datos por dia de muertes.\n3.- Salir.\n");            
            aux = lector.nextLine();
            if(comprobarNum(aux)){
                opc = Integer.parseInt(aux);
            }
            switch (opc){
                case 1:
                   ejercicio1();
                 break;
                 case 2:
                    ejercicio2();
                 break;
                 case 3:
                    out();
                 break;
                default:
                    System.out.println("__Opción no valida__");
                 break;
            }            
        }while (salir == false);             
         session.close();   
    }
    
    public static void out(){
        System.out.println("Deseas salir (s/n)?");
        String fuera;
        fuera = lector.nextLine();
        if (fuera.toLowerCase().equals("s")){
            salir = true;
        }
    }
    
    public static boolean comprobarNum(String cadena) {
        boolean resultado;
            try {
                Integer.parseInt(cadena);
                resultado = true;
            } catch (NumberFormatException ex) {                
                resultado = false;
            }
            return resultado;
    }
    
    public static void ejercicio1(){
        System.out .println("Indicar que numero de caso buscar:");
        int cant = lector.nextInt();
        Query query = session.createQuery("SELECT x.countriesAndTerritories, SUM(x.cases) FROM Record x GROUP BY x.countriesAndTerritories HAVING SUM(x.cases) > " + cant);
        List<Object[]> record = query.list();
        record.forEach((valor) -> {
            System.out.println("Pais: " + valor[0]+"\nCasos: "+valor[1]+"\n\n");   
        });
        lector.nextLine();
    }
    
    public static void ejercicio2(){
        Query query = session.createQuery("SELECT x.countriesAndTerritories, max(x.deaths), x.dateRep From Record x GROUP BY x.countriesAndTerritories");
        List<Object[]> record = query.list();
        record.forEach((valor) -> {
            System.out.println("Pais:" +valor[0]+"\nMuertes: "+valor[1]+"\nDía: "+valor[2]+"\n\n");
        });
    }
     
  
}
