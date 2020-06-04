package org.example;

import com.google.inject.Inject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Logger;

public class WalkerServiceImpl implements WalkerService {

    public static int MAX_MEMBER_ID = 200_000;

    static Logger logger = Logger.getLogger(WalkerServiceImpl.class.getName());

    @Inject
    WriterService writerService;

    @Override
    public void walk(String url) {

        Random random = new Random();

        for(int i = 0 ; i < 10 ; i++) {
            Connection connect = Jsoup.connect(url + "/forum/memberinfo.aspx?mid=" + random.nextInt(MAX_MEMBER_ID));
            Document doc = null;
            try {
                doc = connect.get();
                logger.info(doc.title());
                Elements newsHeadlines = doc.select("table[id=member_messages_to_forums]");
                for (Element element : newsHeadlines) {
                    logger.info("[table]======================================");
                    //logger.info(element.toString());// element.attr("title") + " : " + element.absUrl("href"));
                    for(Element tr : element.select("tr")) {
                        logger.info("[tr] --------------------------------------");
                        // Element divTag = doc.getElementById("mydiv");
                        // String description = document.select("meta[name=description]").first().attr("content");
                        //              System.out.println("Description : " + description);                //
                        //              String keywords = document.select("meta[name=keywords]").first().attr("content");
                        //              System.out.println("Keywords : " + keywords);
                        // String keywords = document.select("meta[name=keywords]").first().attr("content");
                        // Elements links = document.select("a[href]");
                        // Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
                        // logger.info(element.toString());
                        int count;
                        double percentage;
                        int userId;
                        int userName;
                        Element childTrs = tr.selectFirst("tr");
                        if (childTrs != null) {
                            logger.info("[tr/tr] --------------------------------------");
                        }
                    }
                }

            } catch (IOException e) {
                logger.severe(e.toString());
            }

        }



    }

}
