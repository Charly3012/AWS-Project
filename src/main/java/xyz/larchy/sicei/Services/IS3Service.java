package xyz.larchy.sicei.Services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IS3Service {
    String uploadPerfilPhotos(MultipartFile file) throws IOException;
}
