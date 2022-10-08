package javax_mail_html;

import java.util.List;
import java.util.Map;

public class HTMLWriter {
    private HTMLTable htmlTable;
    private HTMLRow htmlRow;
    private HTMLColumn htmlColumn;
    private String tableName;
    private String style = "style";

    public String getTableName(){
        return this.tableName;
    }
    public HTMLWriter(String name){
        htmlTable = new HTMLTable();
        this.tableName = name;
    }

    public void addTableAttribute(String name,String value){
        this.htmlTable.addTableAttr(name,value);
    }
    public void createRow(){
        htmlRow = new HTMLRow();
        htmlTable.addRow(htmlRow);
    }
    public void createTotalRow(){
        htmlRow = new HTMLRow(true);
        htmlTable.addRow(htmlRow);
    }

    public void writeDataNotAvailableInfo(){
        htmlTable =  new HTMLTable();
        htmlRow = new HTMLRow();
        htmlColumn = new HTMLColumn();
        htmlColumn.addClmAttr("colspan","6");
        htmlColumn.addData("no data");
        htmlRow.addColumn(htmlColumn);
        htmlTable.addRow(htmlRow);
    }
    public void writeData(Object data){

        htmlColumn = new HTMLColumn();
        htmlColumn.addData(data);
        htmlRow.addColumn(htmlColumn);
    }
    public void writeData(Object data,Map<String,String> attributes){

        htmlColumn = new HTMLColumn();
        htmlColumn.addData(data);
        for (Map.Entry<String,String> entry : attributes.entrySet())
        {
            htmlColumn.addClmAttr(entry.getKey(),entry.getValue());
        }
        htmlRow.addColumn(htmlColumn);
    }
    public void writeDataWithRowSpan(Object data,int rowspan){
        htmlColumn = new HTMLColumn();
        htmlColumn.addClmAttr("rowspan",String.valueOf(rowspan));
        htmlColumn.addData(data);
        htmlRow.addColumn(htmlColumn);
    }

    public void writeDataWith(Object data,String  align){
        htmlColumn = new HTMLColumn();
        htmlColumn.addClmAttr("align",align);
        htmlColumn.addData(data);
        htmlRow.addColumn(htmlColumn);
    }

    public void writeDataWith(Object data, Map<String,String> attributes){
        htmlColumn = new HTMLColumn();
        htmlColumn.addData(data);
        for (Map.Entry<String,String> entry: attributes.entrySet())
            htmlColumn.addClmAttr(entry.getKey(),entry.getValue());

        htmlRow.addColumn(htmlColumn);
    }

    public void writeHeader(Object data){
        htmlColumn = new HTMLColumn(true);
        htmlRow.addRowAttr(style,"background-color:rgb(125,172,210);");
        htmlColumn.addData(data);
        htmlRow.addColumn(htmlColumn);
    }


    public void writeHeader(List<String> headerNames){
        for(String header: headerNames) {
            htmlColumn = new HTMLColumn(true);
            htmlRow.addRowAttr(style, "background-color:rgb(125,172,210);");
            htmlColumn.addData(header);
            htmlRow.addColumn(htmlColumn);
        }
    }
    public String getHTMLContent(){
        return htmlTable.getHTMLTable();
    }
}

