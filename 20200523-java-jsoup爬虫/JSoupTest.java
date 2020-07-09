import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;

public class JSoupTest {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("http://www.lib.uestc.edu.cn/bysw/list60")
                .userAgent("Chrome")
                .timeout(3000)
                .get();

        Elements elements = doc.select("div.grid_1_of_3.images_1_of_3.top_grid h4[style='text-align:center;']");
        for (Iterator<Element> it = elements.iterator(); it.hasNext(); ) {
            Element elt = it.next();
            Element autElt = elt.getElementsByTag("p").first();
            System.out.println(String.format("书名：%s，作者：%s", elt.ownText(), autElt.ownText()));
        }
    }
}
