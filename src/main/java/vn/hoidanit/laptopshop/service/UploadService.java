package vn.hoidanit.laptopshop.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;

@Service
public class UploadService {
    private final ServletContext servletContext;

    public UploadService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public String handleSaveFileUpload(MultipartFile file, String targetFolder) {
        String finalName = "";
        try {
            byte[] bytes = file.getBytes();
            // get root path to webapp => webapp/resources/images
            String rootPath = this.servletContext.getRealPath("/resources/images");
            // get directory
            File dir = new File(rootPath + File.separator + targetFolder);
            // if directory called avatar does not exist => create the directory named
            // avatar
            if (!dir.exists())
                dir.mkdirs();
            // Create the file on server
            finalName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            File serverFile = new File(dir.getAbsolutePath() + File.separator + finalName);
            try (BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile))) {
                stream.write(bytes);
                stream.close();
            }
        } catch (IOException ex) {
        }
        return finalName;
    }
}
