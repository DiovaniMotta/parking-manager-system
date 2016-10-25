package br.com.furb.programacao.parking.files;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

public class SessionFileBinary extends SessionFile {

	@Override
	public void writeAll(String file) throws IOException {
		ObjectOutputStream dos = new ObjectOutputStream(new FileOutputStream(toAbsolutePath()));
		Object[] objects = new Gson().fromJson(file, Object[].class);
		dos.writeObject(Arrays.asList(objects));
		dos.flush();
		dos.close();
	}

	@Override
	@SuppressWarnings("unchecked")
	public StringBuilder readAll() throws IOException {
		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(toAbsolutePath()));
		try {
			List<Object> objects = (List<Object>) inputStream.readObject();
			String json = new Gson().toJson(objects);
			return new StringBuilder(json);
		} catch (Exception exception) {
			exception.printStackTrace();
			return new StringBuilder();
		} finally {
			inputStream.close();
		}
	}

}
