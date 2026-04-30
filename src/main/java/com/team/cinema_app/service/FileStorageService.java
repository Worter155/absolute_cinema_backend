package com.team.cinema_app.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String savePoster(UUID movieId, MultipartFile file) {

        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            Path movieFolder = Paths.get(uploadDir, "movies", movieId.toString());
            Files.createDirectories(movieFolder);

            Path targetLocation = movieFolder.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation);

            return targetLocation.toString();

        } catch (IOException ex) {
            throw new RuntimeException("Не удалось сохранить файл", ex);
        }
    }

    public void deletePoster(String path) {
        try {
            if (path != null) {
                Files.deleteIfExists(Paths.get(path));
            }
        } catch (IOException ex) {
            throw new RuntimeException("Не удалось удалить файл", ex);
        }
    }
}
