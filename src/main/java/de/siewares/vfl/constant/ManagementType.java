/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.constant;

/**
 *
 * @author Sievers
 */
public enum ManagementType {
 COMPLETE(1), SENIOR(2), YOUTH(3);

 private int value;
 
  private ManagementType(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
  
  
}
