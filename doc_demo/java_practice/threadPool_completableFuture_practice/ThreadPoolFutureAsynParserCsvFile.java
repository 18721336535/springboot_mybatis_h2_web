package threadPool_completableFuture_practice;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ThreadPoolFutureAsynParserCsvFile {

    public void loadFileIntoDB() {

        List<File> files = getCSVFiles("./iem/temp/csvs");

        parallelParseFile(files);
    }

    /*get csv files*/
    protected List<File> getCSVFiles(String filesPath){
        File[] csvFiles = new File(filesPath).listFiles();
        return Arrays.asList(csvFiles);
    }

    /* group files and parallel parsing file by group */
    protected void parallelParseFile(List<File> files){

        ExecutorService executors = Executors.newFixedThreadPool(1);

        RunnableParamDto paramDto = new RunnableParamDto();

        List<File> marginGroup = files.parallelStream().filter(file -> file.getName().contains("margin")).collect(Collectors.toList());
        List<File> crfGroup = files.parallelStream().filter(file -> file.getName().contains("crfs")).collect(Collectors.toList());
        List<List<File>> groups = new ArrayList<>();
        groups.add(marginGroup);
        groups.add(crfGroup);
        paramDto.setFileGroups(groups);

        executors.submit(new ParseRunnable(paramDto));
    }
}

