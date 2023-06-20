package com.zbq.fileIO;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class FileNIOTest {

    public static final String STATIC_LOGIN_HTML = "D:\\workspace\\gitProjects\\job_module\\src\\main\\resources\\static\\login.html";

    @Test
    public void nioReadTest() throws IOException, InterruptedException {
        FileChannel fileChannel = new FileInputStream(STATIC_LOGIN_HTML).getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int len;
        while ( (len = fileChannel.read(byteBuffer)) != -1 ){
            byteBuffer.flip();
            Thread.sleep(1000000);
            System.out.println(new String(byteBuffer.array(),0,len,"utf-8"));
        }
    }
    @Test
    public void nioWriteTest() throws IOException {
        String name = "D:\\workspace\\gitProjects\\job_module\\src\\main\\resources\\static\\nio.txt";
        FileChannel fileChannel =new FileOutputStream(name).getChannel();
        ByteBuffer byteBuffer = Charset.forName("utf8").encode("data source");
        // 字节缓冲的容量和limit会随着数据长度变化，不是固定不变的
        System.out.println("初始化容量和limit：" + byteBuffer.capacity() + "," + byteBuffer.limit());
        int length = 0;
        while ((length = fileChannel.write(byteBuffer)) != 0) {
            /*
             * 注意，这里不需要clear，将缓冲中的数据写入到通道中后 第二次接着上一次的顺序往下读
             */
            System.out.println("写入长度:" + length);
        }
    }
}
