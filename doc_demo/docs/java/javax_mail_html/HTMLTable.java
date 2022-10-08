package javax_mail_html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HTMLTable {
    private Map<String,String> tableAttributes = new HashMap();
    private List<HTMLRow> rowList = new ArrayList<>();

    public HTMLTable(){
        intitTableAttributes();
    }

    public void clearTableAttributes(){
        tableAttributes.clear();
    }
    private void intitTableAttributes(){
        addTableAttr("width","100%");
        addTableAttr("cellspacing","0");
        addTableAttr("cellpadding","0");
        addTableAttr("border","1");

    }


    public void addTableAttr(String attrName, String attrValue){
        this.tableAttributes.put(attrName,attrValue);

    }
    public void addRow(HTMLRow htmlRow){
        this.rowList.add(htmlRow);
    }

    private String getTableAttributes(){
        StringBuilder attributes = new StringBuilder();
        for(Map.Entry<String,String> entry : tableAttributes.entrySet())
            attributes.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\" ");

        return attributes.toString();
    }

    public String getRows(){
        StringBuilder rowBuilder = new StringBuilder();
        for(HTMLRow htmlRow: rowList)
            rowBuilder.append(htmlRow.getHTMLRow());
        return rowBuilder.toString();

    }

    public String getHTMLTable(){
        return "<table #ATTR#>#DATA#</table>"
                .replace("#ATTR#",getTableAttributes())
                .replace("#DATA#",getRows());
    }
}

