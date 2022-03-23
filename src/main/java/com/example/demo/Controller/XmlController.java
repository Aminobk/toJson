package com.example.demo.Controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Service.CSVService;
import com.example.demo.Service.XmlService;

@RestController
public class XmlController {
	@Autowired
	 XmlService xmlService;
	
	@PostMapping("/uploadxml")
	public String uploadxml(@RequestParam("file") MultipartFile file) throws Exception {
		return xmlService.ReaderXmlFile(file);
	}
}
