package com.jk.util;

import java.io.File;

public class FilesUtil {
	private File theFile;
	private String theFileFileName;
	private String theFileContentType;
	@Override
	public String toString() {
		return "FilesUtil [theFile=" + theFile + ", theFileFileName=" + theFileFileName + ", theFileContentType="
				+ theFileContentType + "]";
	}
	public FilesUtil() {
		super();
	}
	public FilesUtil(File theFile, String theFileFileName, String theFileContentType) {
		super();
		this.theFile = theFile;
		this.theFileFileName = theFileFileName;
		this.theFileContentType = theFileContentType;
	}
	public File getTheFile() {
		return theFile;
	}
	public void setTheFile(File theFile) {
		this.theFile = theFile;
	}
	public String getTheFileFileName() {
		return theFileFileName;
	}
	public void setTheFileFileName(String theFileFileName) {
		this.theFileFileName = theFileFileName;
	}
	public String getTheFileContentType() {
		return theFileContentType;
	}
	public void setTheFileContentType(String theFileContentType) {
		this.theFileContentType = theFileContentType;
	}
}
