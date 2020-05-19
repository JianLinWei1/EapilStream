import java.io.*;

/**
 * @auther JianLinWei
 * @date 2020-05-15 20:25
 */
public class ByteUtil {

    public  static  byte[]  obj2Bytes(Object obj){
        return  null;
    }


    public  static  void  createH265File(byte[] bytes) throws IOException {
        String filepath = "D:\\1.h265";
        File file = new File(filepath);
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bytes, 0, bytes.length);
        fos.flush();
        fos.close();
    }

    }
