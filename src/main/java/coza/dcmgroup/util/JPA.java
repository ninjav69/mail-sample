/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coza.dcmgroup.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ninjav
 */
public class JPA {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static EntityManager em() {
        if (em == null) {
            emf = Persistence.createEntityManagerFactory("persistence-test");
            em = emf.createEntityManager();
        }

        return em;
    }
}
