import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ListIterator;

public class Spider {
    public static void main(String[] args) {
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.lib.uestc.edu.cn/bysw/list60").timeout(3000).get();

            Elements divs = doc.getElementsByClass("grid_1_of_3 images_1_of_3 top_grid");
            for (ListIterator<Element> it = divs.listIterator(); it.hasNext(); ) {
                Element div = it.next();
                Element h = div.getElementsByTag("h4").first();
                Element p = h.getElementsByTag("p").first();
                System.out.println(String.format("书名：%s，作者：%s", h.ownText(), p.ownText()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
