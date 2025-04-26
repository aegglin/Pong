import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Window extends JFrame implements Runnable {

    private Graphics2D g2d;
    private KListener keyListener = new KListener();

    private Rect playerOne, ai, ballRect;
    private Ball ball;
    private PlayerController playerController;
    private AIController aiController;

    public Text leftScoreText;
    public Text rightScoreText;

    public boolean isRunning = true;

    public Window() {
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyListener);
        Constants.TOOLBAR_HEIGHT = this.getInsets().top;
        Constants.INSETS_BOTTOM = this.getInsets().bottom;
        g2d = (Graphics2D)this.getGraphics();

        playerOne = new Rect(Constants.HZ_PADDING, 40, Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, Constants.PADDLE_COLOR);
        playerController = new PlayerController(playerOne, keyListener);

        ai = new Rect(Constants.SCREEN_WIDTH - Constants.PADDLE_WIDTH - Constants.HZ_PADDING, 40, Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, Constants.PADDLE_COLOR);

        leftScoreText = new Text(0, new Font("Times New Roman", Font.PLAIN, Constants.TEXT_SIZE), Constants.TEXT_X_POS, Constants.TEXT_Y_POS);
        rightScoreText = new Text(0, new Font("Times New Roman", Font.PLAIN, Constants.TEXT_SIZE), Constants.SCREEN_WIDTH - Constants.TEXT_X_POS - Constants.TEXT_SIZE, Constants.TEXT_Y_POS);

        ballRect = new Rect(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2, Constants.BALL_WIDTH, Constants.BALL_HEIGHT, Constants.PADDLE_COLOR);
        ball = new Ball(ballRect, playerOne, ai, leftScoreText, rightScoreText);

        aiController = new AIController(new PlayerController(ai), ballRect);


    }

    public void stop() {
        isRunning = false;
    }

    public void update(double dt) {

        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        g2d.drawImage(dbImage, 0, 0, this);

        playerController.update(dt);
        aiController.update(dt);
        ball.update(dt);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        leftScoreText.draw(g2d);
        rightScoreText.draw(g2d);

        playerOne.draw(g2d);
        ai.draw(g2d);
        ball.rect.draw(g2d);
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
