package javax_mail_html;

import java.util.HashMap;
import java.util.Map;

public class HTMLColumn {
    private Map<String,String>  columnAttributes = new HashMap<>();
    Object data = "";
    boolean isHeader = false;
    public HTMLColumn(){
        initColumnAttributs();
    }
    public HTMLColumn(boolean isHeader){
        this.isHeader = isHeader;
        initColumnAttributs();

    }
    public void clearColumnAttributes(){
        columnAttributes.clear();
    }

    public void initColumnAttributs(){
        addClmAttr("align","left");
    }
    public void addClmAttr(String attrName, String attrValue){
        this.columnAttributes.put(attrName,attrValue);

    }
    public void addData(Object data){
        this.data = data;
    }

    private String getColumnAttributes(){
        StringBuilder attributes = new StringBuilder();
        for(Map.Entry<String,String> entry : columnAttributes.entrySet())
            attributes.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\" ");

        return attributes.toString();
    }

    public String getHTMLColumn(){
        StringBuilder htmlColumnBuilder;
        if(isHeader) htmlColumnBuilder = new StringBuilder("<th #ATTR#>#DATA#</th>");
        else htmlColumnBuilder = new StringBuilder("<td #ATTR#>#DATA#</td>");
        return htmlColumnBuilder.toString()
                .replace("#ATTR#",getColumnAttributes())
                .replace("#DATA#",String.valueOf(data));

    }
}
