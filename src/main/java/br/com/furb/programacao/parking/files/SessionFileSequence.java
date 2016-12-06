package br.com.furb.programacao.parking.files;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SessionFileSequence extends SessionFile {

	@Override
	public void writeAll(String file) throws IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(toAbsolutePath()));
		dos.writeBytes(file);
		dos.flush();
		dos.close();
	}

	@Override
	public StringBuilder readAll() throws IOException {
		Path path = Paths.get(toAbsolutePath());
		String dados = new String(Files.readAllBytes(path));
		return new StringBuilder(dados);
	}
	
	
}
