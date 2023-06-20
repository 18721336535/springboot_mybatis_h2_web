package generateExcel;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenerateExcel {

    private void generateExcel() throws IOException {
        List<OTCProduct> products = new ArrayList<>();
        products.add(new OTCProduct("c1","ca1",100,1000));
        products.add(new OTCProduct("c1","ca2",200,2000));
        products.add(new OTCProduct("c2","cb1",300,3000));
        products.add(new OTCProduct("c2","cb2",400,4000));

        WorkBookWriter workBookWriter = new WorkBookWriter("products");
        workBookWriter.createRow();

        workBookWriter.writeHeaders(Arrays.asList("category1","category2","originPrice","salePrice"),
                Arrays.asList(7500,7500,7500,7500));
        products.forEach(rowdata ->{
            workBookWriter.createRow();

            boolean isTotalRow = rowdata.isTotalRow();
            if(isTotalRow){
                workBookWriter.writeDataToCell(rowdata.getCategory1(),workBookWriter.getDefaultCellStyleForTotalRow());
                workBookWriter.writeDataToCell(rowdata.getCategory2(),workBookWriter.getDefaultCellStyleForTotalRow());
                workBookWriter.writeDataToCell(rowdata.getOriginPrice(),workBookWriter.getAmountCellStyleForTotalRow());
                workBookWriter.writeDataToCell(rowdata.getSalePrice(), workBookWriter.getNumberCellStyleForTotalRow());
            }
            else{
                workBookWriter.writeDataToCell(rowdata.getCategory1());
                workBookWriter.writeDataToCell(rowdata.getCategory2());
                workBookWriter.writeDataToCell(rowdata.getOriginPrice(),workBookWriter.getAmountCellStyle());
                workBookWriter.writeDataToCell(rowdata.getSalePrice(), workBookWriter.getNumberCellStyle());
            }
        });
        workBookWriter.mergeRegion(2,3,1,2);
        FileOutputStream out = new FileOutputStream("filepath");
        workBookWriter.getWorkbook().write(out);
        out.close();

//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        workBookWriter.getWorkbook().write(bos);
//        byte[] bts = bos.toByteArray();

    }


}
