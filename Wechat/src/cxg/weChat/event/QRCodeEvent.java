package cxg.weChat.event;

/**
* ����: QRCodeEvent </br>
* ����: ɨ���������ά���¼� </br>
* ������Ա�� souvc </br>
* ����ʱ�䣺  2015-9-30 </br>
* �����汾��V1.0  </br>
 */
public class QRCodeEvent extends BaseEvent {
    // �¼�KEYֵ
    private String EventKey;
    // ���ڻ�ȡ��ά��ͼƬ
    private String Ticket;

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String ticket) {
        Ticket = ticket;
    }
}
