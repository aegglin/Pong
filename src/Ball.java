public class Ball {
    public Rect rect;
    public Rect leftPaddle, rightPaddle;

    private double vy = 10.0;
    private double vx = -150.0;

    public Ball(Rect rect, Rect leftPaddle, Rect rightPaddle) {
        this.rect = rect;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
    }

    public void update(double dt) {

    }
}
