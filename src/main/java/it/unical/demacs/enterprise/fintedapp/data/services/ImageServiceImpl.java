package it.unical.demacs.enterprise.fintedapp.data.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    public String compress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(str.getBytes(StandardCharsets.UTF_8));
        gzip.close();

        return Base64.getEncoder().encodeToString(out.toByteArray());
    }


    public String decompress(String compressedStr) throws IOException {
        if (compressedStr == null || compressedStr.length() == 0) {
            return "";
        }
        byte[] compressed = Base64.getDecoder().decode(compressedStr);
        GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(compressed));
        byte[] buffer = new byte[1024];
        int len;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        while((len = gis.read(buffer)) != -1){
            out.write(buffer, 0, len);
        }
        return new String(out.toByteArray(), StandardCharsets.UTF_8);
    }

}
