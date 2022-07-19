package java_file_handle;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CountWordFrequencyFromBigFile {
    public  static void readFileFromLineNumber() throws IOException {
        String filePath = "";
        long lineNumStart=0;
        long count = Files.lines(Paths.get(filePath)).count();
        LineNumberReader lineNumRd = new LineNumberReader(new FileReader(filePath));
        StringBuilder sbd = new StringBuilder();
        String line = lineNumRd.readLine();
        lineNumRd.setLineNumber(100);
        while (line != null){
            long curLineNum = lineNumRd.getLineNumber();
            if( curLineNum >= lineNumStart && curLineNum < count-100){
                sbd.append("->"+line);
                sbd.append("\r\n");
            }
            line = lineNumRd.readLine();
        }
        try(FileWriter fileWriter = new FileWriter(filePath,false)){
            fileWriter.write(sbd.toString());
        }
    }

    public  static void countWordFrequencyByLine() throws IOException {
        String filePath = "";
        LineNumberReader lineNumRd = new LineNumberReader(new FileReader(filePath));
        StringBuilder sbd = new StringBuilder();
        String line = lineNumRd.readLine();
        lineNumRd.setLineNumber(100);
        ConcurrentMap<String,Long> cmap = new ConcurrentHashMap<>();
        while (line != null){
            String[] words = line.replaceAll("  +","").split(" +");
            Arrays.asList(words).parallelStream().forEach(word ->{
                cmap.put(word,cmap.get(word)+1);
            });
        }
    }

    public  static void countWordFrequencyByThreadPool() throws IOException {
        String filePath = "";
        LineNumberReader lineNumRd = new LineNumberReader(new FileReader(filePath));
        StringBuilder sbd = new StringBuilder();
        String line = lineNumRd.readLine();
        lineNumRd.setLineNumber(100);
        ConcurrentMap<String,Long> cmap = new ConcurrentHashMap<>();
        while (line != null){
            String[] words = line.replaceAll("  +","").split(" +");
            Arrays.asList(words).stream().forEach(word ->{
                cmap.put(word,cmap.get(word)+1);
            });
        }
    }



    public  static void readFileFromLineNumberMethod2() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("path")));
        char[] chars = new char[100];
        bufferedReader.read(chars,100, 1000);
        System.out.println(chars.length);

    }
    public void readFileFromLineNumberMethod3() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(new File(""),"r");
        long i= randomAccessFile.getFilePointer();//取得偏移量
        randomAccessFile.seek(100);//设置偏移量
        byte[] bytes = new byte[100];
        randomAccessFile.readFully(bytes,10,100);
        System.out.println(bytes.length);


    }


    }
