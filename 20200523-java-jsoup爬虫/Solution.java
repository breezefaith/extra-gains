import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ListIterator;

public class Solution {
    public void solve() {
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.lib.uestc.edu.cn/bysw/list60").timeout(3000).get();

            Elements divElts = doc.select("div.grid_1_of_3 h4");
            Elements h4Elts = divElts.select("h4");
            h4Elts.forEach((h4) -> {
                System.out.println("书名：" + h4.ownText() + " 作者：" + h4.getElementsByTag("p").first().text());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Solution().solve();
    }
}
