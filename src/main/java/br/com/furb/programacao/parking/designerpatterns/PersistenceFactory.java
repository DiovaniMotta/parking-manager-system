/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.designerpatterns;

import br.com.furb.programacao.parking.dao.ClienteDAO;
import br.com.furb.programacao.parking.dao.Persistence;
import br.com.furb.programacao.parking.dao.ReservaDAO;
import br.com.furb.programacao.parking.dao.VagaDAO;
import br.com.furb.programacao.parking.dao.VeiculoDAO;
import br.com.furb.programacao.parking.exceptions.NotImplementationException;
import br.com.furb.programacao.parking.model.Entidade;

/**
 *
 * @author Diovani
 */
public class PersistenceFactory {

    public static Persistence<? extends Entidade> getFactory(Class<?> klasse) throws NotImplementationException{
        if(klasse == ClienteDAO.class)
            return new ClienteDAO();
        if(klasse == ReservaDAO.class)
            return new ReservaDAO();
        if(klasse == VagaDAO.class)
            return new VagaDAO();
        if(klasse == VeiculoDAO.class)
            return new VeiculoDAO();
        throw  new NotImplementationException("Unidade de persistência não está definida.");
    }
    
}
