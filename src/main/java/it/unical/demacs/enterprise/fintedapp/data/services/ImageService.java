package it.unical.demacs.enterprise.fintedapp.data.services;

import java.io.IOException;

public interface ImageService {
	public String compress(String str) throws IOException;
	
	String decompress(String compressedStr) throws IOException;
}
