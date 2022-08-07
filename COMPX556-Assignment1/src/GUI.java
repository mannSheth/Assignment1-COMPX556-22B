import javax.swing.*;
import java.awt.*;

//MannSheth_1364689
public class GUI extends JFrame
{

    RectangleDrawn[] tobeDrawn;
    private DrawCanvas canvas;
    private int SCALE = 8;

    public GUI(int frameWidth, int frameHeight, RectangleDrawn[] tobeDrawn)
    {
        canvas = new DrawCanvas();
        if (frameHeight > 300)
        {
            SCALE = 3;
        }
        canvas.setPreferredSize(new Dimension(frameWidth * SCALE, frameHeight * SCALE));
        canvas.setOpaque(true);
        canvas.setBackground(Color.white);
        this.setBackground(Color.DARK_GRAY);

        this.tobeDrawn = tobeDrawn;
        setContentPane(canvas);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setTitle("The Solution");
        setVisible(true);
    }

    private class DrawCanvas extends JPanel
    {
        @Override
        public void paintComponent(Graphics g)
        {
            if(tobeDrawn != null)
            {
                for(RectangleDrawn r: tobeDrawn)
                {
                    r.draw(g, SCALE);
                }
            }
        }
    }
}
