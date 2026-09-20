package vn.iotstar.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.iotstar.service.IStorageService;
import java.io.InputStream;
import java.nio.file.*;

@Service
public class StorageServiceImpl implements IStorageService {
    private final Path rootLocation = Paths.get("uploads");

    public StorageServiceImpl() {
        try {
            Files.createDirectories(rootLocation);
        } catch (Exception e) {
            throw new RuntimeException("Không thể tạo thư mục lưu ảnh", e);
        }
    }

    @Override
    public String getSorageFilename(MultipartFile file, String id) {
        String ext = org.springframework.util.StringUtils.getFilenameExtension(file.getOriginalFilename());
        return id + "." + ext;
    }

    @Override
    public void store(MultipartFile file, String storedFilename) {
        try {
            if (file.isEmpty()) { throw new RuntimeException("File rỗng."); }
            Path destinationFile = this.rootLocation.resolve(Paths.get(storedFilename)).normalize().toAbsolutePath();
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi lưu file.", e);
        }
    }
}