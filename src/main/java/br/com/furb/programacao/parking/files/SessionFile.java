package br.com.furb.programacao.parking.files;

public abstract class SessionFile {

	private String rootDirectory;
	private String fileName;
	
	public String getRootDirectory() {
		return rootDirectory;
	}

	public void setRootDirectory(String rootDirectory) {
		this.rootDirectory = rootDirectory;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public abstract void write(String file);
	
	public abstract void write(String file,boolean append);
	
	public abstract StringBuilder readAll();
	
	public abstract String readByConst(String locate);
	
}
