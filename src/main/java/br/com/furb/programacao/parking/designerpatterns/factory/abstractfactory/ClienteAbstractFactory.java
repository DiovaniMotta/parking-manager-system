/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.designerpatterns.factory.abstractfactory;

import br.com.furb.programacao.parking.exceptions.NotImplementationException;
import br.com.furb.programacao.parking.model.Cliente;
import br.com.furb.programacao.parking.model.enumerator.Ativo;
import br.com.furb.programacao.parking.model.enumerator.TipoCliente;

/**
 *
 * @author Diovani
 */
public abstract class ClienteAbstractFactory {

    public static ClienteAbstractFactory create(TipoCliente tipoCliente) throws NotImplementationException{
        switch(tipoCliente){
            case FISICO:
                    return new ClienteAbstractFactoryFisico();
            case JURIDICO:
                    return new ClienteAbstractFactoryJuridico();
        }
        throw new NotImplementationException("Fabrica Não Criada.");
    }
    
    
    public abstract Cliente getCliente(String nome,String endereco, String cnh, String telefone, String celular,Integer ID, Ativo ativo);
   
}
