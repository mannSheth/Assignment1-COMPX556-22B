import javax.print.attribute.standard.RequestingUserName;
import java.awt.*;
import java.util.Random;

//MannSheth_1364689
public class RectangleDrawn {
    private int x;
    private int y;
    private Rectangle rectangle;
    private Random rand;
    private float r;
    private float g;
    private float b;

    public RectangleDrawn(int x, int y, Rectangle rectangle)
    {
        this.x = x;
        this.y = y;
        this.rectangle = rectangle;
        rand = new Random();
        r = rand.nextFloat();
        g = rand.nextFloat()/3;
        b = rand.nextFloat()/3;
    }

    public void draw(Graphics gr, int scale)
    {
        int scaledX = x * scale;
        int scaledY = y * scale;
        int scaledW = rectangle.getWidth() * scale;
        int scaledH = rectangle.getHeight() * scale;

        Color randomColor = new Color(r, g, b);

        gr.setColor(randomColor);
        gr.fillRect(scaledX, scaledY, scaledW, scaledH);

        gr.setColor(Color.WHITE);
        gr.drawRect(scaledX, scaledY, scaledW, scaledH);
        gr.setFont(new Font("Fira", Font.PLAIN, 10));
        gr.drawString(rectangle.getId(), scaledX+  (scaledW/2) , scaledY+ (scaledH/2) );
    }
}
