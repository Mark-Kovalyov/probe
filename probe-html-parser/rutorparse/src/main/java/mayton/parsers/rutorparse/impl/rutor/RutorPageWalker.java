package mayton.parsers.rutorparse.impl.rutor;

import mayton.parsers.rutorparse.interfaces.rutor.IPageWalker;
import mayton.parsers.rutorparse.Utils;
import mayton.parsers.rutorparse.entities.RutorRawEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;

import static java.lang.String.format;

@Component
public class RutorPageWalker implements IPageWalker {

    static Logger logger = LogManager.getLogger(RutorPageWalker.class);

    @Override
    public Iterable<RutorRawEntity> walk(int page) {
        logger.info(":: [1] start walking page {}", page);

        Connection connection = Jsoup.connect(
                format("", page)
        );

        String selectorDate  = "tr.gai:nth-child(1) > td:nth-child(1)"; // /html/body/div/div[5]/div[1]/div[2]/table/tbody/tr[1]/td[1]
        String selectorDate2 = "tr.tum:nth-child(2) > td:nth-child(1)"; // /html/body/div/div[5]/div[1]/div[2]/table/tbody/tr[2]/td[1]
        String selectorDate3 = "tr.gai:nth-child(3) > td:nth-child(1)"; // /html/body/div/div[5]/div[1]/div[2]/table/tbody/tr[3]/td[1]

        String selectorName = "tr.gai:nth-child(1) > td:nth-child(2) > a:nth-child(3)";

        try {
            Document document = connection.get();

            Element rootDiv = document.selectFirst("html")
                                .selectFirst("body")
                                    .selectFirst("div");


            Element tableBody = document.selectFirst("html")
                            .selectFirst("body")
                                .selectFirst("div")
                                    .select("div").get(4)
                                        .select("div").get(0)
                                            .select("div").get(1)
                                                .selectFirst("table")
                                                    .selectFirst("tbody");
            if (tableBody == null) {
                logger.warn("No tableBody");
                return Collections.EMPTY_LIST;
            }
            int n = 0;
            while(true) {
                Element dateObj = tableBody.select("tr").get(n).select("td").get(0);
                if (dateObj == null) {
                    break;
                }
                logger.info("dateObj = {}", dateObj.text());
                Utils.sleep(3);
                n++;

            }
            //logger.debug(":: finished walking page");
        } catch (IOException e) {
            logger.warn("",e);
        }
        Utils.sleep(3);
        return Collections.EMPTY_LIST;
    }

}
