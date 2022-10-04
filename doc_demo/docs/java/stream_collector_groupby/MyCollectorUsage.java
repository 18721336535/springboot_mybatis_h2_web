package stream_collector_groupby;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MyCollectorUsage {
    public static void main(String[] args){
        //.generate product data
        MyCollectorUsage dto = new MyCollectorUsage();
        //.group by name + total row
        Collector<Product, ?, Integer> summingSalaries = Collectors.summingInt(Product::getPrice);
        Map<Object, List<Product>> salariesByDept = dto.generateData().stream().collect(Collectors.groupingBy(product -> product.getCategory()));
        Map<String, Integer> salariesByDept1 = dto.generateData().stream().collect(Collectors.groupingBy(Product::getCategory, summingSalaries));

    }
    private List<Product> generateData(){
        return  Arrays.asList(
                new Product("A","A_A1",1),
                new Product("A","A_A2",100),
                new Product("A","A_A2",30),
                new Product("B","B_B1",10),
                new Product("B","B_B2",5)
                );

    }

}
class Product implements Comparable<Product>{
    String category;
    String name;
    int price;

    public Product(String category, String name, int price) {
        this.category = category;
        this.name = name;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    static Map<String,Integer> categoryOrderMap;
    static Map<String,Integer> nameOrderMap;
    static {
        categoryOrderMap = new ConcurrentHashMap();
        categoryOrderMap.put("A",2);
        categoryOrderMap.put("B",1);

        nameOrderMap = new ConcurrentHashMap();
        nameOrderMap.put("A_A1",4);
        nameOrderMap.put("A_A2",3);
        nameOrderMap.put("B_B1",2);
        nameOrderMap.put("B_B2",1);

    }
    @Override
    public int compareTo(Product o) {
        return categoryOrderMap.get(this.getCategory()) - categoryOrderMap.get(o.getCategory());
    }

}
