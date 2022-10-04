package stream_collector_groupby;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MyCollectorUsage {
    public static void main(String[] args){
        //.generate product data
        MyCollectorUsage dto = new MyCollectorUsage();
        //.group by cat + total row

        List<Product> productList = dto.generateData();
        Map<String, List<Product>> salariesByCategory = productList.stream().collect(Collectors.groupingBy(product -> product.getCategory()));

        Collector<Product, ?, Integer> summingSalaries = Collectors.summingInt(Product::getOriginPrice);
        Map<String, Integer> totalOriginPriceByC = productList.stream().collect(Collectors.groupingBy(Product::getCategory, summingSalaries));
        Map<String, Integer> totalSalePriceByC = productList.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.summingInt(Product::getSalePrice)));

        int allTotalOriginPrice = productList.stream().collect(Collectors.summingInt(Product::getOriginPrice));
        int allTotalSalePrice = productList.stream().collect(Collectors.summingInt(Product::getSalePrice));

        for(Map.Entry entry:salariesByCategory.entrySet()){
            List<Product> list = (List<Product>) entry.getValue();
            list.stream().sorted();
        }

    }

    private void generateRow(){
        List<Product> productList  = generateData();
        Map<String,Product> totalProductMap = productList.stream().collect(Collectors.groupingBy(Product::getCategory, new Collector<Product, Product, Product>() {
            @Override
            public Supplier<Product> supplier() {
                return () -> new Product("","",0,0);
            }

            @Override
            public BiConsumer<Product, Product> accumulator() {
                return (p1,p2)->{
                    p1.setOriginPrice(p1.getOriginPrice()+p2.getOriginPrice());
                    p1.setSalePrice(p1.getSalePrice()+p2.getSalePrice());
                };
            }

            @Override
            public BinaryOperator<Product> combiner() {
                return (p1,p2)->{
                    p1.setOriginPrice(p1.getOriginPrice()+p2.getOriginPrice());
                    p1.setSalePrice(p1.getSalePrice()+p2.getSalePrice());
                    return p1;
                };
            }

            @Override
            public Function<Product, Product> finisher() {
                return null;
            }

            @Override
            public Set<Characteristics> characteristics() {
                return null;
            }
        }));
    }



    private List<Product> generateData(){
        return  Arrays.asList(
                new Product("A","A_A1",1,4),
                new Product("A","A_A2",100,400),
                new Product("A","A_A2",30,120),
                new Product("B","B_B1",10,40),
                new Product("B","B_B2",5,20)
                );

    }

}
class Product implements Comparable<Product>{
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
