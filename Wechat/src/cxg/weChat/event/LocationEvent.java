package cxg.weChat.event;

/**
* ����: LocationEvent </br>
* ����: �ϱ�����λ���¼� </br>
* ������Ա�� liuhf </br>
* ����ʱ�䣺  2015-9-30 </br>
* �����汾��V1.0  </br>
 */
public class LocationEvent extends BaseEvent {
    // ����λ��γ��
    private String Latitude;
    // ����λ�þ���
    private String Longitude;
    // ����λ�þ���
    private String Precision;

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getPrecision() {
        return Precision;
    }

    public void setPrecision(String precision) {
        Precision = precision;
    }
}