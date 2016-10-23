package br.com.furb.programacao.parking.files;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SessionFileBinary extends SessionFile {

	@Override
	public void writeAll(String file) throws IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(toAbsolutePath()));
		dos.writeUTF(file);
		dos.flush();
		dos.close();
	}
}
