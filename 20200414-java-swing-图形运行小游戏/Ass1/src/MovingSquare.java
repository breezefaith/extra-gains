import java.awt.*;

public class MovingSquare extends MovingRectangle
{
    public MovingSquare()
    {
        super();
        width = height = width < height ? width : height;
    }

    public MovingSquare(int defaultSize)
    {
        super(defaultSize);
        width = height = width < height ? width : height;
    }

    public MovingSquare(int x, int y, int w, int mw, int mh, Color bc, Color fc, int pathType)
    {
        super(x, y, w, w, mw, mh, bc, fc, pathType);
        width = height = w;
    }

    public MovingSquare(int x, int y, int w, int mw, int mh, Color bc, Color fc, String message, int pathType)
    {
        super(x, y, w, w, mw, mh, bc, fc, pathType);
        width = height = w;
        this.message = message;
    }

    public MovingSquare(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, int pathType)
    {
        super(x, y, w, h, mw, mh, bc, fc, pathType);
        width = height = width < height ? width : height;
    }

    public MovingSquare(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, String message, int pathType)
    {
        super(x, y, w, h, mw, mh, bc, fc, pathType);
        width = height = width < height ? width : height;
        this.message = message;
    }

    @Override
    public void setHeight(int h)
    {
        super.setHeight(h);
        width = height = h;
    }

    @Override
    public void setWidth(int w)
    {
        super.setWidth(w);
        width = height = w;
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
