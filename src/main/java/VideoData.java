/**
 * @auther JianLinWei
 * @date 2020-05-19 9:53
 */
public class VideoData {

    private  byte[]  h265;

    public synchronized byte[] getH265() {
        return h265;
    }

    public synchronized void setH265(byte[] h265) {
        this.h265 = h265;
    }
}
