/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.main;

import java.util.ArrayList;
import java.util.List;

import br.com.furb.programacao.parking.dao.generator.PersistenceSession;
import br.com.furb.programacao.parking.dao.generator.PersistenceSessionSequenceVaga;
import br.com.furb.programacao.parking.exceptions.NotImplementationException;
import br.com.furb.programacao.parking.model.Vaga;
import br.com.furb.programacao.parking.model.enumerator.Ativo;

/**
 *
 * @author Diovani
 */
public class MainApp {

	public static void main(String[] args) throws NotImplementationException {
		try {
			PersistenceSession<Vaga> persistenceSession = new PersistenceSessionSequenceVaga();
			List<Vaga> vagas = new ArrayList<Vaga>();
			for (int x = 1; x <= 100; x++) {
				Vaga vaga = new Vaga(x, x, "vaga " + x);
				vaga.setID(x);
				vaga.setAtivo(Ativo.SIM);
				vagas.add(vaga);
			}
			for (Vaga vaga : vagas)
				persistenceSession.save(vaga);
			Vaga vaga = new Vaga(50, 50, "DIOVANI BERNARDI DA MOTTA");
			vaga.setID(50);
			vaga.setAtivo(Ativo.SIM);
			persistenceSession.merge(vaga);
			Vaga v = new Vaga(59,59, "vaga " + 59);
			v.setID(59);
			v.setAtivo(Ativo.SIM);
			persistenceSession.remove(v);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
