package mayton.probe;

import quickfix.Message;
import quickfix.field.MsgType;
import quickfix.field.Text;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class App {



    public static void main(String[] args) throws Exception{

        Message message = new Message();

        message.setString(MsgType.FIELD, "8");
        message.setString(Text.FIELD, "Text");


        System.out.println(message.toString());

        Message message1 = new Message("");

    }

}
