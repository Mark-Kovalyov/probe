package mayton;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.Random;
import java.util.stream.IntStream;

import static java.math.BigInteger.ZERO;

public class DigHamster {

    static Logger logger = LoggerFactory.getLogger(DigHamster.class);

    public static String randomStr(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        String dec = "0123456789";
        for(int i=0;i<length;i++) {
            sb.append(dec.charAt(random.nextInt(10)));
        }
        logger.trace("Generated : {}", sb.toString());
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {

        logger.info("BEGIN!");

        BigInteger n = new BigInteger(randomStr(20));

        Terminal terminal = new DefaultTerminalFactory().createTerminal();

        Screen screen = new TerminalScreen(terminal);

        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.ANSI.BLUE);
        textGraphics.setForegroundColor(TextColor.ANSI.YELLOW);

        screen.startScreen();

        textGraphics.putString(0, 0, "Dig Hamster 1.0");
        textGraphics.drawLine(0,1, 80, 1, '=');
        textGraphics.putString(0, 2, "Input : " + n.toString());
        textGraphics.putString(0, 3, "Analyze ");
        textGraphics.putString(0, 4, "----------------------------------------");

        new FactorizationDHThread(
                new LanternaParameters(0, 6, "Factorization : ", screen, textGraphics, " "),
                (LanternaParameters par) -> par.textGraphics.putString(par.row, par.column, par.formatExpr + par.result)
        ).start();

        //textGraphics.putString(0, 7, "Is odd   : [" + (n.mod(BigInteger.valueOf(2)).equals(ZERO)) + "]");
        //textGraphics.putString(0, 8, "MOD(3)   : [" + (n.mod(BigInteger.valueOf(3)).equals(ZERO)) + "]");
        //textGraphics.putString(0, 9, "MOD(5)   : [" + (n.mod(BigInteger.valueOf(5)).equals(ZERO)) + "]");
        //textGraphics.putString(0, 10, "MOD(7)   : [" + (n.mod(BigInteger.valueOf(5)).equals(ZERO)) + "]");

        screen.refresh();



        /*IntStream.range(0, 10).forEach(item -> {
            textGraphics.putString(0, item, "[ ] Item-" + item);
        });
        screen.refresh();

        Thread.sleep(3000);

        IntStream.range(0, 10).forEach(item -> {
            textGraphics.putString(0, item, "[x] Item-" + item + "-2");
        });*/



        screen.readInput();
        screen.stopScreen();

        logger.info("END!");
    }

}
