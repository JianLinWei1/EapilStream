import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @auther JianLinWei
 * @date 2020-04-29 14:43
 */
public class Testt {


    public static void main(String[] args) throws InterruptedException, IllegalAccessException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap  = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                 .channel(NioSocketChannel.class)
                 .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
//                      ch.pipeline().addLast(new ObjectEncoder());
                        ch.pipeline().addLast(new ClientHandler());

                    }
                });


        ChannelFuture future = bootstrap.connect("192.168.1.191", 8090).sync();
        HKVRequest hkvRequest = new HKVRequest();
        hkvRequest.setStart_code("AVS");
        hkvRequest.setCmd_type('0');
        hkvRequest.setLength(4);

        hkvRequest.setType('4');
        hkvRequest.setChannle('0');
        hkvRequest.setRes("00");
//       byte[] bytes = VideoUtil.obj2Bytes(hkvRequest, 12);
       Object obj = hkvRequest;
         ByteBuf byteBuf = (ByteBuf)obj ;
          future.channel().writeAndFlush(byteBuf);
        future.channel().closeFuture().sync();
    }
}

class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

            System.err.println("client--收到响应：" );



    }





    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.err.println("客户端读取数据完毕" );
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("client 读取数据出现异常" +cause.getLocalizedMessage());
        ctx.close();
    }

}
