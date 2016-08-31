/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.main;

import br.com.furb.programacao.parking.exceptions.NotImplementationException;
import br.com.furb.programacao.parking.factory.PersistenceFactory;
import br.com.furb.programacao.parking.factory.PersistenceFactory.TypePersistence;

/**
 *
 * @author Diovani
 */
public class MainApp {

    public static void main(String[] args) throws NotImplementationException {
        PersistenceFactory.getFactory(TypePersistence.CLIENTE).openSessionFactory();
    }
}
