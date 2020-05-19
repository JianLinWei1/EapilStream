import org.bytedeco.javacv.CanvasFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * @auther JianLinWei
 * @date 2020-04-29 15:00
 */
public class Test2 {

    public static void main(String[] args) {


        try {
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
            String filepath = "D:\\1.h265";
            File file = new File(filepath);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fos = new FileOutputStream(file);



            while (true) {
            Thread.sleep(50);
                //包头
                byte[] buf = new byte[8];
               inputStream.read(buf);




                HKVHeader hkvHeader = new HKVHeader(buf);
                if(hkvHeader.getCmd_type() == 1 ){
                    System.out.println("header length:"+ hkvHeader.getLength());
                    if(hkvHeader == null || hkvHeader.getLength() <= 0 )
                        continue;
                    //包体
                    byte[]  buf_body =  new byte[hkvHeader.getLength()];
                    inputStream.read(buf_body);
                    HKVVideoHeader hkvVideoHeader = new HKVVideoHeader(buf_body);
                    //帧类型 1  I  2 P  3 F(音频）
                    if(hkvVideoHeader.getFtype() == 0)
                        continue;
                    //视频
                    if(hkvVideoHeader.getFtype() == 1  || hkvVideoHeader.getFtype() == 2 ){
                        System.out.println("编码："+hkvVideoHeader.getPtype());
                        //数据
                        byte[]  h265  = new byte[buf_body.length - 32];
                        System.arraycopy(buf_body, 32, h265, 0,buf_body.length - 32);
                        System.out.println("视频长度：" + h265.length);
                        System.out.println("Fsize:" + hkvVideoHeader.getFsize());
                       fos.write(h265, 0, h265.length);





                    }



                }






            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

