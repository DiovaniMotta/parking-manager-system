package br.com.furb.programacao.parking.facede;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.furb.programacao.parking.comparator.ComparatorUtis;
import br.com.furb.programacao.parking.dao.generator.PersistenceSession;
import br.com.furb.programacao.parking.designerpatterns.factory.abstractfactory.PersistenceAbstractFactory;
import br.com.furb.programacao.parking.exceptions.DetachedException;
import br.com.furb.programacao.parking.exceptions.ValidatePropertyException;
import br.com.furb.programacao.parking.model.Cliente;
import br.com.furb.programacao.parking.model.enumerator.Persistencia;

public class ClienteFacede {
	
	public enum Order {
		ENDERECO,
		CELULAR,
		TELEFONE,
		NOME,
		CNH,
	}
	
	private PersistenceSession<Cliente> persistence;
	
	public ClienteFacede() throws IOException, ValidatePropertyException {
		super();
		persistence = PersistenceAbstractFactory.getPersistence(Persistencia.TEXTO).getCliente();
	}

	public boolean verificarExistencia(Cliente cliente) throws IOException, ValidatePropertyException{
		Cliente cli = persistence.findByKey(String.valueOf(cliente.getID()));
		return cli == null ? false : true; 
	}
	
	public boolean salvar(Cliente cliente) throws IOException, ValidatePropertyException{
		boolean existencia = verificarExistencia(cliente);
		if(existencia)
			return atualizar(cliente);
		return persistence.save(cliente);
	}
	
	public boolean atualizar(Cliente cliente) throws IOException, ValidatePropertyException{
		return persistence.merge(cliente);
	}
	
	public boolean remover(Cliente cliente) throws IOException, ValidatePropertyException, DetachedException{
		boolean existencia = verificarExistencia(cliente);
		if(existencia)
			return persistence.remove(cliente);
		throw new DetachedException("Não é possível remover um registro que ainda não está salvo.");
	}
	
	public Cliente buscarPeloID(String ID) throws IOException, ValidatePropertyException{
		return persistence.findByKey(ID);
	}
	
	public List<Cliente> buscarTodos() throws IOException{
		return persistence.findAll();
	}
	
	public List<Cliente> buscarPeloNome(String nome) throws IOException, ValidatePropertyException{
		List<Cliente> clientes = persistence.findAll();
		List<Cliente> ordenar = clientes.stream().filter(c -> c.getNome().contains(nome)).collect(Collectors.toList());
		Comparator<Cliente> comparator = ComparatorUtis.getCliente(Order.NOME);
		Collections.sort(ordenar,comparator);
		return ordenar;
	}
	
	public List<Cliente> buscarPeloEndereco(String endereco) throws IOException, ValidatePropertyException{
		List<Cliente> clientes = persistence.findAll();
		List<Cliente> ordenar = clientes.stream().filter(c -> c.getEndereco().contains(endereco)).collect(Collectors.toList());
		Comparator<Cliente> comparator = ComparatorUtis.getCliente(Order.ENDERECO);
		Collections.sort(ordenar,comparator);
		return ordenar;
	}
	
	public List<Cliente> buscarPelaCNH(String cnh) throws IOException, ValidatePropertyException{
		List<Cliente> clientes = persistence.findAll();
		List<Cliente> ordenar = clientes.stream().filter(c -> c.getCnh().contains(cnh)).collect(Collectors.toList());
		Comparator<Cliente> comparator = ComparatorUtis.getCliente(Order.CNH);
		Collections.sort(ordenar,comparator);
		return ordenar;
	}
	
	public List<Cliente> buscarPelaTelefone(String telefone) throws IOException, ValidatePropertyException{
		List<Cliente> clientes = persistence.findAll();
		List<Cliente> ordenar = clientes.stream().filter(c -> c.getTelefone().contains(telefone)).collect(Collectors.toList());
		Comparator<Cliente> comparator = ComparatorUtis.getCliente(Order.TELEFONE);
		Collections.sort(ordenar,comparator);
		return ordenar;
	}
	
	public List<Cliente> buscarPelaCelular(String telefone) throws IOException, ValidatePropertyException{
		List<Cliente> clientes = persistence.findAll();
		List<Cliente> ordenar = clientes.stream().filter(c -> c.getCelular().contains(telefone)).collect(Collectors.toList());
		Comparator<Cliente> comparator = ComparatorUtis.getCliente(Order.CELULAR);
		Collections.sort(ordenar,comparator);
		return ordenar;
	}
	
	public void imprimirTodos() throws IOException{
		List<Cliente> clientes = persistence.findAll();
		Iterator<Cliente> iterator = clientes.iterator();
		System.out.println(" ===================================================================");
		System.out.println(" =======================RELATÓRIO DE CLIENTES=======================");
		System.out.println(" ===================================================================");
		while(iterator.hasNext()){
			Cliente cliente = iterator.next();
			System.err.println(" ->CLIENTE:"+cliente.getID() +" - "+cliente.getNome());
			System.out.println(" ->TELEFONE:"+cliente.getTelefone());
			System.out.println(" ->CELULAR:"+cliente.getCelular());
			System.out.println(" ->ENDERECO:"+cliente.getEndereco());
			System.out.println(" ->CELULAR:"+cliente.getCelular());
		}
	}
}
