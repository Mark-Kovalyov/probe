package mayton.html.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import mayton.html.DynamicDictionaryService;
import mayton.html.MemberWriterService;
import mayton.html.WalkerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Random;

@Singleton
public class HttpWalkerServiceImpl implements WalkerService {

    static Logger logger = LogManager.getLogger(HttpWalkerServiceImpl.class);

    @Inject
    private MemberWriterService memberWriterService;

    @Inject
    private DynamicDictionaryService dynamicDictionaryService;

    public void processHistogram(@NotNull Element histogram) {

    }

    public void processMember(@NotNull String url, int mid) {
        Connection connect = Jsoup.connect(url + "/forum/memberinfo.aspx?mid=" + mid);
        Document doc = null;
        try {
            doc = connect.get();
            logger.info(doc.title());
            Elements newsHeadlines = doc.select("table[id=member_messages_to_forums]");
            for (Element element : newsHeadlines) {
                logger.info("[table]======================================");
                //logger.info(element.toString());// element.attr("title") + " : " + element.absUrl("href"));
                for (Element tr : element.select("tr")) {
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
            logger.error("", e);
        }

    }

    public void processSeries(@NotNull String url, Iterable<Integer> mids) {
        mids.forEach((item) -> processMember(url, item));
    }

    @Override
    public void walk(@NotNull String url) {
        dynamicDictionaryService.getOrCreateEntityId("Asp.Net");
        dynamicDictionaryService.getOrCreateEntityId("C++");
        dynamicDictionaryService.getOrCreateEntityId("Java");
    }

}
