import java.awt.*;

//MannSheth_1364689
public class Rectangle implements Comparable<Rectangle>
{
    private int width;
    private int height;
    private int area;
    private String id;

    public Rectangle(String id, int width, int height)
    {
        this.id = id;
        this.width = width;
        this.height = height;
        area = width*height;
    }

    /**
     * @param otherRec the other Rectangle you want to compare to
     * @return returns a negative positive or zero depending on if this shape is less than greater or equal to given rectangle
     */
    @Override
    public int compareTo(Rectangle otherRec)
    {
        return this.getArea() - otherRec.getArea();
    }

    public void rotate()
    {
        int holder = width;
        width = height;
        height = holder;
    }

    public boolean equals(Rectangle otherRec)
    {
        if(this == otherRec)
        {
            return true;
        }
        if(otherRec == null || getClass() != otherRec.getClass())
        {
            return false;
        }

        return this.getHeight() == otherRec.getHeight() && this.getWidth() == otherRec.getWidth();
    }

    public int getArea() {
        return area;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getId() {
        return id;
    }
}
