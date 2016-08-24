/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.designerpatterns.factory;

import br.com.furb.programacao.parking.exceptions.NotImplementationException;
import br.com.furb.programacao.parking.model.Cliente;
import br.com.furb.programacao.parking.model.ClienteFisico;
import br.com.furb.programacao.parking.model.ClienteJuridico;
import br.com.furb.programacao.parking.model.enumerator.TipoCliente;

/**
 *
 * @author Diovani
 */
public class ClienteFactory extends ClienteCreate{

    public ClienteFactory(TipoCliente tipoCliente) {
        super(tipoCliente);
    }

    @Override
    public Cliente create(TipoCliente tipoCliente) throws NotImplementationException {
        if(tipoCliente == TipoCliente.FISICO)
            return new ClienteFisico();
        if(tipoCliente == TipoCliente.JURIDICO)
            return new ClienteJuridico();
        throw new NotImplementationException("Tipo de Cliente Não Implementado...");
    }
}
