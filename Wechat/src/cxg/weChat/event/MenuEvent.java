package cxg.weChat.event;


/**
* ����: MenuEvent </br>
* ����: �Զ���˵��¼� </br>
* ������Ա�� liuhf </br>
* ����ʱ�䣺  2015-9-30 </br>
* �����汾��V1.0  </br>
 */
public class MenuEvent extends BaseEvent {
    // �¼�KEYֵ�����Զ���˵��ӿ���KEYֵ��Ӧ
    private String EventKey;

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
}