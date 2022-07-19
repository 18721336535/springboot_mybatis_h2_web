package java_shell_process;

import java.lang.reflect.Method;

public class CreateShellProcess{
    public static int executeShellProcess() throws Exception {
        try{
            StringBuilder sbd = new StringBuilder();
            String cmd = sbd.append("/temp").append("send_batch_file.sh")
                    .append(" ").append("param1")
                    .append(" ").append("hostUid")//param2
                    .append(" ").append("hostName").toString();//param3
            Runtime runtime = Runtime.getRuntime();
            Method method = Runtime.class.getMethod("exec",String[].class);
            Process process = (Process)method.invoke(runtime,new Object[]{new String[]{"bash","-c",cmd}});
            return process.waitFor();
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            throw new Exception("occur InterruptedException",e);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private static int transFileToRemote() throws Exception {
        String cmd = "bash,-c,./transFile.sh param1 param2 ..,/temp/transFile.log 2>&1";
        try {
            String[] cmdArray = cmd.split(",");
            String[] cmdArrayProcessed = {cmdArray[0], cmdArray[1], cmdArray[2] + " >" + cmdArray[3]};
            return Runtime.getRuntime().exec(cmdArrayProcessed).waitFor();
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            throw new Exception("occur InterruptedException",e);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}