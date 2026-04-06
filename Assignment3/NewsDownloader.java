package Assignment3;

public class NewsDownloader implements Runnable {
    private final String sourceName;
    private final int delaySeconds;

    public NewsDownloader(String sourceName, int delaySeconds) {
        this.sourceName = sourceName;
        this.delaySeconds = delaySeconds;
    }

    @Override
    public void run() {
        System.out.println("Downloading from: " + sourceName + "...");
        try {
            Thread.sleep(delaySeconds * 1000L);
            System.out.println(sourceName + " download complete!");
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            System.out.println(sourceName + " download interrupted.");
        }
    }
}
