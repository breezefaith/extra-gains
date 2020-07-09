import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Book {
    String name;
    String author;

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }
}

public class Test {
    public String get(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        URLConnection urlConnection = url.openConnection();
        System.out.println(urlConnection.getURL().toString());

        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();
        return sb.toString();
    }

    public List<Book> getBooks(String html) {
        List<Book> list = new ArrayList<>();
        Document d = Jsoup.parse(html);
        Elements elements = d.select("div h4[style='text-align:center;']");
        for (Iterator<Element> it = elements.iterator(); it.hasNext(); ) {
            Element elt = it.next();
            list.add(new Book(elt.ownText(), elt.getElementsByTag("p").first().ownText()));
        }
        return list;
    }

    public static void main(String[] args) {
        Test test = new Test();
        try {
            String html = test.get("http://www.lib.uestc.edu.cn/bysw/list60");
            List<Book> books = test.getBooks(html);
            for (Book b : books) {
                System.out.println("《" + b.name + "》-" + b.author);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
