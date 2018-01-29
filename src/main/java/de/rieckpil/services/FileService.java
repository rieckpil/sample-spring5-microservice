package de.rieckpil.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

  public void saveFile(String name, MultipartFile file) throws IOException, NoSuchAlgorithmException;

}
