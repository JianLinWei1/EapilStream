import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * @auther JianLinWei
 * @date 2020-04-30 11:07
 */
public class HKVHeader  implements Serializable {
    private  String start_code; //起始值“AVS"
    private   char cmd_type ; //请求类型  0 请求 1 应答
    private   int length ;  //数据长度

    public HKVHeader(byte[] bytes){
        byte[]  bytes1 = new byte[3];
        ByteBuffer byteBuffer  = ByteBuffer.wrap(bytes);
        byteBuffer.get(bytes1, 0, 3);
        this.start_code  =  new String(bytes1);

        this.cmd_type = (char) byteBuffer.get(3);
        this.length = byteBuffer.getInt(4);
    }

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
}
