package java_file_handle;

import java.io.*;

public class AutoBatchHandleFiles {
    public void handleFiles() {
        File dir = new File("filesDir");
        handle(dir,0);
    }

    public static void handle(File dir,int len){
        File[] files = dir.listFiles();
        for (int i = 0; i < dir.length(); i++) {
            if (files[i].isDirectory()){
                handle(files[i],len+1);
            }
            File outPath = new File(files[i].getParent().replace("nameN","nameO"));
            File readFile = new File(files[i].getAbsolutePath());
            if(!readFile.isDirectory()){
                String fileName = readFile.getName();
                String absolutePath = readFile.getAbsolutePath();
                modifyFile(absolutePath,fileName,i,outPath);
            }
        }
    }

    public static void modifyFile(String absolutePath,String fileName,int index, File outPath){
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName)),"utf-8"))){
            StringBuffer stringBuffer = new StringBuffer();
            String temp;
            StringBuilder appendBld = new StringBuilder();
            for(;(temp = bufferedReader.readLine()) != null; temp = null) {
                appendBld.append(System.getProperty("line.separator"));//换行符
                if (temp.contains("Assert.assert")) {
                    //todo handle
                    appendBld.append(temp);
                }
                appendBld.append(temp);
                stringBuffer.append(appendBld);
            }
            temp = appendBld.toString();
            temp = temp.replaceFirst(System.getProperty("line.separator"),"");
            if (outPath.exists() == false) {
                outPath.mkdirs();
                System.out.println("output path:" + outPath);
            }
            try(PrintWriter printWriter = new PrintWriter(outPath + "\\" + fileName, "utf-8")) {
                printWriter.write(temp);
                printWriter.flush();
            }
            System.out.println("第 " + (index + 1) + "个file " + absolutePath + " 已经成功输出到 " + outPath + "\\" + fileName);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
