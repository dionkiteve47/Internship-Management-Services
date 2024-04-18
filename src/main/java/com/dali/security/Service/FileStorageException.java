package com.dali.security.Service;

import java.io.IOException;

public class FileStorageException extends RuntimeException {
    public FileStorageException(String s) {
        super(s);
    }

    public FileStorageException(String s , Throwable cause){
        super(s,cause);
    }
}
