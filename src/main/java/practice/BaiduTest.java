package practice;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;

/**
 * Created by zhangguijiang on 2017/12/25.
 */
public class BaiduTest {

    public static void main(String[] args) throws IOException {
        BaiduBidRequest.BidRequest.Builder builder = BaiduBidRequest.BidRequest.newBuilder();
        builder.setId("291757056025511754");
        builder.addImp(BaiduBidRequest.Imp
                .newBuilder()
                .setId("5a41b9afd9fcfd2d9db25511389fc19bbf9cce7b5413e1")
                .setTagid("1462853203791")
                .setBidfloor(2100)
                .setSecure(false)
                .setNative(
                        BaiduBidRequest.Native.newBuilder()
                                .setRequestNative(BaiduBidRequest.NativeRequest.newBuilder().setPlcmtcnt(1).build())
                                .build()).addTemplelist("BC0054").addTemplelist("BC0056").build());
        builder.setSite(BaiduBidRequest.Site.newBuilder().setName("afd.baidu.com").setPage("/afd/entry").build());
        builder.setApp(BaiduBidRequest.App.newBuilder().setBundle("").setVer("8.5").build());
        builder.setDevice(BaiduBidRequest.Device.newBuilder().setUa("1080_1920_android_8.5_480").setIp("180.99.7.90")
                .setModel("").setOs("android").setConnectiontype(BaiduBidRequest.ConnectionType.CONNECTION_UNKNOWN)
                .setDevicetype(BaiduBidRequest.DeviceType.APP).setIfa("73f74acbd9cee561429a0d499790e86d").setW(0)
                .setH(0).build());
        builder.setAt(BaiduBidRequest.AuctionType.SECOND_PRICE);
        builder.setTmax(200);
        BaiduBidRequest.BidRequest bidRequest = builder.build();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            bidRequest.writeTo(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] byteArray = output.toByteArray();

        HttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://adquerybackup.qunar.com/dsp/baidu_rtb");
        post.setEntity(new ByteArrayEntity(byteArray));
        post.addHeader(new BasicHeader("Content-Type", "application/octet-stream"));
        HttpResponse response = httpClient.execute(post);
    }
}
