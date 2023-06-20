package java_http;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Date;
import java.util.List;

public class AppacheHttpClientsApply {

    private static HttpUtils httpUtils =  new HttpUtils();
    private static final ObjectMapper MAPPER =  new ObjectMapper();


    public static void main(String[] args) throws Exception {
        String url = "https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq" +
                "=%E6%89%8B%E6%9C%BA&cid2=653&cid3=655&s=113&click=0&page=";
        String html = httpUtils.doGetHtml(url);
        //解析页面，获取商品数据并存储
        parse(html);
    }

    public static void parse(String html) throws Exception {
        //解析html获取Document
        Document doc = Jsoup.parse(html);

        //获取spu信息
        Elements spuEles = doc.select("div#J_goodsList > ul > li");

        for (Element spuEle : spuEles) {
            //获取spu
            long spu = Long.parseLong(spuEle.attr("data-spu"));

            //获取sku信息
            Elements skuEles = spuEle.select("li.ps-item");

            for (Element skuEle : skuEles) {
                //获取sku
                long sku = Long.parseLong(skuEle.select("[data-sku]").attr("data-sku"));

                //根据sku查询商品数据
                Item item = new Item();
                item.setSku(sku);
//                List<Item> list = this.itemService.findAll(item);
//
//                if(list.size()>0) {
//                    //如果商品存在，就进行下一个循环，该商品不保存，因为已存在
//                    continue;
//                }

                //设置商品的spu
                item.setSpu(spu);

                //获取商品的详情的url
                String itemUrl = "https://item.jd.com/" + sku + ".html";
                item.setUrl(itemUrl);


                //获取商品的图片
                String picUrl ="https:"+ skuEle.select("img[data-sku]").first().attr("data-lazy-img");
                picUrl = picUrl.replace("/n9/","/n1/");
                String picName = httpUtils.doGetImage(picUrl);
                item.setPic(picName);

                //获取商品的价格
                String priceJson = httpUtils.doGetHtml("https://p.3.cn/prices/mgets?skuIds=J_" + sku);//6784500
                //[{"p":"-1.00","op":"5699.00","cbf":"0","id":"J_6784500","m":"9999.00"}]
                double price = MAPPER.readTree(priceJson).get(0).get("p").asDouble();
                item.setPrice(price);


                //获取商品的标题
                String itemInfo = httpUtils.doGetHtml(item.getUrl());
                String title = Jsoup.parse(itemInfo).select("div.sku-name").text();
                item.setTitle(title);


                item.setCreated(new Date());
                item.setUpdated(item.getCreated());

                //保存商品数据到数据库中
//                this.itemService.save(item);

            }
        }
    }

}
