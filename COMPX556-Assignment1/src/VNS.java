import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

//MannSheth_1364689
public class VNS extends Canvas {

    //the sheet of metal is always 100 wide;
    final static int frameWidth = 100;
    private static int totalAreaCheck;
    private static long tmax;
    private static final int kmax = 3;
    private static int k ;
    private static List<Integer> costList = new LinkedList<>();
    private static List<Double> timingList = new LinkedList<>();
    private static int cost = 1;

    private static Rectangle[] readCSV(String filePath)
    {
        List<Rectangle> recsList = new LinkedList<>();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while (true)
            {
                String row = br.readLine();
                if(row == null)
                {
                    break;
                }
                String[] cells = row.split(",");
                if (cells.length == 3)
                {

                    String id = cells[0];
                    int width = Integer.parseInt(cells[1]);
                    int height = Integer.parseInt(cells[2]);
                    recsList.add(new Rectangle(id, width, height));
                }
                else
                {
                    totalAreaCheck = Integer.parseInt(cells[0]);
                }

            }
            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return recsList.toArray(new Rectangle[recsList.size()]);
    }
    public static Configuration neighborChange(Configuration best, Configuration compare, double startTime)
    {
        if(compare.getMaxHeight() < best.getMaxHeight())
        {
            double endTime = System.nanoTime();
            costList.add(cost);
            timingList.add((endTime - startTime)/1e9);
            best = compare;
            k = 1;
        }
        else
        {
            k++;
        }
        return best;
    }

    /**
     * @param startingConfiguration this is the initial configuration that we will pass through
     * @return it will return an updated Configuration of lower height
     */
    public static Configuration BVNS(Configuration startingConfiguration)
    {
        Configuration highScore = startingConfiguration;
        Configuration randomConfiguration;
        Configuration localMinConfiguration;
        double startLoop = System.nanoTime();
        double t = 0;

        do {
            k = 1;
            do {
                randomConfiguration = highScore.shakeMethodHarder(k);
                localMinConfiguration = randomConfiguration.firstImprovementHarder(k);
                cost += randomConfiguration.getCost();
                highScore = neighborChange(highScore, localMinConfiguration, startLoop);
            }
            while(k <= kmax);
            t = System.nanoTime() - startLoop;
        }
        while (t <= tmax);
        return highScore;
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        if (args.length == 2)
        {
            String filepath = args[0];
            tmax = TimeUnit.NANOSECONDS.convert(Integer.parseInt(args[1]), TimeUnit.SECONDS);
            Rectangle[] rectangles = readCSV(filepath);

            System.out.println("File read and Rectangles Stored");
            int actualArea = 0;
            for(Rectangle rec : rectangles)
            {
                System.out.println(rec.getId());
                actualArea += rec.getArea();
            }

            System.out.println("Area that should of been calculated: " + totalAreaCheck);
            System.out.println("Area calculated: " + actualArea);

            Arrays.sort(rectangles, Collections.reverseOrder());
            for(Rectangle rec : rectangles)
            {
                if (rec.getWidth() < rec.getHeight())
                {
                    rec.rotate();
                }
            }
            Configuration initial = new Configuration(rectangles);
            Configuration result = BVNS(initial);
            System.out.println("MAX: HEIGHT: " + result.getMaxHeight());
            System.out.println("Amount of Improvements made: " + costList.size());
            System.out.println("Cost of best height: " + costList.get(costList.size() - 1));
            System.out.println("Time to get best improvement: " + timingList.get(timingList.size() - 1));

            new GUI(frameWidth, result.getMaxHeight() + 10, result.getDrawnRectangles());
            long endTime = System.nanoTime();
            double elapsedTime = ((endTime - startTime)/1e9);

            System.out.println(elapsedTime);
        }
        else
        {
            System.out.println("Invalid arguments must have filepath of problem and tMax in that order");
        }
    }
}
