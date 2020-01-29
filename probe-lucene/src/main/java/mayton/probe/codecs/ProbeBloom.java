package mayton.probe.codecs;

import mayton.probe.FixIndexer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.codecs.bloom.FuzzySet;
import org.apache.lucene.util.BytesRef;

import java.io.IOException;

public class ProbeBloom {

    static Logger logger = LogManager.getLogger(ProbeBloom.class);

    public static void main(String[] args) throws IOException {

        int threeDivisors = 0;
        int allSelection = 3_000_000;

        //FuzzySet fuzzySet = FuzzySet.createSetBasedOnQuality(allSelection / 3, 80.0f);
        FuzzySet fuzzySet = FuzzySet.createSetBasedOnMaxMemory(100 * 1024 * 1024);

        for(int i = 0 ; i < allSelection ; i++) {
            if (i%3 == 0) {
                BytesRef bytesRef = new BytesRef(String.valueOf(i));
                fuzzySet.addValue(bytesRef);
                threeDivisors++;
            }
        }
        logger.info("threeDivisors = {}", threeDivisors);
        int threeDivisorsMaybe = 0;
        for (int i = 0; i < allSelection; i++) {
            BytesRef bytesRef = new BytesRef(String.valueOf(i));
            if (fuzzySet.contains(bytesRef) == FuzzySet.ContainsResult.MAYBE) {
                threeDivisorsMaybe++;
            } else {
                // nothing to do
            }
        }

        logger.info("threeDivisors(maybe) = {}", threeDivisorsMaybe);
        logger.info("fuzzySet.ramBytesUsed          = {} bytes", fuzzySet.ramBytesUsed());
        logger.info("fuzzySet.saturation            = {}", fuzzySet.getSaturation());
        logger.info("fuzzySet.EstimatedUniqueValues = {}", fuzzySet.getEstimatedUniqueValues());

    }

}
