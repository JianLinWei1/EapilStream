import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @auther JianLinWei
 * @date 2020-04-30 11:24
 */
public class VideoUtil {

    private static ExecutorService executorService = Executors.newCachedThreadPool();
    public  static   String LOCK = "LOCK";

    public static void main(String[] args) {
        codeH265();
    }

    public  static  void codeH265(){
        executorService.execute(() ->{
            try {
               /* synchronized (LOCK){
                    if(inputStream == null) {
                        System.out.println("线程等待");
                        LOCK.wait();

                    }
                }*/


//                InputStream inputStream = new ByteArrayInputStream(h265);
                Socket socket = new Socket("192.168.1.191", 8090);

                InputStream inputStream = socket.getInputStream();
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

                HKVRequest hkvRequest = new HKVRequest();
                hkvRequest.setStart_code("AVS");
                hkvRequest.setCmd_type('0');
                hkvRequest.setLength(4);

                hkvRequest.setType('4');
                hkvRequest.setChannle('0');
                hkvRequest.setRes("00");
                byte[] bytes = hkvRequest.getBytes(hkvRequest);


                //发送一个长度为12的字节数组
                outputStream.write(bytes);

                FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(socket.getInputStream());
               /* frameGrabber.setFrameRate(30);
                frameGrabber.setFormat("h265");
                frameGrabber.setVideoBitrate(20000);
                frameGrabber.setVideoOption("preset", "ultrafast");
                frameGrabber.setNumBuffers(250000);*/

                frameGrabber.start();

                Frame frame = frameGrabber.grab();


                CanvasFrame canvas = new CanvasFrame("摄像头");//新建一个窗口
                canvas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                canvas.setAlwaysOnTop(true);
                while (frame != null) {



                    canvas.showImage(frameGrabber.grab());


                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


    }



}