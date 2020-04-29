import java.awt.*;

public class MovingRectangle extends MovingShape
{
    public MovingRectangle(){
        super();
    }

    public MovingRectangle(int defaultSize){
        super(defaultSize);
    }

    public MovingRectangle(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, int pathType){
        super(x, y, w, h, mw, mh, bc, fc, pathType);
        this.message = null;
    }

    public MovingRectangle(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, String message, int pathType){
        super(x, y, w, h, mw, mh, bc, fc, pathType);
        this.message = message;
    }

    @Override
    public boolean contains(Point p)
    {
        return p.getX() >= x && p.getX() <= x + width && p.getY() >= y && p.getY() <= y + height;
    }

    @Override
    public void draw(Graphics g)
    {
        Color defaultColor = g.getColor();

        g.setColor(fillColor);
        g.fillRect(x,y,width,height);
        g.setColor(borderColor);
        g.drawRect(x, y, width, height);

        g.setColor(defaultColor);
        drawMessage(g);
    }

    @Override
    public double getArea()
    {
        return width * height * 1.0;
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
