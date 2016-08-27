/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.designerpatterns.factory.singleton;

import br.com.furb.programacao.parking.designerpatterns.factory.ClienteCreate;
import br.com.furb.programacao.parking.designerpatterns.factory.ClienteFactory;
import br.com.furb.programacao.parking.exceptions.NotImplementationException;
import br.com.furb.programacao.parking.model.Cliente;
import br.com.furb.programacao.parking.model.Reserva;
import br.com.furb.programacao.parking.model.Vaga;
import br.com.furb.programacao.parking.model.Veiculo;
import br.com.furb.programacao.parking.model.enumerator.TipoCliente;

/**
 *
 * @author Diovani
 */
public class Entidades {

    private static Cliente cliente = null;
    private static Reserva reserva = null;
    private static Vaga vaga = null;
    private static Veiculo veiculo = null;

    public static Cliente getCliente(TipoCliente tipoCliente) {
        if (cliente == null) {
            ClienteCreate clienteCreate = new ClienteFactory(tipoCliente);
            try {
                cliente = clienteCreate.create(tipoCliente);
            } catch (NotImplementationException nie) {
               nie.printStackTrace();
            }
            return cliente;
        }
        return cliente;
    }

    public static Reserva getReserva() {
        if (reserva == null) {
            reserva = new Reserva();
        }
        return reserva;
    }

    public static Vaga getVaga() {
        if (vaga == null) {
            vaga = new Vaga();
        }
        return vaga;
    }

    public static Veiculo getVeiculo() {
        if (veiculo == null) {
            veiculo = new Veiculo();
        }
        return veiculo;
    }
}
