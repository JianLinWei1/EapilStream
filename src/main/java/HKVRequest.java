import java.io.*;
import java.nio.ByteBuffer;

/**
 * @auther JianLinWei
 * @date 2020-04-30 11:21
 */
public class HKVRequest  implements Serializable   {
    private  String start_code; //起始值“AVS"
    private   char cmd_type ; //请求类型  0 请求 1 应答
    private   int length ;  //数据长度
    /**
     * #define STREAM_MAJ		    (4)     //主码流
     * #define STREAM_SUB 		    (5)     //副码流
     * #define STREAM_FACE_IPC  (6)     //人脸摄像头
     * #define STREAM_PLAYBCAK  (7)     //录像文件回放
     */
    private  char  type ; //码流类型
    private  char channle ; //通道
    private  String res;//预留2


    public String getStart_code() {
        return start_code;
    }

    public void setStart_code(String start_code) {
        this.start_code = start_code;
    }

    public char getCmd_type() {
        return cmd_type;
    }

    public void setCmd_type(char cmd_type) {
        this.cmd_type = cmd_type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public char getChannle() {
        return channle;
    }

    public void setChannle(char channle) {
        this.channle = channle;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public byte[]  getBytes(Object obj) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(12);

        byteBuffer.put((byte) this.start_code.toCharArray()[0]);
        byteBuffer.put((byte) this.start_code.toCharArray()[1]);
        byteBuffer.put((byte) this.start_code.toCharArray()[2]);
        byteBuffer.put((byte) this.cmd_type);
        byteBuffer.putInt(this.length);
        byteBuffer.put((byte) this.type);
        byteBuffer.put((byte) this.channle);
        byteBuffer.put((byte) this.res.toCharArray()[0]);
        byteBuffer.put((byte) this.res.toCharArray()[1]);


       return  byteBuffer.array();

    }
}
