package stream_collector_groupby;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class Product implements Comparable<Product> {
    String category;
    String name;
    int originPrice;
    int salePrice;

    public Product(String category, String name, int originPrice, int salePrice) {
        this.category = category;
        this.name = name;
        this.originPrice = originPrice;
        this.salePrice = salePrice;
    }

    public int getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(int originPrice) {
        this.originPrice = originPrice;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    static Map<String, Integer> categoryOrderMap;
    static Map<String, Integer> nameOrderMap;

    static {
        categoryOrderMap = new ConcurrentHashMap();
        categoryOrderMap.put("A", 2);
        categoryOrderMap.put("B", 1);

        nameOrderMap = new ConcurrentHashMap();
        nameOrderMap.put("A_A1", 4);
        nameOrderMap.put("A_A2", 3);
        nameOrderMap.put("B_B1", 2);
        nameOrderMap.put("B_B2", 1);

    }

    @Override
    public int compareTo(Product o) {
        return categoryOrderMap.get(this.getCategory()) - categoryOrderMap.get(o.getCategory());
    }

}
