package com.group14.app.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IParseUploadedFile<S> {
	List<S> parseFile(MultipartFile file);
}
