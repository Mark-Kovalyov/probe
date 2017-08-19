package mayton.camel;

public class UpperCaseTransformer {

    public String transform(String body){
        String upperCaseContent = body.toUpperCase();
        return upperCaseContent;
    }

}
