package com.example.demo.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.GsonBuilder;

@Service
public class XmlService {
	
	public  String ReaderXmlFile(MultipartFile file) throws Exception{
		BufferedReader reader;
		try {
			Path tempDir = Files.createTempDirectory("");
			File tempFile = tempDir.resolve(file.getOriginalFilename()).toFile();
			file.transferTo(tempFile);
			reader = new BufferedReader(new FileReader(tempFile));
			String Line,Str="";
			//String Str="<user><FirstName>Mohamed Amine</FirstName><LastName>Ben Khemis</LastName><Age>24</Age></user><user><FirstName>Hedi</FirstName><LastName>Bergaoui</LastName><Age>25</Age></user><user><FirstName>Nassima</FirstName><LastName>Dridi</LastName><Age>23</Age></user>"	;
			while((Line=reader.readLine())!=null) {
				Str=Str+Line;
			}
			JSONObject json = XML.toJSONObject(Str);
			reader.close();
            return json.toString();
			//System.out.println(json);
		} catch (IOException e1) {
			 //TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
		
	}
   
}
