package cxg.weChat.resp;

/**
* ����: TextMessage </br>
* ����: �ı���Ϣ </br>
* ������Ա�� souvc </br>
* ����ʱ�䣺  2015-9-30 </br>
* �����汾��V1.0  </br>
 */
public class TextMessage extends BaseMessage {
    // �ظ�����Ϣ����
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
