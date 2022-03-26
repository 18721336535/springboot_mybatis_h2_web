package com.zbq.utils.completableFuture;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ASYNCSVParser {
    public void dbRelease() {
        /*get csv files*/
        List<File> files = getCSVFiles("/iem/temp/csvs");
        /*asyn parse*/
        asynParse(files);
    }

    protected List<File> getCSVFiles(String filesPath){
        File[] csvFiles = new File(filesPath).listFiles();
        return Arrays.asList(csvFiles);
    }

    protected String asynParse(List<File> files){
        //asyn db operation/release
        ExecutorService executors = null;
        try {
            executors = Executors.newFixedThreadPool(1);
            RunnableParamDto paramDto = new RunnableParamDto();
            List<File> marginGroup = files.parallelStream()
                    .filter(file -> file.getName().contains("margin")).collect(Collectors.toList());
            List<File> crfGroup = files.parallelStream()
                    .filter(file -> file.getName().contains("crfs")).collect(Collectors.toList());
            List<List<File>> groups = new ArrayList<>();
            groups.add(marginGroup);
            groups.add(crfGroup);
            paramDto.setFileGroups(groups);
            executors.submit(new ParseRunnable(paramDto));
       }finally {
            executors.shutdown();
       }
        return "csv parse is processing in backend";
    }
}
