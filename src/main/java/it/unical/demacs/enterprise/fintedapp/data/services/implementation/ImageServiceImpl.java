package it.unical.demacs.enterprise.fintedapp.data.services.implementation;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.springframework.stereotype.Service;

import it.unical.demacs.enterprise.fintedapp.data.services.ImageService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

	@Override
	public String compress(String str) throws IOException {
		if(str == null || str.length() == 0)
			return str;
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(out);
		
		gzip.write(str.getBytes());
		gzip.close();
		
        String outStr = Base64.getEncoder().encodeToString(out.toByteArray());
        return outStr;
	}

	@Override
	public String decompress(String compressedStr) throws IOException {
	    if (compressedStr == null) {
	        return null;
	    }

	    String outStr = "";
	    byte[] compressed = Base64.getDecoder().decode(compressedStr);
	    GZIPInputStream gzip = new GZIPInputStream(new ByteArrayInputStream(compressed));
	    BufferedReader br = new BufferedReader(new InputStreamReader(gzip, "UTF-8"));

	    String line;
	    while ((line = br.readLine()) != null) {
	        outStr += line;
	    }
	    br.close();
	    gzip.close();

	    return outStr;
	}

}
