import java.util.ArrayList;
import java.util.List;

public class DebugTest {
    public static void main(String args[]){
        String name = "";
        name = "test";
        List<String> lst = new ArrayList<>();
        lst.add("t1");
        lst.add("t2");
        boolean b = false;
        if(b){
            name = "hello";
            System.out.println(name);
            lst.add("n3");
        }
    }
}
