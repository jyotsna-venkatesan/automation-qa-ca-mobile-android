package org.CATests.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {

    public static Map<String, String> getTestData(String filePath, int rowNumber) {
        Map<String, String> data = new HashMap<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0); // Assuming first row is header
            Row dataRow = sheet.getRow(rowNumber);

            for (Cell headerCell : headerRow) {
                int columnIndex = headerCell.getColumnIndex();
                Cell dataCell = dataRow.getCell(columnIndex);

                String headerValue = headerCell.getStringCellValue();
                String dataValue = getCellValueAsString(dataCell);

                data.put(headerValue, dataValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    double numericValue = cell.getNumericCellValue();
                    if (numericValue == Math.floor(numericValue)) {
                        return String.valueOf((long) numericValue); // Treat as long if integer value
                    } else {
                        return String.valueOf(numericValue); // Treat as double if fractional value
                    }
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                switch (cell.getCachedFormulaResultType()) {
                    case STRING:
                        return cell.getRichStringCellValue().getString();
                    case NUMERIC:
                        double numericValue = cell.getNumericCellValue();
                        if (numericValue == Math.floor(numericValue)) {
                            return String.valueOf((long) numericValue); // Treat as long if integer value
                        } else {
                            return String.valueOf(numericValue); // Treat as double if fractional value
                        }
                    case BOOLEAN:
                        return String.valueOf(cell.getBooleanCellValue());
                    default:
                        return "";
                }
            case BLANK:
                return "";
            default:
                return "";
        }
    }

    public static int getTotalRows(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            int nonEmptyRowCount = 0;

            for (int i = 1; i <= lastRowNum; i++) { // Assuming first row is header
                Row row = sheet.getRow(i);
                if (row != null && row.getPhysicalNumberOfCells() > 0 && !isRowEmpty(row)) {
                    nonEmptyRowCount++;
                }
            }
            return nonEmptyRowCount;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static boolean isRowEmpty(Row row) {
        for (Cell cell : row) {
            if (cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    public static void writeDataToProperties(String excelFilePath, int rowNumber) {
        Map<String, String> data = getTestData(excelFilePath, rowNumber);
        ConfigLoader configLoader = new ConfigLoader();

        for (Map.Entry<String, String> entry : data.entrySet()) {
            configLoader.setProperty(entry.getKey(), entry.getValue());
        }
    }
}