package com.example.demo.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Service.ExcelService;

@RestController
public class ExcelController {
	@Autowired
	ExcelService excelService;
	
	@PostMapping("/uploadexcel")
	public List<Map<String, String>> upload(@RequestParam("file") MultipartFile file) throws Exception {
		return excelService.upload(file);
		//System.out.println(file);
	}

}