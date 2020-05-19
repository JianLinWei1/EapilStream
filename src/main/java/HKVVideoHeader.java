import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * @auther JianLinWei
 * @date 2020-05-09 15:05
 */
public class HKVVideoHeader implements Serializable {
    private byte ftype;// 帧类型
    private byte ptype; //视频编码类型
    private byte fps;  //帧率
    private byte res;//预留
    private short width;//宽度
    private short height;//高度
    private int fsize; //帧大小
    private int fid; //帧id;
    private long timestamp; //时间戳ms
    private int errcode; // 错误码
    private int ext_length; //扩展长度



    public HKVVideoHeader(byte[] bytes) throws UnsupportedEncodingException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        this.ftype = byteBuffer.get(0);
        this.ptype = byteBuffer.get(1);
        this.fps = byteBuffer.get(2);
        this.res = byteBuffer.get(3);
        this.width = byteBuffer.getShort(4);
        this.height = byteBuffer.getShort(6);
        this.fsize = byteBuffer.getInt(8);
        this.fid = byteBuffer.getInt(12);
        this.timestamp = byteBuffer.getLong(16);
        this.errcode = byteBuffer.getInt(24);
        this.ext_length = byteBuffer.getInt(28);

    }

    public byte getFtype() {
        return ftype;
    }

    public void setFtype(byte ftype) {
        this.ftype = ftype;
    }

    public byte getPtype() {
        return ptype;
    }

    public void setPtype(byte ptype) {
        this.ptype = ptype;
    }

    public byte getFps() {
        return fps;
    }

    public void setFps(byte fps) {
        this.fps = fps;
    }

    public byte getRes() {
        return res;
    }

    public void setRes(byte res) {
        this.res = res;
    }

    public short getWidth() {
        return width;
    }

    public void setWidth(short width) {
        this.width = width;
    }

    public short getHeight() {
        return height;
    }

    public void setHeight(short height) {
        this.height = height;
    }

    public int getFsize() {
        return fsize;
    }

    public void setFsize(int fsize) {
        this.fsize = fsize;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public int getExt_length() {
        return ext_length;
    }

    public void setExt_length(int ext_length) {
        this.ext_length = ext_length;
    }
}
