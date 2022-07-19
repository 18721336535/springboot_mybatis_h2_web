package java_http;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpsURLConnectionApply {
//    HttpClient client = HttpClient.newBuilder()
//            .version(Version.HTTP_1_1)
//            .followRedirects(Redirect.NORMAL)
//            .connectTimeout(Duration.ofSeconds(20))
//            .proxy(ProxySelector.of(new InetSocketAddress("proxy.example.com", 80)))
//            .authenticator(Authenticator.getDefault())
//            .build();
//    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
//   System.out.println(response.statusCode());
//   System.out.println(response.body());

    public static String sendRequest(String urlParam,String method) {

        HttpsURLConnection con = null;

        BufferedReader buffer = null;
        StringBuffer resultBuffer = null;

        try {
            URL url = new URL(urlParam);
            //得到连接对象
            con = (HttpsURLConnection) url.openConnection();
            //设置请求类型
            con.setRequestMethod(method);
            //设置请求需要返回的数据类型和字符集类型
            con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            //允许写出
            con.setDoOutput(true);
            //允许读入
            con.setDoInput(true);
            //不使用缓存
            con.setUseCaches(false);
            con.setRequestProperty("lt","2");
            con.setRequestProperty("rt","");
            con.setRequestProperty("page","1");
            con.setRequestProperty("locid","");
            //得到响应码
            int responseCode = con.getResponseCode();
            Map mp = con.getHeaderFields();
            if(responseCode == HttpURLConnection.HTTP_OK){
                //得到响应流
                InputStream inputStream = con.getInputStream();
                //将响应流转换成字符串
                resultBuffer = new StringBuffer();
                String line;
                buffer = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                while ((line = buffer.readLine()) != null) {
                    resultBuffer.append(line);
                }
                return resultBuffer.toString();
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public static void main(String[] args) {

        String url ="https://xinfengxian.tuliu.com/";
//        System.out.println(sendRequest(url,"GET"));
        url = "https://xinfengxian.tuliu.com/landext/markets";
        System.out.println(sendRequest(url,"POST"));
    }
}
