package com.example.demo.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Util.ExcelUtil;

@Service
public class ExcelService {
	
	@Autowired
	ExcelUtil excelutil;

	public List<Map<String, String>> upload(MultipartFile file) throws Exception {
		//try {
			Path tempDir = Files.createTempDirectory("");
			File tempFile = tempDir.resolve(file.getOriginalFilename()).toFile();
			file.transferTo(tempFile);
			Workbook workbook = WorkbookFactory.create(tempFile);
			Sheet sheet = workbook.getSheetAt(0);
			Supplier<Stream<Row>> rowStreamSupplier = excelutil.getRowStreamSupplier(sheet);
			Row headerRow = rowStreamSupplier.get().findFirst().get();
			List<String> headerCells = excelutil.getStream(headerRow).map(Cell::getStringCellValue).collect(Collectors.toList());
			int colCount = headerCells.size();
			return rowStreamSupplier.get().skip(1)
			.map(row -> {
				List<String> cellList = excelutil.getStream(row).map(cell-> {
					String cellVal = "";
					CellType c=cell.getCellTypeEnum();
					if (c.equals(CellType.STRING)) {
						cellVal = cell.getStringCellValue();
					} else if (c.equals(CellType.NUMERIC)) {
						cellVal = String.valueOf(cell.getNumericCellValue());
						if (DateUtil.isCellDateFormatted(cell)) {
							DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
							Date date = cell.getDateCellValue();
							cellVal = df.format(date);
						}
					}
					return cellVal;
				}).collect(Collectors.toList());
					//return cellVal;
				
						//::getStringCellValue).collect(Collectors.toList());
			return excelutil.cellIteratorSupplier(colCount).get().collect(Collectors.toMap(headerCells::get, cellList::get));	
			//System.out.println(cellMap);
			})
			.collect(Collectors.toList());
		
			
			
		//} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		
	}
}