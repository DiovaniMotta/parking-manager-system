/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.factory;

import br.com.furb.programacao.parking.dao.Persistence;
import br.com.furb.programacao.parking.dao.VeiculoDAO;
import br.com.furb.programacao.parking.model.Entidade;

/**
 *
 * @author Diovani
 */
public class VeiculoFactory extends PersistenceFactory{

    @Override
    public Persistence<? extends Entidade> openSessionFactory() {
        return new VeiculoDAO();
    }
}
