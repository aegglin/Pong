public class Time {

    public static double timeStarted = System.nanoTime(); // System current time in nanoseconds

    public static double getTime() {
        return (System.nanoTime() - timeStarted) * 1E-9;
    }
}
