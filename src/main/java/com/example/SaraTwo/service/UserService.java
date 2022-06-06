package com.example.SaraTwo.service;

import com.example.SaraTwo.dto.RegisterRequest;
import com.example.SaraTwo.entity.Documents;
import com.example.SaraTwo.entity.User;
import com.example.SaraTwo.repository.FileRepository;
import com.example.SaraTwo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileRepository fileRepository;

    @Value("${file.path}")
    private String path;


    public String register(RegisterRequest registerRequest) {
        userRepository.save(User.builder().name(registerRequest.getName()).email(registerRequest.getEmail())
                .phone(registerRequest.getPhone()).password(registerRequest.getPassword()).build());
        return "User created successfully";
    }

    public boolean fileUpload(MultipartFile file) {
        boolean fileUploadStatus = false;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(file.getBytes(), 0, (int) file.getSize());
            String checkSum = new BigInteger(1, messageDigest.digest()).toString();

            if (isEmpty(checkSum)) {
                log.info("Checksum is empty");
                Files.copy(file.getInputStream(), Paths.get(path + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
                log.info("File copy to path");
            }
            fileRepository.save(Documents.builder().fileName(UUID.randomUUID().toString()).fileSize(file.getSize())
                    .fileType(file.getContentType()).originalFileNAme(file.getOriginalFilename()).checksum(checkSum).build());
            fileUploadStatus = true;
            log.info("file Uploaded Successfully");
        } catch (SizeLimitExceededException exception) {
            log.info(exception.getMessage().toString());
            exception.getMessage().toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileUploadStatus;
    }

    private boolean isEmpty(String checkSum) {
        List<Documents> documentsList = fileRepository.findByChecksum(checkSum);
        if (documentsList.isEmpty()) {
            return true;
        } else
            return false;
    }
}
