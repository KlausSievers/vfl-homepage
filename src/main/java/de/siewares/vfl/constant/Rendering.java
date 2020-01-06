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
public enum Rendering {
  NONE(""), BACKGROUND("background"), INVERSE_BACKGROUND("inverse-background");

  private final String value;

  private Rendering(String value) {
    this.value = value;
  }
}
