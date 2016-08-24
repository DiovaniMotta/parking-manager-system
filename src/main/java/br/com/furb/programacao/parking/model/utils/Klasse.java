package br.com.furb.programacao.parking.model.utils;

/**
 * 
 * @author Diovani Bernardi da Motta
 * Data: 11/05/2015
 * Classe respons�vel por inst�nciar um objeto do atrav�s da classe repassada como parametro
 */
public class Klasse {

	/**
	 * M�todo respons�vel por inst�nciar um novo objeto atrav�s de sua classe 
	 * @param kclasse a classe a qual se deseja instanciar o objeto
	 * @return um objeto do tipo informado criado.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T instancialize(Class<?> kclasse) throws InstantiationException,IllegalAccessException{
		T target = null;
		target = (T) kclasse.newInstance();
		return target;
	}
	
	/**
	 * M�todo respons�vel por inst�nciar um novo objeto atrav�s de sua classe 
	 * @param name o nome qualificado da classe
	 * @return um objeto do tipo informado criado.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public static <T> T instancialize(String name) throws InstantiationException,IllegalAccessException, ClassNotFoundException{
		T target = null;
		target = (T) Class.forName(name).newInstance();
		return target;
	}
}
