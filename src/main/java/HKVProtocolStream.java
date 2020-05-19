import java.io.Serializable;

/**
 * @auther JianLinWei
 * @date 2020-04-30 11:10
 */
public class HKVProtocolStream  implements Serializable {
    /**
     * #define STREAM_MAJ		    (4)     //主码流
     * #define STREAM_SUB 		    (5)     //副码流
     * #define STREAM_FACE_IPC  (6)     //人脸摄像头
     * #define STREAM_PLAYBCAK  (7)     //录像文件回放
     */
    private  char  type ; //码流类型
    private  char channle ; //通道
    private  String res;//预留

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
}
