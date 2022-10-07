package stream_collector_groupby;

import java.util.*;
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
            public Function<Product,Product> finisher() {
                return Function.identity();
//                return x->x;
            }

            @Override
            public Set<Characteristics> characteristics() {
                return EnumSet.of(Characteristics.CONCURRENT);
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
