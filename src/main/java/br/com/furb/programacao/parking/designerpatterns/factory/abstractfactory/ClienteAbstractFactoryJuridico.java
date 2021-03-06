/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.designerpatterns.factory.abstractfactory;

import java.util.ArrayList;

import br.com.furb.programacao.parking.model.Cliente;
import br.com.furb.programacao.parking.model.ClienteJuridico;
import br.com.furb.programacao.parking.model.enumerator.Ativo;

/**
 *
 * @author Diovani
 */
public class ClienteAbstractFactoryJuridico extends ClienteAbstractFactory {

	@Override
	public Cliente getCliente(String nome, String endereco, String cnh, String telefone, String celular, Integer ID,
			Ativo ativo) {
		try {
			return new ClienteJuridico(nome, endereco, cnh, telefone, celular, new ArrayList<>(), ID, ativo);
		} catch (Exception exception) {
			return null;
		}
	}
}
