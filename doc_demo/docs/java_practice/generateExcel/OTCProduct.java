package generateExcel;

public  class OTCProduct{
    String category1;
    String category2;
    double originPrice;
    double  salePrice ;
    boolean isTotalRow;
    boolean  isHeader;
    boolean  isHighLightRow;

    public boolean isHighLightRow() {
        return isHighLightRow;
    }

    public void setHighLightRow(boolean highLightRow) {
        isHighLightRow = highLightRow;
    }

    public OTCProduct(String category1, String category2, double originPrice, double salePrice) {
        this.category1 = category1;
        this.category2 = category2;
        this.originPrice = originPrice;
        this.salePrice = salePrice;
    }

    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public double getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(double originPrice) {
        this.originPrice = originPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public boolean isTotalRow() {
        return isTotalRow;
    }

    public void setTotalRow(boolean totalRow) {
        isTotalRow = totalRow;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }
}
