package com.example.demo.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Util.user;
import com.google.gson.GsonBuilder;
@Service
public class CSVService {
	public String readCsvUsingBufferReader(MultipartFile file) throws Exception {
		String line="";
		String deleimeter=";";
		List<user> Users = new ArrayList<user>();
		try {
			Path tempDir = Files.createTempDirectory("");
			File tempFile = tempDir.resolve(file.getOriginalFilename()).toFile();
			file.transferTo(tempFile);
			BufferedReader reader = new BufferedReader(new FileReader(tempFile));
			reader.readLine();
			while((line=reader.readLine())!=null) {
				//System.out.println("User data : "+line);
				String[] userData=line.split(deleimeter);
				Users.add( new user(userData[0],userData[1],Integer.valueOf(userData[2])));
				//System.out.println("User json : " + new Gson().toJson(user));
			}
			reader.close();
			return new GsonBuilder().setPrettyPrinting().create().toJson(Users).toString();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
