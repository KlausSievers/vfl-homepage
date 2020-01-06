/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Sievers
 */
public class BCryptGenerator {
      public static void main(String[] args) {
       BCryptPasswordEncoder generator = new BCryptPasswordEncoder();
        System.out.println(generator.encode("ralf"));
    }
}
