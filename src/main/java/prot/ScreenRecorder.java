package prot;

import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.*;
import org.bytedeco.ffmpeg.global.*;
import org.bytedeco.javacv.Frame;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenRecorder {
    private FFmpegFrameRecorder recorder;
    private Robot robot;
    private Rectangle captureSize;
    private boolean running;
    private Thread recordingThread;
    private Java2DFrameConverter converter = new Java2DFrameConverter();

    public ScreenRecorder(JFrame frame) {
        try {
            if (!frame.isVisible()) {
            frame.setVisible(true);
            }
            robot = new Robot();

            // Location ng jframe sa screen
            Point location = frame.getLocationOnScreen();
            Dimension size = frame.getSize();
            captureSize = new Rectangle(location.x, location.y, size.width, size.height);
            
            // for specific filename with date & time
            String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new java.util.Date());
            String filename = "recordings/session_" + timestamp + ".avi";
            
            // recording file format
            recorder = new FFmpegFrameRecorder(filename, size.width, size.height);
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_MPEG4);
            recorder.setFormat("avi");
            recorder.setFrameRate(20);
            recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        running = true;
        recordingThread = new Thread(() -> {
            try {
                recorder.start();
                while (running) {
                BufferedImage screen = robot.createScreenCapture(captureSize);
                BufferedImage formattedImage = new BufferedImage(
                    screen.getWidth(), screen.getHeight(), BufferedImage.TYPE_3BYTE_BGR
                );
                Graphics2D g = formattedImage.createGraphics();
                g.drawImage(screen, 0, 0, null);
                g.dispose();

                Frame frame = converter.convert(formattedImage);
                recorder.record(frame);

                Thread.sleep(50);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        recordingThread.start();
    }

    public void stop() {
        running = false;
        try {
            recordingThread.join();
            recorder.stop();
            recorder.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}