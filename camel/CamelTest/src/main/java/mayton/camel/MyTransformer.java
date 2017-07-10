package mayton.camel;

public class MyTransformer {

    public String transform(String body){
        String upperCaseContent = body.toUpperCase();
        return upperCaseContent;
    }

}
