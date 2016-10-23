/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.designerpatterns;

import java.io.IOException;

import br.com.furb.programacao.parking.dao.Persistence;
import br.com.furb.programacao.parking.exceptions.ValidatePropertyException;
import br.com.furb.programacao.parking.model.Entidade;
import br.com.furb.programacao.parking.model.enumerator.Persistencia;

/**
 *
 * @author Diovani
 */
public abstract class PersistenceFactory {

	public enum TipoClasse {
		CLIENTE, CONFIGURACAO, RESERVA, VAGA, VEICULO,
	}

	public static PersistenceFactory create(TipoClasse tipoClasse) {
		switch (tipoClasse) {
		case CLIENTE:
			return new PersistenciaClienteFactory();
		case CONFIGURACAO:
			return new PersistenciaConfiguracaoFactory();
		case RESERVA:
			return new PersistenciaReservaFactory();
		case VAGA:
			return new PersistenciaVagaFactory();
		case VEICULO:
			return new PersistenciaVeiculoFactory();
		}
		return null;
	}

	public abstract <T extends Entidade> Persistence<T> open(Persistencia persistencia)
			throws IOException, ValidatePropertyException;
}
