/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.main;

import br.com.furb.programacao.parking.dao.Persistence;
import br.com.furb.programacao.parking.exceptions.NotImplementationException;
import br.com.furb.programacao.parking.factory.PersistenceFactory;
import br.com.furb.programacao.parking.factory.PersistenceFactory.TypePersistence;
import br.com.furb.programacao.parking.model.Vaga;
import br.com.furb.programacao.parking.model.enumerator.Ativo;
import br.com.furb.programacao.parking.model.enumerator.Persistencia;

/**
 *
 * @author Diovani
 */
public class MainApp {

	public static void main(String[] args) throws NotImplementationException {
		try {
			@SuppressWarnings("unchecked")
			Persistence<Vaga> p = (Persistence<Vaga>) PersistenceFactory.getFactory(TypePersistence.VAGA)
					.openSessionFactory(Persistencia.BINARIO);
			Vaga v = new Vaga(1, Ativo.SIM);
			v.setCodigo(1);
			v.setNumeroVaga(1);
			v.setReferencia("AAAAAAA");
			Vaga v1 = new Vaga(2, Ativo.SIM);
			v1.setCodigo(2);
			v1.setNumeroVaga(2);
			v1.setReferencia("AAAAAAA");
			p.save(v1);
			p.save(v);
			System.out.println(p.findAll().toString());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
