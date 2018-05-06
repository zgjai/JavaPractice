package rpc.thrift.UserInfo;

import com.google.common.collect.Maps;
import org.apache.thrift.TException;

import java.util.Map;

/**
 * @author zhangguijiang
 * @date 2018/4/24
 */
public class UserInfoServiceImpl implements UserInfoService.Iface {

    private static final Map<Integer, UserInfo> userMap = Maps.newHashMap();

    static {
        userMap.put(1, new UserInfo(1, "mao", "mao", "男", "2016"));

        userMap.put(2, new UserInfo(2, "ci", "ci", "女", "07"));

        userMap.put(3, new UserInfo(3, "yuan", "yuan", "男", "28"));
    }

    @Override
    public UserInfo getUserInfoById(int userId) throws TException {
        return userMap.get(userId);
    }

    @Override
    public String getUserNameById(int userId) throws TException {
        return userMap.get(userId).getUserName();
    }

    @Override
    public int getUserCount() throws TException {
        return userMap.size();
    }

    @Override
    public boolean checkUserById(int userId) throws TException {
        return userMap.containsKey(userId);
    }
}
