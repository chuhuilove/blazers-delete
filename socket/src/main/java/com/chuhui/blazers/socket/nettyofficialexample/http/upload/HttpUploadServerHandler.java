package com.chuhui.blazers.socket.nettyofficialexample.http.upload;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.DiskAttribute;
import io.netty.handler.codec.http.multipart.DiskFileUpload;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.util.CharsetUtil;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_0;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * HttpUploadServerHandler
 *
 * @author: cyzi
 * @Date: 2019/9/5 0005
 * @Description:TODO
 */
public class HttpUploadServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    private static final Logger logger = Logger.getLogger(HttpUploadServerHandler.class.getName());


    private HttpPostRequestDecoder decoder;
    private HttpRequest request;

    private final StringBuilder responseContent = new StringBuilder();


    static {
        DiskFileUpload.deleteOnExitTemporaryFile = true;
        DiskFileUpload.baseDirectory = null;

        DiskAttribute.deleteOnExitTemporaryFile = true;
        DiskAttribute.baseDirectory = null;

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        if (decoder != null) {
            decoder.cleanFiles();
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        if (msg instanceof HttpRequest) {
            HttpRequest req = (HttpRequest) msg;
            this.request = req;
            URI uri = new URI(req.uri());

            // 路由分发到form
            if (uri.getPath().startsWith("/form")) {
                writeMenu(ctx);
                return;
            }
        }
    }

    private void writeMenu(ChannelHandlerContext ctx) {
        responseContent.setLength(0);

        // create Pseudo Menu
        responseContent.append("<html>");
        responseContent.append("<head>");
        responseContent.append("<title>Netty Test Form</title>\r\n");
        responseContent.append("</head>\r\n");
        responseContent.append("<body bgcolor=white><style>td{font-size: 12pt;}</style>");

        responseContent.append("<table border=\"0\">");
        responseContent.append("<tr>");
        responseContent.append("<td>");
        responseContent.append("<h1>Netty Test Form</h1>");
        responseContent.append("Choose one FORM");
        responseContent.append("</td>");
        responseContent.append("</tr>");
        responseContent.append("</table>\r\n");

        // GET
        responseContent.append("<CENTER>GET FORM<HR WIDTH=\"75%\" NOSHADE color=\"blue\"></CENTER>");
        responseContent.append("<FORM ACTION=\"/formget\" METHOD=\"GET\">");
        responseContent.append("<input type=hidden name=getform value=\"GET\">");
        responseContent.append("<table border=\"0\">");
        responseContent.append("<tr><td>Fill with value: <br> <input type=text name=\"info\" size=10></td></tr>");
        responseContent.append("<tr><td>Fill with value: <br> <input type=text name=\"secondinfo\" size=20>");
        responseContent
                .append("<tr><td>Fill with value: <br> <textarea name=\"thirdinfo\" cols=40 rows=10></textarea>");
        responseContent.append("</td></tr>");
        responseContent.append("<tr><td><INPUT TYPE=\"submit\" NAME=\"Send\" VALUE=\"Send\"></INPUT></td>");
        responseContent.append("<td><INPUT TYPE=\"reset\" NAME=\"Clear\" VALUE=\"Clear\" ></INPUT></td></tr>");
        responseContent.append("</table></FORM>\r\n");
        responseContent.append("<CENTER><HR WIDTH=\"75%\" NOSHADE color=\"blue\"></CENTER>");

        // POST
        responseContent.append("<CENTER>POST FORM<HR WIDTH=\"75%\" NOSHADE color=\"blue\"></CENTER>");
        responseContent.append("<FORM ACTION=\"/formpost\" METHOD=\"POST\">");
        responseContent.append("<input type=hidden name=getform value=\"POST\">");
        responseContent.append("<table border=\"0\">");
        responseContent.append("<tr><td>Fill with value: <br> <input type=text name=\"info\" size=10></td></tr>");
        responseContent.append("<tr><td>Fill with value: <br> <input type=text name=\"secondinfo\" size=20>");
        responseContent
                .append("<tr><td>Fill with value: <br> <textarea name=\"thirdinfo\" cols=40 rows=10></textarea>");
        responseContent.append("<tr><td>Fill with file (only file name will be transmitted): <br> "
                + "<input type=file name=\"myfile\">");
        responseContent.append("</td></tr>");
        responseContent.append("<tr><td><INPUT TYPE=\"submit\" NAME=\"Send\" VALUE=\"Send\"></INPUT></td>");
        responseContent.append("<td><INPUT TYPE=\"reset\" NAME=\"Clear\" VALUE=\"Clear\" ></INPUT></td></tr>");
        responseContent.append("</table></FORM>\r\n");
        responseContent.append("<CENTER><HR WIDTH=\"75%\" NOSHADE color=\"blue\"></CENTER>");

        // POST with enctype="multipart/form-data"
        responseContent.append("<CENTER>POST MULTIPART FORM<HR WIDTH=\"75%\" NOSHADE color=\"blue\"></CENTER>");
        responseContent.append("<FORM ACTION=\"/formpostmultipart\" ENCTYPE=\"multipart/form-data\" METHOD=\"POST\">");
        responseContent.append("<input type=hidden name=getform value=\"POST\">");
        responseContent.append("<table border=\"0\">");
        responseContent.append("<tr><td>Fill with value: <br> <input type=text name=\"info\" size=10></td></tr>");
        responseContent.append("<tr><td>Fill with value: <br> <input type=text name=\"secondinfo\" size=20>");
        responseContent
                .append("<tr><td>Fill with value: <br> <textarea name=\"thirdinfo\" cols=40 rows=10></textarea>");
        responseContent.append("<tr><td>Fill with file: <br> <input type=file name=\"myfile\">");
        responseContent.append("</td></tr>");
        responseContent.append("<tr><td><INPUT TYPE=\"submit\" NAME=\"Send\" VALUE=\"Send\"></INPUT></td>");
        responseContent.append("<td><INPUT TYPE=\"reset\" NAME=\"Clear\" VALUE=\"Clear\" ></INPUT></td></tr>");
        responseContent.append("</table></FORM>\r\n");
        responseContent.append("<CENTER><HR WIDTH=\"75%\" NOSHADE color=\"blue\"></CENTER>");

        responseContent.append("</body>");
        responseContent.append("</html>");


        ByteBuf buf = Unpooled.copiedBuffer(responseContent.toString(), CharsetUtil.UTF_8);
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, buf);

        // 设置请求头

        response.headers().set(CONTENT_TYPE, "text/html;charset=UTF-8");
        response.headers().setInt(CONTENT_LENGTH, buf.readableBytes());

        boolean keepAlive = HttpUtil.isKeepAlive(request);

        if (!keepAlive) {
            response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
        } else if (response.protocolVersion().equals(HTTP_1_0)) {
            response.headers().set(CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }


        ChannelFuture future = ctx.writeAndFlush(response);

        if (!keepAlive) {
            // 必要时,在完成写入操作后关闭connection.
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.log(Level.WARNING, responseContent.toString(), cause);
        ctx.channel().close();

    }
}
