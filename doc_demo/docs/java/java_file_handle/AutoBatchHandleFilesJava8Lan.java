package java_file_handle;

import java.io.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoBatchHandleFilesJava8Lan {
    static int index=0;
    public void handleFiles() {
        File dir = new File("filesDir");
        handle(dir);
    }

    public static void handle(File dir){
        File[] files = dir.listFiles();
        Arrays.asList(files).stream().forEach(file ->{
            if (file.isDirectory()){ handle(file);}
            modifyFile(file);
        });
    }

    public static void modifyFile(File file){
        File outPath = new File(file.getParent().replace("nameN", "nameO"));
        String fileName = file.getName();
        String absolutePath = file.getAbsolutePath();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"))){
            String temp;
            StringBuilder appendBld = new StringBuilder();
            while(( temp = bufferedReader.readLine() ) != null){
                // appendBld.append(System.getProperty("line.separator"));//换行符
                if (temp.contains("Assert.assert")) {
                    //enrich handle logic
                    temp = temp.replace("Assert.assert","assert");
                    appendBld.append(temp);
                    continue;
                }
                appendBld.append(temp);
            }

            if (outPath.exists() == false) {
                outPath.mkdirs();
                System.out.println("output path:" + outPath);
            }

            try(PrintWriter printWriter = new PrintWriter(outPath + "\\" + fileName, "utf-8")) {
                printWriter.write(appendBld.toString());
                printWriter.flush();
                System.out.println("第 " + (index++) + "个file " + absolutePath + " 已经成功输出到 " + outPath + "\\" + fileName);
            }
        } catch(Exception e){
            Logger.getAnonymousLogger().log(Level.SEVERE,e.getMessage());
        }
    }
}
