package com.tieto.webapp;

import org.apache.poi.ss.usermodel.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class Music
 */
@WebServlet("/content/Music")
public class MusicServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private void folderToExcel(String sheetName, OutputStream out) throws IOException {
        try (Workbook wb = new org.apache.poi.xssf.usermodel.XSSFWorkbook()) {
            Sheet s = wb.createSheet(sheetName);

            List<String> EHudba = fileList();

            writeListToSheet(s, EHudba, wb);

            s.autoSizeColumn(0);

            wb.write(out);
        }
    }

    private void writeListToSheet(Sheet s, List<String> EHudba, Workbook wb) throws IOException {
        int index = 0;
        for (String i : EHudba) {
            Path p = Paths.get(i);
            Row r = s.createRow(index);

            r.createCell(0).setCellValue(i);

            float result = Math.round(Files.size(p) / (float) 10000) / (float) 100;
            r.createCell(1);
            formatCell(r.createCell(1), wb);
            r.getCell(1).setCellValue(result);

            index++;

        }
    }

    private List<String> fileList() throws IOException {
        List<String> fileNames = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get("c:/Users/zvird/Desktop"))) {
            for (Path path : directoryStream) {
                fileNames.add(path.toString());
            }
        }
        return fileNames;
    }

    private void formatCell(Cell cell, Workbook wb) {

        short format = wb.createDataFormat().getFormat("#,##0 \"MB\"");
        CellStyle style = wb.createCellStyle();
        style.setDataFormat(format);

        cell.setCellStyle(style);

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String fileName = getFileName(request);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Expires:", "0"); // eliminates browser caching
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        OutputStream out = response.getOutputStream();
        folderToExcel(fileName, out);
        out.flush();
    }

    private String getFileName(HttpServletRequest request) {
        final String fileName;
        final String nameParameter = request.getParameter("fileName");
        if (nameParameter == null || nameParameter.isEmpty()) {
            fileName = "ListOfFiles.xlsx";
        } else {
            fileName = nameParameter + ".xlsx";
        }
        return fileName;
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
