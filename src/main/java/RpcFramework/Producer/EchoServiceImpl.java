package RpcFramework.Producer;

/**
 * Created by zhangguijiang on 2018/3/28.
 */
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String ping) {
        return ping != null ? " --> I am ok." : " I am ok.";
    }
}
