package com.zbq;

import javax.script.*;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ScriptEngineTest {
    public static void main(String args[]) throws FileNotFoundException, ScriptException, NoSuchMethodException, InterruptedException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
        Bindings binds = engine.createBindings();
        binds.put("factor",2);
        engine.setBindings(binds, ScriptContext.ENGINE_SCOPE);
        while(true) {
            try {
                engine.eval(new FileReader("C:\\Users\\zbq\\Desktop\\gitTest\\Springboot_H2_web_repository_1\\springboot_mybatis_h2_web\\src\\main\\resources\\mode.js"));
                Invocable iv = (Invocable) engine;
//            Thread.sleep(1000);
                Double rs = (double) iv.invokeFunction("formula", 1, 2);
                System.out.println(rs);
            }catch (Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.out.println(e.getMessage()+"--");
            }
        }
    }
}
