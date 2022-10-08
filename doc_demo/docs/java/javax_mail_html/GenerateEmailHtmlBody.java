package javax_mail_html;

import generateExcel.OTCProduct;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;

public class GenerateEmailHtmlBody {
    public HTMLWriter  generateHtmlBody(){

        List<OTCProduct> products = new ArrayList<>();
        products.add(new OTCProduct("c1","ca1",100,1000));
        products.add(new OTCProduct("c1","ca2",200,2000));
        products.add(new OTCProduct("c2","cb1",300,3000));
        products.add(new OTCProduct("c2","cb2",400,4000));

        HTMLWriter  htmlWriter = new HTMLWriter("products");
        htmlWriter.createRow();

        htmlWriter.writeHeader(Arrays.asList("category1","category2","originPrice","salePrice"));

        Map<String,ExcelIndexVO> indexVOMap = new LinkedHashMap<>();
        int firstRow=0;
        int lastRow=0;
        for(OTCProduct product :products){
            if(indexVOMap.containsKey(product.getCategory1()))
                indexVOMap.get(product.getCategory1());
            else {
                firstRow = ++lastRow;
                ExcelIndexVO excelIndexVO = new ExcelIndexVO();
                excelIndexVO.setFirstRow(firstRow);
                excelIndexVO.setLastRow(lastRow);
                indexVOMap.put(product.getCategory1(),excelIndexVO);
            }
        }
        int rowSpan = 0;
        for (OTCProduct product: products){
            Map<String, String> columnAttrs = new HashMap<>();

            if(product.isTotalRow()) htmlWriter.createRow();
            if(product.isHighLightRow()) columnAttrs.put("style","background-color:rgb(250,0,0);");
            htmlWriter.createRow();
             if(indexVOMap.containsKey(product.getCategory1()))
             {
                 ExcelIndexVO excelIndexVO = indexVOMap.get(product.getCategory1());
                 rowSpan = 1+ excelIndexVO.getLastRow() - excelIndexVO.getFirstRow();
             }
             if(indexVOMap.containsKey(product.getCategory1()))
                 htmlWriter.writeDataWithRowSpan(product.getCategory1(),rowSpan);

             htmlWriter.writeData(product.getCategory2(),columnAttrs);
             columnAttrs.put("align","right");
             htmlWriter.writeData(NumberFormat.getInstance(Locale.US).format(product.getOriginPrice()),columnAttrs);
             htmlWriter.writeData(BigDecimal.valueOf(product.getSalePrice()).setScale(2, RoundingMode.HALF_UP)+"%",columnAttrs);
             indexVOMap.remove(product.getCategory1());
        }
        return  htmlWriter;
    }
}
