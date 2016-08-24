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
import br.com.furb.programacao.parking.model.Cliente;
import br.com.furb.programacao.parking.model.Entidade;
import br.com.furb.programacao.parking.model.Reserva;
import br.com.furb.programacao.parking.model.Vaga;
import br.com.furb.programacao.parking.model.Veiculo;

/**
 *
 * @author Diovani
 */
public class PersistenceFactory {

    public static <T extends Entidade> Persistence<T> getFactory(Class<T> kclasse) {
        try {
            if (kclasse == Cliente.class) {
                return (Persistence<T>) new ClienteDAO();
            }
            if (kclasse == Reserva.class) {
                return (Persistence<T>) new ReservaDAO();
            }
            if (kclasse == Vaga.class) {
                return (Persistence<T>) new VagaDAO();
            }
            if (kclasse == Veiculo.class) {
                return (Persistence<T>) new VeiculoDAO();
            }
            throw new NotImplementationException("Unidade de persistência não está definida.");
        } catch (NotImplementationException notImplementationException) {
            return null;
        }
    }

}
