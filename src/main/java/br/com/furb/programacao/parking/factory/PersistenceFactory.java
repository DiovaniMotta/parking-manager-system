/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.factory;

import br.com.furb.programacao.parking.dao.Persistence;
import br.com.furb.programacao.parking.exceptions.NotImplementationException;
import br.com.furb.programacao.parking.model.Entidade;

/**
 *
 * @author Diovani
 */
public abstract class PersistenceFactory {

    public enum TypePersistence{
        CLIENTE,
        RESERVA,
        VAGA,
        VEICULO,
    }
    
    public static PersistenceFactory getFactory(TypePersistence typePersistence) throws NotImplementationException {
        switch(typePersistence){
            case CLIENTE:
                return new ClienteFactory();
            case RESERVA:
                return new ReservaFactory();
            case VAGA:
                return new VagaFactory();
            case VEICULO:
                return new VeiculoFactory();
            default:
                throw new NotImplementationException("Unidade de persistência não está definida.");
        }
    }

    public abstract Persistence<? extends Entidade> openSessionFactory();
}
