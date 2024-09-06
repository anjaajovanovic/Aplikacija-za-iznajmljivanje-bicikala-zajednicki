/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package rs.ac.bg.fon.ai.zajednicki.communication;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public enum Operation implements Serializable {
    LOGIN, ADD_KORISNIK, GET_ALL_KORISNICI, 
    EDIT_KORISNIK, DELETE_KORISNIK, GET_ALL_BICIKLE, 
    GET_ALL_STAVKE_IZNAJMLJIVANJA, ADD_NEW_IZNAJMLJIVANJE, 
    GET_ALL_IZNAJMLJIVANJA, DELETE_IZNAJMLJIVANJE, 
    EDIT_IZNAJMLJIVANJE, GET_ALL_STATISTIKA, GET_KORISNIK, GET_IZNAJMLJIVANJE;
}
