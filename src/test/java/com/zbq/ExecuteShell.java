package com.zbq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ExecuteShell {

    public void execCommand(String cmd) {
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(cmd, null, null);
            InputStream stderr = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(stderr, "GBK");
            BufferedReader br = new BufferedReader(isr);
            String line = "";
            StringBuilder sbd =new StringBuilder();
            while ((line = br.readLine()) != null) {
                sbd.append(line);
                System.out.println(line);
            }
        } catch (Exception e) {

        }
    }

}