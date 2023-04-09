package com.zbq.demo.io.fileIO;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileNIOTest {

    public static void main() throws IOException {
        FileChannel fileChannel =new FileInputStream("D:\\workspace\\gitProjects\\job_module\\src\\main\\resources\\static\\login.html").getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int len;
        while ( (len = fileChannel.read(byteBuffer)) != -1 ){
            byteBuffer.flip();
            System.out.println(new String(byteBuffer.array(),0,len,"utf-8"));
        }
    }
}
