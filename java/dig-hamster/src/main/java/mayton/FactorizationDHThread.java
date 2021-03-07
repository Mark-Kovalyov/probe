package mayton;
import java.io.IOException;
import java.util.function.Consumer;

public class FactorizationDHThread extends DHThread {

    public FactorizationDHThread(LanternaParameters lanternaParameters, Consumer<LanternaParameters> lanternaParametersConsumer) {
        super(lanternaParameters, lanternaParametersConsumer);
    }

    @Override
    public void run() {
        try {
            printPlaceHolder();

            Thread.sleep(3000);
            lanternaParameters.result = "13^2 * 27^3";

            printResult();
        } catch (IOException | InterruptedException ex) {

        }
    }
}
