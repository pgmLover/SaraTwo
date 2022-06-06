package com.example.SaraTwo.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Documents {

    @Id
    @GeneratedValue
    private  Long id;
    private  String originalFileNAme;
    private  String fileName;
    private  String checksum;
    private  Long fileSize;
    private  String fileType;
}
