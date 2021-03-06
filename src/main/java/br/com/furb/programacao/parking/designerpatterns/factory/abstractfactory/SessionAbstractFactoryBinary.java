package br.com.furb.programacao.parking.designerpatterns.factory.abstractfactory;

import br.com.furb.programacao.parking.files.SessionFile;
import br.com.furb.programacao.parking.files.SessionFileBinary;

public class SessionAbstractFactoryBinary extends SessionAbstractFactory {

	@Override
	public SessionFile getSession() {
		return new SessionFileBinary();
	}

}
