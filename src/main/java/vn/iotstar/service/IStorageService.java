package vn.iotstar.service;

import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {
    String getSorageFilename(MultipartFile file, String id);
    void store(MultipartFile file, String storedFilename);
}