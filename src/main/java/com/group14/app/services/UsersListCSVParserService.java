package com.group14.app.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.group14.app.models.AppUser;

@Service
public class UsersListCSVParserService implements IParseUploadedFile<AppUser> {

	@Override
	public List<AppUser> parseFile(MultipartFile file) {
		String[] rows;
		try {
			byte[] bytes = file.getBytes();
			String completeData = new String(bytes);
			rows = completeData.split("\\r?\\n");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		List<AppUser> studentsList = new ArrayList<>();
		for (int i = 0; i < rows.length; i++) {
			String[] columns = rows[i].split(",");
			AppUser user = new AppUser(columns[0], columns[1], columns[2], columns[3]);
			if (i == 0 && !user.isValidUser()) {
				continue;
			}
			studentsList.add(user);
		}
		return studentsList;
	}

}
