import javax.swing.JFrame;
import java.awt.*;

public class Window extends JFrame implements Runnable {

    private Graphics2D g2d;

    public Window() {
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g2d = (Graphics2D)this.getGraphics();
    }

    public void update(double dt) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }

    @Override
    public void run() {
        double lastFrameTime = 0.0;

        double time;
        double deltaTime;
        //game loop
        while (true) {
            time = Time.getTime();
            deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            update(deltaTime);

            try {
                Thread.sleep(30);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
