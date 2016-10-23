package br.com.furb.programacao.parking.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import br.com.furb.programacao.parking.exceptions.ValidatePropertyException;

public abstract class SessionFile {

	protected String rootDirectory;
	protected String fileName;

	public void createRootDirectory(String rootDirectory) throws IOException {
		this.rootDirectory = rootDirectory;
		if (!rootDirectory.isEmpty())
			if (!FileUtils.exists(rootDirectory))
				FileUtils.createDirectory(rootDirectory);
	}

	public void createFile(String fileName) throws ValidatePropertyException, IOException {
		if (fileName == null || fileName.isEmpty())
			throw new ValidatePropertyException("O nome do arquivo deve ser informado");
		this.fileName = fileName;
		if (!FileUtils.exists(toAbsolutePath()))
			FileUtils.create(toAbsolutePath());
	}

	protected String toAbsolutePath() {
		return rootDirectory + "\\" + fileName;
	}

	public abstract void writeAll(String file) throws IOException;


	public StringBuilder readAll() throws IOException {
		Path path = Paths.get(toAbsolutePath());
		String dados = new String(Files.readAllBytes(path));
		return new StringBuilder(dados);
	}
}
