package br.com.furb.programacao.parking.designerpatterns.factory.abstractfactory;

import br.com.furb.programacao.parking.files.SessionFile;
import br.com.furb.programacao.parking.model.enumerator.Persistencia;

public abstract class SessionAbstractFactory {

	public static SessionAbstractFactory create(Persistencia persistencia){
		switch(persistencia){
		case BINARIO:
			return new SessionAbstractFactoryBinary();
		case TEXTO:
			return new SessionAbstractFactorySequence();
		}
		return null;
	}
		
	public abstract SessionFile getSession();
}
