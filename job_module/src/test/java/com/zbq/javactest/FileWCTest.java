package com.zbq.javactest;

import com.alibaba.fastjson.JSON;
import com.zbq.javactest.paramdto.ParamDto;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class FileWCTest {

    // String param1 = "D:\\workspace\\gitProjects\\job_module\\src\\test\\wc.txt";
    // String param2 = "D:\\workspace\\gitProjects\\job_module\\src\\test\\output.log";
    public static void main(String args[]) throws IOException {
        ParamDto param = new ParamDto();
        param.setFilePath1(args[0]);
        param.setFilePath2(args[1]);
        readFileByBuffer(param);
    }

    public static void readFileByBuffer(ParamDto param) throws IOException {
        int len;
        Map<String, AtomicLong> wcMap = new ConcurrentHashMap<>();
        try( FileChannel fileChannel = new FileInputStream(param.getFilePath1()).getChannel() ) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while ( (len = fileChannel.read(byteBuffer)) != -1 ) { lineHanle(len, wcMap, byteBuffer); }
            System.out.println(JSON.toJSONString(wcMap));
        }

        StringBuilder sbd = new StringBuilder("");
        wcMap.entrySet().stream().forEach(entry -> sbd.append(entry.getKey()+" : "+entry.getValue()+"\r\n"));

        try( BufferedOutputStream bops = new BufferedOutputStream(new FileOutputStream(param.getFilePath2())) ){
            bops.write(sbd.toString().getBytes(StandardCharsets.UTF_8));
        }

    }

    private static void lineHanle(int len, Map<String, AtomicLong> wcMap, ByteBuffer byteBuffer) throws UnsupportedEncodingException {
        byteBuffer.flip();

        String stringPatch = new String(byteBuffer.array(), 0, len, "utf-8");

        String[] wordStrs = stringPatch.replaceAll("\r\n","|").split("\\|");

        Arrays.stream(wordStrs).parallel().forEach( word -> {
            if ( wcMap.get(word) == null ) {wcMap.put(word, new AtomicLong(1));return;}
            wcMap.get(word).incrementAndGet();
        });
    }
}
