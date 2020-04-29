import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MovingStarsMap extends MovingRectangle
{
    private ArrayList<Point> points;
    protected String message = "CS230";

    public MovingStarsMap()
    {
        super();
        createPoints();
    }

    public MovingStarsMap(int defaultSize)
    {
        super(defaultSize);
        createPoints();
    }

    public MovingStarsMap(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, int pathType)
    {
        super(x, y, w, h, mw, mh, bc, fc, pathType);
        createPoints();
    }

    public MovingStarsMap(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, String message, int pathType)
    {
        super(x, y, w, h, mw, mh, bc, fc, pathType);
        this.message = message;
        createPoints();
    }

    private void createPoints()
    {
        Random random = new Random();
        Point p1 = new Point(random.nextInt(width), random.nextInt(height));
        Point p2 = new Point(random.nextInt(width), random.nextInt(height));
        Point p3 = new Point(random.nextInt(width), random.nextInt(height));
        points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
    }

    @Override
    public void draw(Graphics g)
    {
        Color defaultColor = g.getColor();

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(fillColor);
        Point p1 = points.get(0),
                p2 = points.get(1),
                p3 = points.get(2);
        g2d.drawLine(x, y, x + p1.x, y + p1.y);
        g2d.drawLine(x + p1.x, y + p1.y, x + p2.x, y + p2.y);
        g2d.drawLine(x + p2.x, y + p2.y, x + p3.x, y + p3.y);

        Stroke dash = new BasicStroke(2.5f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND, 3.5f, new float[]{8},
                0f);
        g2d.setStroke(dash);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, width, height);

        g.setColor(defaultColor);

        drawMessage(g);
    }

    public void drawMessage(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        FontMetrics metrics = g2d.getFontMetrics();
        int textWidth = metrics.stringWidth(message);
        int textHeight = metrics.getHeight();
        int textX = x + (width - textWidth) / 2;
        int textY = y + (height - textHeight) / 2 + metrics.getAscent();

        g2d.setBackground(null);
        g2d.drawString(message, textX, textY);
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
