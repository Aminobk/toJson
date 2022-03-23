package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Service.CSVService;

@RestController
public class CsvController {
	@Autowired
	CSVService csvService;
	
	@PostMapping("/uploadcsv")
	public String uploadcsv(@RequestParam("file") MultipartFile file) throws Exception {
		return csvService.readCsvUsingBufferReader(file);
	}

}
