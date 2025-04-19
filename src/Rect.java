import java.awt.Graphics2D;
import java.awt.Color;

public class Rect {

    private int x, y, width, height;
    private Color color;

    public Rect(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillRect(x, y, width, height);
    }
}
