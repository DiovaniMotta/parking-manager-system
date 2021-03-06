/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.factory;

import br.com.furb.programacao.parking.dao.Persistence;
import br.com.furb.programacao.parking.dao.VagaDAO;
import br.com.furb.programacao.parking.model.Entidade;
import br.com.furb.programacao.parking.model.enumerator.Persistencia;

/**
 *
 * @author Diovani
 */
public class VagaFactory extends PersistenceFactory{

    @Override
    public Persistence<? extends Entidade> openSessionFactory(Persistencia persistencia) {
        return new VagaDAO(persistencia);
    }
}
