import java.awt.*;

public class MovingSpinningCircle extends MovingEllipse
{
    private int startAngle = 0;
    public static final int ROTATION_SPEED = 20;
    public static final int SHOWN_FAN_ANGLE = 90;

    public MovingSpinningCircle()
    {
        super();
        width = height = width < height ? width : height;
    }

    public MovingSpinningCircle(int defaultSize)
    {
        super(defaultSize);
        width = height = width < height ? width : height;
    }

    public MovingSpinningCircle(int x, int y, int w, int mw, int mh, Color bc, Color fc, int pathType)
    {
        super(x, y, w, w, mw, mh, bc, fc, pathType);
        width = height = w;
    }

    public MovingSpinningCircle(int x, int y, int w, int mw, int mh, Color bc, Color fc, String message,int pathType)
    {
        super(x, y, w, w, mw, mh, bc, fc, pathType);
        width = height = w;
        this.message = message;
    }

    public MovingSpinningCircle(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, int pathType)
    {
        super(x, y, w, h, mw, mh, bc, fc, pathType);
        width = height = width < height ? width : height;
    }

    public MovingSpinningCircle(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, String message, int pathType)
    {
        super(x, y, w, h, mw, mh, bc, fc, pathType);
        this.message = message;
        width = height = width < height ? width : height;
    }

    @Override
    public void draw(Graphics g)
    {
        Color defaultColor = g.getColor();

        g.setColor(fillColor);
        g.fillArc(x, y, width, height, startAngle, SHOWN_FAN_ANGLE);
        g.fillArc(x, y, width, height, startAngle + 2 * SHOWN_FAN_ANGLE, SHOWN_FAN_ANGLE);
        startAngle = (startAngle + ROTATION_SPEED) % 360;
        g.setColor(borderColor);
        g.drawOval(x, y, width, height);

        g.setColor(defaultColor);

        drawMessage(g);
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
