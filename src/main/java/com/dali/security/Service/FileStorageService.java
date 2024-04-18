package com.dali.security.Service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    byte[] storeFile(MultipartFile file);
}
