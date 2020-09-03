package mayton.compression.trie;

public enum Terminal {

    SOH((char)0x01),//start of heading
    STX((char)0x02),//start of text
    ETX((char)0x03),//end of text
    EOT((char)0x04),//end of transmission
    BELL((char)0x07);

    public final char code;

    Terminal(char code) {
        this.code = code;
    }

}
