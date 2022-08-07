import javax.naming.InitialContext;
import java.io.ObjectInputFilter;
import java.util.*;

//MannSheth_1364689
public class Configuration {

    private final int boxWidth = 100;
    private RectangleDrawn[] DrawnRectangles;
    private Rectangle[] rectangles;
    private int[] yBottomLine;
    private int maxHeight = -1;
    private int cost = 0;

    public Configuration(Rectangle[] rectangles)
    {
        this.rectangles = rectangles;
        yBottomLine = new int[100];
        DrawnRectangles = placeRectangle(this.rectangles);
    }

    public int getCost() { return cost; }

    public RectangleDrawn[] getDrawnRectangles() {
        return DrawnRectangles;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public Configuration copy()
    {
        return new Configuration(rectangles);
    }

    /**
     * @param k This signifies the which neighborhood we are in
     * @return we return a new Configuration with the updated positions of the rectangles after the shuffle
     */
    public Configuration shakeMethod(int k)
    {
        Random rand = new Random();
        int randomIndex = rand.nextInt(rectangles.length);
        Rectangle[] newOrder = Arrays.stream(rectangles).map(rec -> new Rectangle(rec.getId(), rec.getWidth(), rec.getHeight())).toArray(Rectangle[]::new);
        Configuration res;
        if(k == 1)
        {
            LinkedList<Rectangle> editableRecOrder = new LinkedList<>(Arrays.asList(newOrder));
            Rectangle holder = editableRecOrder.get(randomIndex);
            editableRecOrder.remove(randomIndex);
            editableRecOrder.add(holder);
            newOrder = editableRecOrder.toArray(new Rectangle[0]);
            res = new Configuration(newOrder);
        }
        else if(k == 2)
        {
            LinkedList<Rectangle> editableRecOrder = new LinkedList<>(Arrays.asList(newOrder));
            Rectangle holder = editableRecOrder.get(randomIndex);
            holder.rotate();
            editableRecOrder.remove(randomIndex);
            editableRecOrder.add(holder);
            newOrder = editableRecOrder.toArray(new Rectangle[0]);
            res = new Configuration(newOrder);
        }
        else
        {
            newOrder[randomIndex].rotate();
            res = new Configuration(newOrder);
        }
        return res;
    }

    public Configuration shakeMethodHarder(int k)
    {
        Random rand = new Random();
        int randomIndex = rand.nextInt(rectangles.length);
        Rectangle[] newOrder = Arrays.stream(rectangles).map(rec -> new Rectangle(rec.getId(), rec.getWidth(), rec.getHeight())).toArray(Rectangle[]::new);
        Configuration res;

        if(k == 1)
        {
            LinkedList<Rectangle> editableRecOrder = new LinkedList<>(Arrays.asList(newOrder));
            Rectangle holder = editableRecOrder.get(randomIndex);
            editableRecOrder.remove(randomIndex);
            editableRecOrder.add(holder);
            for (int i = 1; i <= 2; i++) {
                randomIndex = rand.nextInt(rectangles.length);
                holder = editableRecOrder.get(randomIndex);
                editableRecOrder.remove(randomIndex);
                editableRecOrder.add(holder);
            }

            newOrder = editableRecOrder.toArray(new Rectangle[0]);
            res = new Configuration(newOrder);
        }
        else if(k == 2)
        {
            LinkedList<Rectangle> editableRecOrder = new LinkedList<>(Arrays.asList(newOrder));
            //move 1
            Rectangle holder = editableRecOrder.get(randomIndex);
            holder.rotate();
            editableRecOrder.remove(randomIndex);
            editableRecOrder.add(holder);
            for (int i = 1; i <= 2; i++) {
                randomIndex = rand.nextInt(rectangles.length);
                holder = editableRecOrder.get(randomIndex);
                holder.rotate();
                editableRecOrder.remove(randomIndex);
                editableRecOrder.add(holder);
            }
            newOrder = editableRecOrder.toArray(new Rectangle[0]);
            res = new Configuration(newOrder);
        }
        else
        {
            newOrder[randomIndex].rotate();
            for (int i = 1; i <= 2; i++) {
                randomIndex = rand.nextInt(rectangles.length);
                newOrder[randomIndex].rotate();
            }
            res = new Configuration(newOrder);
        }
        return res;
    }

    public Configuration shakeMethodHarderMix(int k)
    {
        Random rand = new Random();
        int randomIndex = rand.nextInt(rectangles.length);
        Rectangle[] newOrder = Arrays.stream(rectangles).map(rec -> new Rectangle(rec.getId(), rec.getWidth(), rec.getHeight())).toArray(Rectangle[]::new);
        Configuration res;
        //This will move a random rectangle to the end of the array/list
        if(k == 1)
        {
            LinkedList<Rectangle> editableRecOrder = new LinkedList<>(Arrays.asList(newOrder));
            Rectangle holder = editableRecOrder.get(randomIndex);
            editableRecOrder.remove(randomIndex);
            editableRecOrder.add(holder);
            newOrder = editableRecOrder.toArray(new Rectangle[0]);
            res = new Configuration(newOrder);
        }
        else if(k == 2)
        {
            LinkedList<Rectangle> editableRecOrder = new LinkedList<>(Arrays.asList(newOrder));
            Rectangle holder = editableRecOrder.get(randomIndex);
            holder.rotate();
            editableRecOrder.remove(randomIndex);
            editableRecOrder.add(holder);
            newOrder = editableRecOrder.toArray(new Rectangle[0]);
            res = new Configuration(newOrder);
        }
        else if(k == 3)
        {
            LinkedList<Rectangle> editableRecOrder = new LinkedList<>(Arrays.asList(newOrder));
            //move 1
            Rectangle holder = editableRecOrder.get(randomIndex);
            editableRecOrder.remove(randomIndex);
            editableRecOrder.add(holder);
            for (int i = 1; i <= 1; i++) {
                randomIndex = rand.nextInt(rectangles.length);
                holder = editableRecOrder.get(randomIndex);
                editableRecOrder.remove(randomIndex);
                editableRecOrder.add(holder);
            }

            newOrder = editableRecOrder.toArray(new Rectangle[0]);
            res = new Configuration(newOrder);
        }
        else if(k == 4)
        {
            LinkedList<Rectangle> editableRecOrder = new LinkedList<>(Arrays.asList(newOrder));
            //move 1
            Rectangle holder = editableRecOrder.get(randomIndex);
            holder.rotate();
            editableRecOrder.remove(randomIndex);
            editableRecOrder.add(holder);
            for (int i = 1; i <= 1; i++) {
                randomIndex = rand.nextInt(rectangles.length);
                holder = editableRecOrder.get(randomIndex);
                holder.rotate();
                editableRecOrder.remove(randomIndex);
                editableRecOrder.add(holder);
            }
            newOrder = editableRecOrder.toArray(new Rectangle[0]);
            res = new Configuration(newOrder);
        }
        else if(k == 5)
        {
            newOrder[randomIndex].rotate();
            res = new Configuration(newOrder);
        }
        else
        {
            newOrder[randomIndex].rotate();
            for (int i = 1; i <= 1; i++) {
                randomIndex = rand.nextInt(rectangles.length);
                newOrder[randomIndex].rotate();
            }
            res = new Configuration(newOrder);
        }
        return res;
    }

    /**
     * @param k this is the neighborhood we want to search to get the first improvement
     * @return a new Configuration that is an improvement or it will return the same
     */
    public Configuration firstImprovement(int k)
    {
        Configuration currentConfig = new Configuration(rectangles);

        Configuration newConf;
        Configuration result = currentConfig;

        for (int i = 0; i < rectangles.length; i++)
        {
            Rectangle[] newOrder = Arrays.stream(rectangles).map(rec -> new Rectangle(rec.getId(), rec.getWidth(), rec.getHeight())).toArray(Rectangle[]::new);
            if(k == 1)
            {
                LinkedList<Rectangle> editableRecOrder = new LinkedList<>(Arrays.asList(newOrder));
                Rectangle holder = editableRecOrder.get(i);
                editableRecOrder.remove(i);
                editableRecOrder.add(holder);
                newOrder = editableRecOrder.toArray(new Rectangle[0]);
                newConf = new Configuration(newOrder);
            }
            else if(k == 2)
            {
                LinkedList<Rectangle> editableRecOrder = new LinkedList<>(Arrays.asList(newOrder));
                Rectangle holder = editableRecOrder.get(i);
                holder.rotate();
                editableRecOrder.remove(i);
                editableRecOrder.add(holder);
                newOrder = editableRecOrder.toArray(new Rectangle[0]);
                newConf = new Configuration(newOrder);

            }
            else
            {
                newOrder[i].rotate();
                newConf = new Configuration(newOrder);
            }

            if(newConf.maxHeight < result.maxHeight)
            {
                cost = i + 1;
                result = newConf;
                return result;
            }
        }
        cost = rectangles.length;
        return result;

    }

    public Configuration firstImprovementHarderMix(int k)
    {
        Random rand = new Random();
        int randomIndex;
        Configuration currentConfig = new Configuration(rectangles);

        Configuration newConf;
        Configuration result = currentConfig;

        for (int i = 0; i < 1000; i++)
        {
            randomIndex = rand.nextInt(rectangles.length);
            Rectangle[] newOrder = Arrays.stream(rectangles).map(rec -> new Rectangle(rec.getId(), rec.getWidth(), rec.getHeight())).toArray(Rectangle[]::new);
            if(k == 1)
            {
                LinkedList<Rectangle> editableRecOrder = new LinkedList<>(Arrays.asList(newOrder));
                Rectangle holder = editableRecOrder.get(randomIndex);
                editableRecOrder.remove(randomIndex);
                editableRecOrder.add(holder);
                newOrder = editableRecOrder.toArray(new Rectangle[0]);
                newConf = new Configuration(newOrder);
            }
            else if(k == 2)
            {
                LinkedList<Rectangle> editableRecOrder = new LinkedList<>(Arrays.asList(newOrder));
                Rectangle holder = editableRecOrder.get(randomIndex);
                holder.rotate();
                editableRecOrder.remove(randomIndex);
                editableRecOrder.add(holder);
                newOrder = editableRecOrder.toArray(new Rectangle[0]);
                newConf = new Configuration(newOrder);
            }
            else if(k == 3)
            {
                LinkedList<Rectangle> editableRecOrder = new LinkedList<>(Arrays.asList(newOrder));
                Rectangle holder = editableRecOrder.get(randomIndex);
                editableRecOrder.remove(randomIndex);
                editableRecOrder.add(holder);
                for (int j = 1; j <= 1; j++) {
                    randomIndex = rand.nextInt(rectangles.length);
                    holder = editableRecOrder.get(randomIndex);
                    editableRecOrder.remove(randomIndex);
                    editableRecOrder.add(holder);
                }
                newOrder = editableRecOrder.toArray(new Rectangle[0]);
                newConf = new Configuration(newOrder);
            }
            else if(k == 4)
            {
                LinkedList<Rectangle> editableRecOrder = new LinkedList<>(Arrays.asList(newOrder));
                Rectangle holder = editableRecOrder.get(randomIndex);
                holder.rotate();
                editableRecOrder.remove(randomIndex);
                editableRecOrder.add(holder);
                for (int j = 1; j <= 1; j++) {
                    randomIndex = rand.nextInt(rectangles.length);
                    holder = editableRecOrder.get(randomIndex);
                    holder.rotate();
                    editableRecOrder.remove(randomIndex);
                    editableRecOrder.add(holder);
                }
                newOrder = editableRecOrder.toArray(new Rectangle[0]);
                newConf = new Configuration(newOrder);

            }
            else if(k == 5)
            {
                newOrder[randomIndex].rotate();
                newConf = new Configuration(newOrder);
            }
            else
            {
                newOrder[randomIndex].rotate();
                for (int j = 1; j <= 1; j++) {
                    randomIndex = rand.nextInt(rectangles.length);
                    newOrder[randomIndex].rotate();
                }
                newConf = new Configuration(newOrder);
            }

            if(newConf.maxHeight < result.maxHeight)
            {
                cost = i + 1;
                result = newConf;
                return result;
            }
        }
        cost = 1000;
        return result;
        //System.out.println("IMPROVED: " + result.maxHeight);

    }

    public Configuration firstImprovementHarder(int k)
    {
        Random rand = new Random();
        int randomIndex;
        //System.out.println("ABOUT TO IMPROVE: " + current.maxHeight);
        Configuration currentConfig = new Configuration(rectangles);

        Configuration newConf;
        Configuration result = currentConfig;

        for (int i = 0; i < 1000; i++)
        {
            randomIndex = rand.nextInt(rectangles.length);
            Rectangle[] newOrder = Arrays.stream(rectangles).map(rec -> new Rectangle(rec.getId(), rec.getWidth(), rec.getHeight())).toArray(Rectangle[]::new);
            if(k == 1)
            {
                LinkedList<Rectangle> editableRecOrder = new LinkedList<>(Arrays.asList(newOrder));
                //move 1
                Rectangle holder = editableRecOrder.get(randomIndex);
                editableRecOrder.remove(randomIndex);
                editableRecOrder.add(holder);
                for (int j = 1; j <= 2; j++) {
                    randomIndex = rand.nextInt(rectangles.length);
                    holder = editableRecOrder.get(randomIndex);
                    editableRecOrder.remove(randomIndex);
                    editableRecOrder.add(holder);
                }
                newOrder = editableRecOrder.toArray(new Rectangle[0]);
                newConf = new Configuration(newOrder);
            }
            else if(k == 2)
            {
                LinkedList<Rectangle> editableRecOrder = new LinkedList<>(Arrays.asList(newOrder));
                //move 1
                Rectangle holder = editableRecOrder.get(randomIndex);
                holder.rotate();
                editableRecOrder.remove(randomIndex);
                editableRecOrder.add(holder);
                for (int j = 1; j <= 2; j++) {
                    randomIndex = rand.nextInt(rectangles.length);
                    holder = editableRecOrder.get(randomIndex);
                    holder.rotate();
                    editableRecOrder.remove(randomIndex);
                    editableRecOrder.add(holder);
                }
                newOrder = editableRecOrder.toArray(new Rectangle[0]);
                newConf = new Configuration(newOrder);

            }
            else
            {
                newOrder[randomIndex].rotate();
                for (int j = 1; j <= 2 ; j++) {
                    randomIndex = rand.nextInt(rectangles.length);
                    newOrder[randomIndex].rotate();
                }
                newConf = new Configuration(newOrder);
            }

            if(newConf.maxHeight < result.maxHeight)
            {
                cost = i + 1;
                result = newConf;
                return result;
            }
        }
        cost = 1000;
        return result;
        //System.out.println("IMPROVED: " + result.maxHeight);

    }

    private RectangleDrawn[] placeRectangle(Rectangle[] rectangles)
    {
        int x = 0, toBeDrawnIndex = 0, passes = 0;
        List<Rectangle> toDraw = new ArrayList<>(Arrays.asList(rectangles));
        RectangleDrawn[] drawRecArray = new RectangleDrawn[rectangles.length];
        while(0 < toDraw.size())
        {
            if (1 < passes) {
                if (x < boxWidth && x >= 0) {
                    int yOld = yBottomLine[x];
                    while (x < boxWidth && x >= 0 && yOld <= yBottomLine[x]) {
                        x++;
                    }
                }
                if (x >= boxWidth && x < 0) {
                    x = 0;
                }
            }

            for (int i = 0; i < toDraw.size(); i++)
            {
                int width = toDraw.get(i).getWidth();
                int height = toDraw.get(i).getHeight();

                int widthToFitIn = 0;

                for (int j = x; j < boxWidth && yBottomLine[j] <= yBottomLine[x]; j++)
                {
                    widthToFitIn++;
                }

                if(width <= widthToFitIn)
                {
                    drawRecArray[toBeDrawnIndex] = new RectangleDrawn(x, yBottomLine[x], toDraw.get(i));
                    toBeDrawnIndex++;
                    int newY = yBottomLine[x] + height;

                    for (int prevRecPos = x; prevRecPos < x + width ; prevRecPos++) {
                        yBottomLine[prevRecPos] = newY;
                    }
                    x += width;

                    passes = 0;

                    toDraw.remove(i);
                    break;
                }
                else if (passes > 10)
                {
                    int bestX = 0;
                    int lowestMaxY = -1;
                    for (int left = 0; left < boxWidth - width; left++)
                    {
                        int yMax = 0;
                        for (int xValue = left; xValue < left + width; xValue++)
                        {
                            if (yBottomLine[xValue] > yMax)
                            {
                                yMax = yBottomLine[xValue];
                            }
                        }
                        if (lowestMaxY == -1 || lowestMaxY > yMax)
                        {
                            lowestMaxY = yMax;
                            bestX = left;
                        }
                    }
                    drawRecArray[toBeDrawnIndex] = new RectangleDrawn(bestX, lowestMaxY, toDraw.get(i));
                    toBeDrawnIndex++;
                    for (int whereShapePlaced = bestX; whereShapePlaced < bestX + width; whereShapePlaced++)
                    {
                        yBottomLine[whereShapePlaced] = lowestMaxY + height;
                    }

                    x = bestX + width;

                    passes = 0;

                    toDraw.remove(i);
                    break;
                }
            }

            passes++;
        }
        for(int y : yBottomLine)
        {
            if(y > maxHeight)
            {
                maxHeight = y;
            }
        }
        return drawRecArray;
    }
}
