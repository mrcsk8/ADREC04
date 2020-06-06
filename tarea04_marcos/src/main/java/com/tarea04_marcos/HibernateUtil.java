/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarea04_marcos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author MARCOS
 */
public class HibernateUtil {
  
  private static SessionFactory sessionFactory;
    
    public static SessionFactory getSessionFactory(){
        FileReader archivo = null;
            try {
                archivo = new FileReader("config.json");
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            
        JsonReader LectorJson = Json.createReader(archivo);
        JsonObject LectorObject = LectorJson.readObject();
        LectorJson.close();
            try {
                archivo.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        JsonObject itemConex = LectorObject.getJsonObject("dbConnection");
        JsonObject itemDrive = LectorObject.getJsonObject("hibernate");
        String address=(itemConex.getString("address"));
        String name=(itemConex.getString("name"));
        String password=(itemConex.getString("password"));
        String user=(itemConex.getString("user"));
        String port=(itemConex.getString("port"));
        String dialect=(itemDrive.getString("dialect"));
        String driver=(itemDrive.getString("driver"));
        String HBM2DDL_AUTO=(itemDrive.getString("HBM2DDL_AUTO"));
        Boolean SHOW_SQL=(itemDrive.getBoolean("SHOW_SQL"));
        
        if(sessionFactory == null){
            try{
                Configuration conf = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER,driver);
                settings.put(Environment.URL,"jdbc:mysql://"+address+":"+port+"/"+name+"?useUnicode=true&useJDBCCompliantTimezoneShift=true"+"&useLegacyDatetimeCode=false&serverTimezone=Europe/Madrid&amp");
                settings.put(Environment.USER,user);
                settings.put(Environment.PASS,password);
                settings.put(Environment.DIALECT,dialect);
                settings.put(Environment.HBM2DDL_AUTO, HBM2DDL_AUTO);
                settings.put(Environment.STATEMENT_BATCH_SIZE, "100");
                conf.setProperties(settings);
                conf.addAnnotatedClass(Record.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
                sessionFactory = conf.buildSessionFactory(serviceRegistry);
            }catch(HibernateException e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }     
  
}
