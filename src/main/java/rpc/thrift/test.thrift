namespace java rpc.thrift

struct UserInfo {
1: i32 userId,
2: string userName,
3: string userPwd,
4: string sex,
5: string age,
}

service UserInfoService {
UserInfo getUserInfoById(1:i32 userId)
string getUserNameById(1:i32 userId)
i32 getUserCount()
bool checkUserById(1:i32 userId)
}