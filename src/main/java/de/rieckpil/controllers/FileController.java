package de.rieckpil.controllers;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/upload-file")
@Slf4j
public class FileController {

  @GetMapping
  public String getUploadForm() {
    return "uploadForm";
  }

  @PostMapping
  public String storeUploadedFile(@RequestParam("name") String fileName,
      @RequestParam("file") MultipartFile file, Model model) throws IOException {

    for (Byte fileByte : file.getBytes()) {
      System.out.print(fileByte.byteValue() + " ");
    }

    log.info(
        String.format("Uploaded file '%s' with name '%s' has size of %s bytes and is of type: %s",
            file.getOriginalFilename(), fileName, file.getSize(), file.getContentType()));

    model.addAttribute("message", String.format("File '%s' was successfully uploaded!", fileName));
    return "uploadForm";

  }

}
