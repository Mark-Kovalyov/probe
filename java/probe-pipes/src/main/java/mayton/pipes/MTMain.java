package mayton.pipes;

import org.apache.commons.io.input.NullInputStream;

public class MTMain {

    public static void main(String[] args) {
        NullInputStream nullInputStream = new NullInputStream();

        MTChain mtChain = new MTChain(nullInputStream);

        mtChain.addNode(new MTNode(() -> {
            // pipedInputStream
        }));

        mtChain.addNode(new MTNode(() -> {}));

        mtChain.addNode(new MTNode(() -> {}));

        mtChain.startNodes();

        mtChain.joinNodes();

    }

}
