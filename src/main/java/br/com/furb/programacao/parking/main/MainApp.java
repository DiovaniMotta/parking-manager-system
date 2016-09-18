/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.main;

import br.com.furb.programacao.parking.designerpatterns.factory.abstractfactory.ClienteAbstractFactory;
import br.com.furb.programacao.parking.exceptions.NotImplementationException;
import br.com.furb.programacao.parking.model.Cliente;
import br.com.furb.programacao.parking.model.enumerator.Ativo;
import br.com.furb.programacao.parking.model.enumerator.TipoCliente;

/**
 *
 * @author Diovani
 */
public class MainApp {

	public static void main(String[] args) throws NotImplementationException {
		try {
			ClienteAbstractFactory abstractFactory = ClienteAbstractFactory.create(TipoCliente.FISICO);
			Cliente cliente = abstractFactory.getCliente("Diovani Bernardi da Motta", "Rua X","110110100101","919191991","1111","1",Ativo.SIM);
			System.out.println(cliente.toString());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
