package mayton;

import java.io.PrintStream;

public class Console {

    private Console(){}
    
    public static PrintStream printf(String format, Object ...args){
        return System.out.printf(format,args);
    }
    
    public static void println(){
        System.out.println();
    }
    
}
