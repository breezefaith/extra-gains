import java.awt.*;

public class MovingEllipse extends MovingShape
{
    public MovingEllipse()
    {
        super();
    }

    public MovingEllipse(int defaultSize)
    {
        super(defaultSize);
    }

    public MovingEllipse(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, int pathType)
    {
        super(x, y, w, h, mw, mh, bc, fc, pathType);
    }

    public MovingEllipse(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, String message, int pathType)
    {
        super(x, y, w, h, mw, mh, bc, fc, pathType);
        this.message = message;
    }

    @Override
    public boolean contains(Point p)
    {
        double dx, dy;
        Point EndPt = new Point(x + width, y + height);
        dx = (2 * p.x - x - EndPt.x) / (double) width;
        dy = (2 * p.y - y - EndPt.y) / (double) height;
        return dx * dx + dy * dy < 1.0;
    }

    @Override
    public void draw(Graphics g)
    {
        Color defaultColor = g.getColor();

        g.setColor(fillColor);
        g.fillOval(x, y, width, height);
        g.setColor(borderColor);
        g.drawOval(x, y, width, height);

        g.setColor(defaultColor);

        drawMessage(g);
    }

    @Override
    public double getArea()
    {
        return Math.PI * height * width / 4;
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
