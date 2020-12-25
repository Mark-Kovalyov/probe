package mayton.html;

public class HtmlParserException extends RuntimeException {

    public HtmlParserException(String comment) {
        super(comment);
    }

    public HtmlParserException(Throwable throwable) {
        super(throwable);
    }

}
