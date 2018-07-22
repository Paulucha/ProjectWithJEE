package com.isa.usersengine.com.isa.userengine.cdi;

import com.isa.usersengine.com.isa.userengine.cdi.com.isa.usersengine.exceptions.UserImageNotFound;

import javax.enterprise.context.RequestScoped;
import javax.servlet.http.Part;

import java.io.*;
import java.nio.file.Paths;
import java.util.Properties;

@RequestScoped
public class FileUploadProcessorBean {

    private static final String SETTINGS_FILE = "settings.properties";


    protected String getUploadImageFilesPath() throws IOException {

        Properties settings = new Properties();
        settings.load(Thread.currentThread().getContextClassLoader().getResource(SETTINGS_FILE).openStream());
        return settings.getProperty("Upload.Path.Images");


    }

    public File uploadImageFile(Part filePart) throws IOException, UserImageNotFound {

        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        if (fileName == null || fileName.isEmpty()){
            throw new UserImageNotFound("Nie ma image'a");
        }

        File file = new File(getUploadImageFilesPath() + fileName);

        InputStream fileContent = filePart.getInputStream();
        OutputStream os = new FileOutputStream(file);

        byte[] buffered = new byte[1024];
        int bytesRead;
        while ((bytesRead = fileContent.read(buffered)) != -1) {
            os.write(buffered, 0, bytesRead);
        }
        fileContent.close();
        os.flush();
        os.close();


        return file;
    }

}
