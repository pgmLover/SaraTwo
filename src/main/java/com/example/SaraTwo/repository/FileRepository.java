package com.example.SaraTwo.repository;

import com.example.SaraTwo.entity.Documents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<Documents,Long> {
    List<Documents> findByChecksum(String checkSum);

}
