import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MainMenu extends JFrame implements Runnable {

    private Graphics2D g2d;
    private KListener keyListener = new KListener();
    private MListener mouseListener = new MListener();
    public Text startGame, exitGame, pong;
    public boolean isRunning = true;

    public MainMenu() {
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyListener);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);

        g2d = (Graphics2D) getGraphics();

        this.startGame = new Text("Start Game", new Font("Times New Roman", Font.PLAIN, 40), Constants.SCREEN_WIDTH / 2.0 - 140.0, Constants.SCREEN_HEIGHT / 2.0, Color.WHITE);
        this.exitGame = new Text("Exit", new Font("Times New Roman", Font.PLAIN, 40), Constants.SCREEN_WIDTH / 2.0 - 80.0, Constants.SCREEN_HEIGHT / 2.0 + 60, Color.WHITE);
        this.pong = new Text("Pong", new Font("Times New Roman", Font.PLAIN, 40), Constants.SCREEN_WIDTH / 2.0 - 155.0, 200, Color.WHITE);
    }

    public void update(double dt) {

        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        g2d.drawImage(dbImage, 0, 0, this);

        if (mouseListener.getMouseX() > startGame.x && mouseListener.getMouseX() < startGame.x + startGame.width
            && mouseListener.getMouseY() > startGame.y - startGame.height / 2 && mouseListener.getMouseY() < startGame.y + startGame.height / 2.0) {
            startGame.color = new Color(158, 158, 158);

            if (mouseListener.isMousePressed()) {
                Main.changeState(1);
            }
        }
        else {
            startGame.color = Color.WHITE;
        }
        if (mouseListener.getMouseX() > exitGame.x && mouseListener.getMouseX() < exitGame.x + exitGame.width
                && mouseListener.getMouseY() > exitGame.y - exitGame.height / 2 && mouseListener.getMouseY() < exitGame.y + exitGame.height / 2.0) {
            exitGame.color = new Color(158, 158, 158);

            if (mouseListener.isMousePressed()) {
                Main.changeState(2);
            }

        } else {
            exitGame.color = Color.WHITE;
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        startGame.draw(g2d);
        exitGame.draw(g2d);
        pong.draw(g2d);
    }

    public void stop() {
        isRunning = false;
    }

    @Override
    public void run() {
        double lastFrameTime = 0.0;

        double time;
        double deltaTime;
        //game loop
        while (isRunning) {
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
        this.dispose();
        return;
    }
}
