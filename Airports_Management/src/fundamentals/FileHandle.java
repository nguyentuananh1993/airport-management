package fundamentals;

import java.io.*;

public class FileHandle {
	private RandomAccessFile randomaccesssfile;
	
	public FileHandle(String path) {
		try {
			randomaccesssfile = new RandomAccessFile(new File(path), "rw");	} 
		catch (FileNotFoundException e) {} }
	
	public FileHandle(String path, String mode) {
		try {
			randomaccesssfile = new RandomAccessFile(new File(path), mode);	} 
		catch (FileNotFoundException e) {} }
	
	public FileHandle(File file) {
		try {
			randomaccesssfile = new RandomAccessFile(file, "rw");	} 
		catch (FileNotFoundException e) {} }
	
	public FileHandle(File file, String mode) {
		try {
			randomaccesssfile = new RandomAccessFile(file, mode);	} 
		catch (FileNotFoundException e) {} }
	
	public void Close() {
		try {
			randomaccesssfile.close(); }
		catch (IOException e) {} }
	
	public long length() {
		long l = 0;
		try {
			l = randomaccesssfile.length(); }
		catch (IOException e) {} 
		return l; }
	
	public void appendText(String text) {
		long l = length();
		try {
			if(l != 0) text = "\n" + text;
			randomaccesssfile.seek(l);
			randomaccesssfile.write(text.getBytes()); } 
		catch (IOException e) {} }
	
	public void appendTextline(String textline) {
		try {
			textline = "\n" + textline;
			randomaccesssfile.seek(length());
			randomaccesssfile.write(textline.getBytes()); } 
		catch (IOException e) {} }
	
}



