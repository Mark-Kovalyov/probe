package mayton.camel;

public class ReverseTransformer {

    public String reverse(String body){
        StringBuilder sb = new StringBuilder(body.length());
        for(char c : body.toCharArray()){
            sb.append(c);
        }
        return sb.toString();
    }

}



