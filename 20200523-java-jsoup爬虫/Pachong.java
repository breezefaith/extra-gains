import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;

public class Pachong {
    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("http://www.lib.uestc.edu.cn/bysw/list60").get();

            Elements div = doc.select("div.grid_1_of_3 h4");
            Elements h4 = div.select("h4");
            for (Iterator<Element> iterator = h4.iterator(); iterator.hasNext(); ) {
                String[] texts = iterator.next().text().split(" ");
                System.out.println("书名：" + texts[0] + ",作者：" + texts[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
