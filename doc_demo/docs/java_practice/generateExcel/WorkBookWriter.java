package generateExcel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;
import java.util.Map;

public class WorkBookWriter {
    private Workbook workbook;
    private Sheet sheet;
    private Row row;
    private Cell cell;
    private int rowIndex = 0;
    private int columnIndex;
    XSSFCellStyle xssfCellStyle;
    private Map<String, XSSFCellStyle> cellStyleMap;

    public WorkBookWriter(String sheetName) {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet(sheetName);
        prepareCellStype();
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public Row createRow() {
        this.columnIndex = 0;
        row = sheet.createRow(rowIndex++);
        return row;
    }

    public Row createRow(int rowIndex) {
        this.rowIndex = rowIndex;
        return createRow();
    }

    public void writeDataToCell(String cellData) {
        cell = row.createCell(columnIndex++);
        cell.setCellStyle(getDefaultCellStyle());
        cell.setCellValue(cellData);
    }


    public void writeHeaders(List<String> headers) {
        for (String headerName : headers) {
            cell = row.createCell(columnIndex);
            cell.setCellStyle(this.getHeaderStyle());
            cell.setCellValue(headerName);
            columnIndex++;
        }

    }

    public void writeHeaders(List<String> headers, XSSFCellStyle headerStyle, int mergeCells, List<String> mergeCellHeaders) {
        for (String headerName : headers) {
            cell = row.createCell(columnIndex);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(headerName);
            columnIndex++;
            if (mergeCellHeaders.contains(headerName)) {
                columnIndex += mergeCells;
            }
        }
    }

    public void writeHeaders(List<String> headers, List<Integer> headesWidth) {
        for (int i = 0; i < headers.size(); i++) {
            cell = row.createCell(columnIndex);
            cell.setCellStyle(this.getHeaderStyle(headesWidth.get(i)));
            cell.setCellValue(headers.get(i));
            columnIndex++;
        }
    }

    public void writeDataToCell(String cellData, XSSFCellStyle cellStyle) {
        cell = row.createCell(columnIndex++);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(cellData);
    }

    public void writeDataToCell(String cellData, int columnIndex) {
        this.columnIndex = columnIndex;
        writeDataToCell(cellData);
    }

    public void writeDataToCell(String cellData, int columnIndex, XSSFCellStyle cellStyle) {
        this.columnIndex = columnIndex;
        writeDataToCell(cellData, cellStyle);
    }

    public void writeDataToCell(Long cellData) {
        cell = row.createCell(columnIndex++);
        cell.setCellStyle(getDefaultCellStyle());
        cell.setCellValue(cellData);
    }

    public void writeDataToCell(Double cellData) {
        cell = row.createCell(columnIndex++);
        cell.setCellStyle(getDefaultCellStyle());
        cell.setCellValue(cellData);
    }

    public void writeDataToCell(Double cellData, XSSFCellStyle cellStyle) {
        cell = row.createCell(columnIndex++);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(cellData);
    }

//    public void writeDataToCell(Long cellData, int columnIndex) {
//        this.columnIndex = columnIndex;
//        writeDataToCell(cellData);
//    }
//
//    public void writeDataToCell(Long cellData, int columnIndex, XSSFCellStyle xssfCellStyle) {
//        this.columnIndex = columnIndex;
//        writeDataToCell(cellData, xssfCellStyle);
//    }

    private XSSFCellStyle createXSSFCellStyle(Workbook workbook) {
        XSSFCellStyle xssfCellStyle = (XSSFCellStyle) workbook.createCellStyle();
        xssfCellStyle.setBorderLeft(BorderStyle.THIN);
        xssfCellStyle.setBorderRight(BorderStyle.THIN);
        xssfCellStyle.setBorderTop(BorderStyle.THIN);
        xssfCellStyle.setBorderBottom(BorderStyle.THIN);
        return xssfCellStyle;

    }

    public XSSFCellStyle getHeaderStyle(){
        workbook.getSheetAt(0).setColumnWidth(this.columnIndex,10000);
        return cellStyleMap.get("header_cell_style");
    }

    public XSSFCellStyle getHeaderStyle(int width){
        workbook.getSheetAt(0).setColumnWidth(this.columnIndex,width);
        return cellStyleMap.get("header_cell_style");
    }

    public XSSFCellStyle getDefaultCellStyle(){

        return cellStyleMap.get("default_cell_style");
    }

    public XSSFCellStyle getDefaultCellStyleForTotalRow(){

        return cellStyleMap.get("default_cell_style_for_total_row");


    }
    public XSSFCellStyle getNumberCellStyle(){

        return cellStyleMap.get("number_cell_style");
    }

    public XSSFCellStyle getNumberCellStyleForTotalRow(){

        return cellStyleMap.get("number_cell_style_for_total_row");


    }

    public XSSFCellStyle getAmountCellStyle(){

        return cellStyleMap.get("amount_cell_style");
    }

    public XSSFCellStyle getAmountCellStyleForTotalRow(){

        return cellStyleMap.get("amount_cell_style_for_total_row");
    }

    private void prepareCellStype(){
        XSSFColor normalHeaderBackground = new XSSFColor(new byte[]{(byte) 255,(byte) 255,(byte) 255});
        XSSFColor totalRowColorBackground = new XSSFColor(new byte[]{(byte) 205,(byte) 205,(byte) 205});
        String numberFormat = "_(* #,##0_);_(* (#,##0);_(* \"-\"??_);_(@_)";

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);

        XSSFCellStyle cellStyle = createXSSFCellStyle(workbook);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(normalHeaderBackground);
        cellStyleMap.put("normal_cell_style",cellStyle);

        XSSFCellStyle defaultCellStyle = createXSSFCellStyle(workbook);
        defaultCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        defaultCellStyle.setFillForegroundColor(normalHeaderBackground);
        cellStyleMap.put("default_cell_style",defaultCellStyle);

        XSSFCellStyle defaultCellStyleForTotalRow = createXSSFCellStyle(workbook);
        defaultCellStyleForTotalRow.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        defaultCellStyleForTotalRow.setFillForegroundColor(totalRowColorBackground);
        defaultCellStyleForTotalRow.setFont(headerFont);
        cellStyleMap.put("default_cell_style_for_total_row",defaultCellStyleForTotalRow);

        DataFormat format = workbook.createDataFormat();
        String amountFormat = "#,##0.00";

        XSSFCellStyle  amountStyle = createXSSFCellStyle(workbook);
        amountStyle.setDataFormat(format.getFormat(amountFormat));
        amountStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        amountStyle.setFillForegroundColor(normalHeaderBackground);
        cellStyleMap.put("amount_cell_style", amountStyle);

        XSSFCellStyle  numberStyle = createXSSFCellStyle(workbook);
        numberStyle.setDataFormat(format.getFormat(amountFormat));
        numberStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        numberStyle.setFillForegroundColor(normalHeaderBackground);
        cellStyleMap.put("number_cell_style", numberStyle);

        XSSFCellStyle amountCellStyleForTotalRow = createXSSFCellStyle(workbook);
        amountCellStyleForTotalRow.setDataFormat(format.getFormat(amountFormat));
        amountCellStyleForTotalRow.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        amountCellStyleForTotalRow.setFillForegroundColor(totalRowColorBackground);
        amountCellStyleForTotalRow.setFont(headerFont);
        cellStyleMap.put("amount_cell_style_for_total_row",amountCellStyleForTotalRow);

        XSSFCellStyle numberCellStyleForTotalRow = createXSSFCellStyle(workbook);
        numberCellStyleForTotalRow.setDataFormat(format.getFormat(numberFormat));
        numberCellStyleForTotalRow.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        numberCellStyleForTotalRow.setFillForegroundColor(totalRowColorBackground);
        numberCellStyleForTotalRow.setFont(headerFont);
        cellStyleMap.put("number_cell_style_for_total_row",numberCellStyleForTotalRow);

        XSSFColor headerBackground = new XSSFColor(new  byte[]{125,(byte)175,(byte) 210});
        XSSFCellStyle headerStyle = createXSSFCellStyle(workbook);

        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(headerBackground);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyleMap.put("header_cell_style",headerStyle);

    }

    public XSSFCellStyle getXssfCellStyle(String cellStyleType){
        return  null != cellStyleMap.get(cellStyleType)? cellStyleMap.get(cellStyleType):cellStyleMap.get("default_cell_style");

    }


    public void mergeRegion(int firstRow ,int lastRow, int firstCoumn, int lastColumn){
        workbook.getSheetAt(0).addMergedRegion(new CellRangeAddress(firstRow,lastRow,firstCoumn,lastColumn));
        workbook.getSheetAt(0).getRow(firstRow).getCell(0).getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
        workbook.getSheetAt(0).getRow(firstRow).getCell(0).getCellStyle().setAlignment(HorizontalAlignment.LEFT);
    }

    public void addMergedRegions(List<CellRangeAddress> ranges){
        if(null != ranges){
            ranges.forEach(range -> sheet.addMergedRegion(range));
        }
    }

}


















