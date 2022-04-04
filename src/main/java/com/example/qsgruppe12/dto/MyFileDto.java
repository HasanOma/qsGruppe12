package com.example.qsgruppe12.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
public class MyFileDto implements Serializable {

    private Long id;

    private String name;

    private String address;

    private MultipartFile logo;

}
