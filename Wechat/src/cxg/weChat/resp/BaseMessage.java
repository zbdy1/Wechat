package cxg.weChat.resp;

/**
* ����: BaseMessage </br>
* ����: ��Ϣ���ࣨ�����ʺ� -> ��ͨ�û��� </br>
* ������Ա�� souvc </br>
* ����ʱ�䣺  2015-9-30 </br>
* �����汾��V1.0  </br>
 */
public class BaseMessage {
    // ���շ��ʺţ��յ���OpenID��
    private String ToUserName;
    // ������΢�ź�
    private String FromUserName;
    // ��Ϣ����ʱ�� �����ͣ�
    private long CreateTime;
    // ��Ϣ����
    private String MsgType;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}
