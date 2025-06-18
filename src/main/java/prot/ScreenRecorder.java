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

    public ScreenRecorder(String filename, JFrame frame) {
        try {
            if (!frame.isVisible()) {
            frame.setVisible(true);
            }
            robot = new Robot();

            // Location ng jframe sa screen
            Point location = frame.getLocationOnScreen();
            Dimension size = frame.getSize();
            captureSize = new Rectangle(location.x, location.y, size.width, size.height);
            
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
                    BufferedImage image = robot.createScreenCapture(captureSize);
                    Frame frame = converter.convert(image);
                    recorder.record(frame);
                    Thread.sleep(50); //
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