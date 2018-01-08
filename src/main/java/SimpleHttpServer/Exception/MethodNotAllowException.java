package SimpleHttpServer.Exception;

/**
 * Created by zhangguijiang on 2017/11/23.
 */
public class MethodNotAllowException extends HttpException {

    public MethodNotAllowException() {
        super(405, "Method Not Allow");
    }
}
