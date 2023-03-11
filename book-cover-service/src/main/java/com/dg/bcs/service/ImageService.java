package com.dg.bcs.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

@Service
@Slf4j
public class ImageService {

    @Autowired
    private ResourceLoader resourceLoader;

    public byte[] readImage(String imageFileLocation) {
        byte[] imageRawBytes = new byte[0];
        try {
            Resource resource = resourceLoader.getResource(imageFileLocation);
            imageRawBytes = FileCopyUtils.copyToByteArray(resource.getFile());

        } catch (IOException e) {
            log.error("Failed to read file:{}",imageFileLocation);
            log.error("Exception:",e);
        }

        return imageRawBytes;
    }
}
