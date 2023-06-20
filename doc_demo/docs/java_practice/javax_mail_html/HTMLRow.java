package javax_mail_html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HTMLRow {
    private Map<String,String> rowAttributes = new HashMap<>();
    boolean isTotalRow = false;
    private List<HTMLColumn> columnList = new ArrayList<>();
    public HTMLRow(){
        initRowAttributs();
    }
    public HTMLRow(boolean isTotalRow){
        this.isTotalRow = isTotalRow;
        initRowAttributs();

    }
    public void clearRowAttributes(){
        rowAttributes.clear();
    }

    public void initRowAttributs(){
        addRowAttr("width","100%");
        if(isTotalRow) addRowAttr("style","background-color:rgb(205,205,205);");
    }

    public void addRowAttr(String attrName, String attrValue){
        this.rowAttributes.put(attrName,attrValue);

    }
    public void addColumn(HTMLColumn column){
        this.columnList.add(column);
    }

    private String getRowAttributes(){
        StringBuilder attributes = new StringBuilder();
        for(Map.Entry<String,String> entry : rowAttributes.entrySet())
            attributes.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\" ");

        return attributes.toString();
    }

    public String getColumns(){
        StringBuilder htmlColumnBuilder = new StringBuilder();
        for(HTMLColumn htmlColumn: columnList)
            htmlColumnBuilder.append(htmlColumn.getHTMLColumn());
        return htmlColumnBuilder.toString();

    }

    public String getHTMLRow(){
        return "<tr #ATTR#>#DATA#</tr>"
                .replace("#ATTR#",getRowAttributes())
                .replace("#DATA#",getColumns());
    }
}
