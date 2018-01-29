package de.rieckpil.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import de.rieckpil.domain.UploadFile;
import de.rieckpil.repositories.FileRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

  private final FileRepository fileRepository;

  private static final String STARTING_PATH = "/tmp";

  public FileServiceImpl(FileRepository fileRepository) {
    this.fileRepository = fileRepository;
  }

  @Override
  public void saveFile(String name, MultipartFile file)
      throws IOException, NoSuchAlgorithmException {

    if (file == null) {
      throw new RuntimeException("No file provided!");
    }

    if (!System.getProperty("os.name").contains("Windows")) {
      storeFileToDatabase(name, file);
    }

    storeFileInFilesystem(file, "rieckpil");

  }

  private void storeFileInFilesystem(MultipartFile file, String username)
      throws FileNotFoundException, IOException, NoSuchAlgorithmException {

    String folderName = "fileuploads-" + username;

    createFolderIfNotExists(folderName);

    FileOutputStream outputStream =
        new FileOutputStream(STARTING_PATH + "/" + folderName + "/" + file.getOriginalFilename());
    byte[] fileBytes = file.getBytes();
    outputStream.write(fileBytes);
    outputStream.close();
  }

  private void createFolderIfNotExists(String folderName) {

    File directory = new File(STARTING_PATH + "/" + folderName);

    if (!directory.exists()) {
      log.info(String.format(
          "'%s' folder does not exists, creating the structure for storing uploaded files.",
          folderName));
      directory.mkdirs();
    }

  }

  private void storeFileToDatabase(String name, MultipartFile file) throws IOException {

    Byte[] byteObjects = new Byte[file.getBytes().length];
    int i = 0;

    for (byte b : file.getBytes()) {
      byteObjects[i++] = b;
    }

    UploadFile fileToSave =
        UploadFile.builder().fileName(name).originalFileName(file.getOriginalFilename())
            .contentType(file.getContentType()).size(file.getSize()).file(byteObjects).build();

    fileRepository.save(fileToSave);
  }

}
