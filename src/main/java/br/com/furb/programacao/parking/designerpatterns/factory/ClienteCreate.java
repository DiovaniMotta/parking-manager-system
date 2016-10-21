/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.designerpatterns.factory;

import br.com.furb.programacao.parking.exceptions.NotImplementationException;
import br.com.furb.programacao.parking.model.Cliente;
import br.com.furb.programacao.parking.model.enumerator.Ativo;
import br.com.furb.programacao.parking.model.enumerator.TipoCliente;

/**
 * 
 * @author Diovani Bernardi da Motta
 * 
 */
public abstract class ClienteCreate {

    private TipoCliente tipoCliente = null;

    public ClienteCreate(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public abstract Cliente create(TipoCliente tipoCliente) throws NotImplementationException;

    public void put(Ativo ativo, String celular, String cnh, String cpf, String documento, String Endereco, String nome, String telefone, String endereco) throws NotImplementationException {
       try{
    	Cliente cliente = create(tipoCliente);
        cliente.setAtivo(ativo);
        cliente.setCelular(celular);
        cliente.setCnh(cnh);
        cliente.setDocumento(documento);
        cliente.setEndereco(endereco);
        cliente.setNome(nome);
        cliente.setTelefone(telefone);
       }catch(Exception exception){
    	   exception.printStackTrace();
       }
       }
}
