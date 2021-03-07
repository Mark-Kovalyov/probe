package mayton;

import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;
import java.math.BigInteger;
import java.util.function.Consumer;

public abstract class DHThread extends Thread {

    protected LanternaParameters lanternaParameters;
    protected Consumer<LanternaParameters> lanternaParametersConsumer;

    public DHThread(LanternaParameters lanternaParameters, Consumer<LanternaParameters> lanternaParametersConsumer) {
        this.lanternaParameters = lanternaParameters;
        this.lanternaParametersConsumer = lanternaParametersConsumer;
    }

    public void printPlaceHolder() throws IOException {
        lanternaParameters.result = lanternaParameters.placeholder;
        lanternaParametersConsumer.accept(lanternaParameters);
        lanternaParameters.screen.refresh();
    }

    public void printResult() throws IOException {
        lanternaParametersConsumer.accept(lanternaParameters);
        lanternaParameters.screen.refresh();
    }


}
