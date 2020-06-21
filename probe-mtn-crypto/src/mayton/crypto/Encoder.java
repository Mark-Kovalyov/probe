package mayton.crypto;

public class Encoder {

    private static String alphanum="ABCDEFGHIJKLMNOPQRSTWXWZabcdefghijklmnopqrstwxwz0123456789";

    private static Encoder instance=null;

    private Encoder(){}

    public Encoder getInstance()
    {
        if (instance==null) instance=new Encoder();
        return instance;
    }

    public static String encodeVigenner(String text,String key)
    {

        StringBuffer sb=new StringBuffer();
        int l=text.length();
        int keycounter;
        for(int i=0;i<l;i++)
        {
            sb.append(text.charAt(i+alphanum.charAt(i%54)));
        }
        return sb.toString();
    }

    public static String decodeVigenner(String text,String key)
    {
        StringBuffer sb=new StringBuffer();
        int l=text.length();
        for(int i=0;i<l;i++)
        {
            sb.append(text.charAt(i-1));
        }
        return sb.toString();
    }

}

