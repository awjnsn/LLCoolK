import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Class to draw pretty K_n graphs
 */
public class LLCoolK
{
    /**
     * Main method, creates JFrame and adds LLCoolPane to it
     * @param args args[0] positive integer n, args[1] dimension of window
     */
    public static void main(String[] args)
    {
        //In case of insufficient args
        if(args.length != 2)
        {
            System.out.println("Usage java LLCoolK [n >= 0] [dimension >= 0]");
        }

        else
        {
            int n = Integer.parseInt(args[0]);

            int dim = Integer.parseInt(args[1]);

            //In case of back n or the dimension being to small
            if(n < 0 || dim < 0)
            {
                System.out.println("Usage java LLCoolK [n >= 0] [dimension >= 0]");
            }

            //Create the frame and add the panel to it
            else
            {
                JFrame frame = new JFrame("k_" + n);

                frame.setSize(dim, dim);

                frame.setLocation(0, 0);

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                frame.setContentPane(new LLCoolKPane(n, dim));

                frame.setVisible(true);

                frame.setResizable(false);
            }
        }
    }

    /**
     * Class to draw k_n
     */
    private static class LLCoolKPane extends JPanel
    {
        private BufferedImage bufferedImage;

        /**
         * Method to construct a LLCoolKPane that will draw k_n
         * @param n k_n
         * @param dim Dimension of the frame enclosing the pane
         */
        LLCoolKPane(int n, int dim)
        {
            this.bufferedImage = new BufferedImage(dim, dim, BufferedImage.TYPE_INT_RGB);

            Graphics graphics = this.bufferedImage.getGraphics();

            this.setFocusable(true);

            graphics.setColor(Color.WHITE);

            graphics.fillRect(0, 0, dim, dim);

            Point[] points = new Point[n];

            //Radius such that k_n will fill up 80% of the space in the frame
            int radius = (int) (dim * 0.4);

            graphics.setColor(Color.BLUE);

            //Space the points evenly using a circle
            for(int i = 0; i < points.length; i++)
            {
                int x = (int) (dim / 2 + radius * Math.cos(2 * Math.PI * i / n));

                int y = (int) (dim / 2 + radius * Math.sin(2 * Math.PI * i / n));

                graphics.fillOval(x, y, dim / 100, dim / 100);

                points[i] = new Point(x, y);
            }

            graphics.setColor(Color.RED);

            //Draw a line between each point, will take n^2 / 2 cycles
            for(int i = 0; i < points.length - 1; i++)
            {
                for(int j = i; j < points.length; j++)
                {
                    int x1 = (int) points[i].getX() + (dim / 200);

                    int y1 = (int) points[i].getY() + (dim / 200);

                    int x2 = (int) points[j].getX() + (dim / 200);

                    int y2 = (int) points[j].getY() + (dim / 200);

                    graphics.drawLine(x1, y1, x2, y2);
                }
            }
        }

        /**
         * Method to paint the changes to the panel
         * @param g Graphics object
         */
        @Override
        protected void paintComponent(Graphics g)
        {
            g.drawImage(this.bufferedImage, 0, 0, this.getWidth(), this.getHeight(), null);
        }
    }
}