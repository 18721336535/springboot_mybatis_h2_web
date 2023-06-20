package javax_mail_html;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class HTMLMailContentWriter {
    private List<HTMLWriter> writers = new ArrayList<>();
    private HTMLTable htmlTable = new HTMLTable();
    private String cob;
    public HTMLMailContentWriter(String cob){
        this.cob = cob;
        htmlTable.clearTableAttributes();
        htmlTable.addTableAttr("cellspacing","0");
        htmlTable.addTableAttr("cellpadding","0");
        htmlTable.addTableAttr("width","75%");
        htmlTable.addTableAttr("border","0");
        htmlTable.addTableAttr("style","font:normal 15px Verdana, Arial, sans-serif;");

    }

    public void addGeneratedReportAsHTML(HTMLWriter htmlWriter){
        writers.add(htmlWriter);
    }

    private void addEmptyRow(){
        HTMLColumn htmlColumn = new HTMLColumn();
        htmlColumn.addData("&nbsp;");
        HTMLRow htmlRow = new HTMLRow();
        htmlRow.addColumn(htmlColumn);
        htmlTable.addRow(htmlRow);
    }

    private void addHeader(){
        HTMLColumn htmlColumn = new HTMLColumn();
        htmlColumn.addData(buildContent());
        HTMLRow htmlRow = new HTMLRow();
        htmlRow.addColumn(htmlColumn);
        htmlTable.addRow(htmlRow);
        addEmptyRow();
    }

    private String buildContent(){
        StringBuilder sd = new StringBuilder("Hi,");
        return sd.toString();
    }

    private String getHostName() throws UnknownHostException {
        String hostName = InetAddress.getLocalHost().getHostName();
        return hostName;
    }

    private void writeReportName(String name){
        HTMLColumn htmlColumn = new HTMLColumn();
        htmlColumn.addData(name);
        HTMLRow htmlRow = new HTMLRow();
        htmlRow.addColumn(htmlColumn);
        htmlTable.addRow(htmlRow);
    }
    private void writeReport(String report){
        HTMLColumn htmlColumn = new HTMLColumn();
        htmlColumn.addData(report);
        HTMLRow htmlRow = new HTMLRow();
        htmlRow.addColumn(htmlColumn);
        htmlTable.addRow(htmlRow);
    }
    public String generateMailContent(){
        String emailTemplate = "<!DOCTYPE HTML><body>#MAIL_CONTENT#</body></html>";
        addHeader();
        for (HTMLWriter htmlWriter:writers){
            writeReportName("Product Name");
            addEmptyRow();
            writeReport(htmlWriter.getHTMLContent());
            addEmptyRow();
        }
        return emailTemplate.replace("#MAIL_CONTENT#",htmlTable.getHTMLTable());
    }
}
