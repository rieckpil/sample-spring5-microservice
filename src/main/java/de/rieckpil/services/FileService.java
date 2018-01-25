package de.rieckpil.services;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

  public void saveFile(String name, MultipartFile file) throws IOException;

}
