package cxg.weChat.resp;

/**
* ����: VoiceMessage </br>
* ����: ������Ϣ</br>
* ������Ա�� souvc </br>
* ����ʱ�䣺  2015-9-30 </br>
* �����汾��V1.0  </br>
 */
public class VoiceMessage extends BaseMessage {
    // ����
    private Voice Voice;

    public Voice getVoice() {
        return Voice;
    }

    public void setVoice(Voice voice) {
        Voice = voice;
    }
}
