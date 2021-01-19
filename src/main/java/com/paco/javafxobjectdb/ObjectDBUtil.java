/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paco.javafxobjectdb;

import javax.persistence.*;

/**
 *
 * @author paco
 */
public class ObjectDBUtil {
  
  private static EntityManagerFactory emf;
  
  static {
    try {
        emf = Persistence.createEntityManagerFactory("p.odb");
        System.out.println(emf);
    } catch (Exception ex) {
      System.out.println("Initial SessionFactory creation failed." + ex);
    }
  }
  
  public static EntityManagerFactory getEntityManagerFactory() {
    return emf;
  }    
}
