package cxg.weChat.resp;

/**
 * ����: MusicMessage </br> 
 * ����: ������Ϣ </br> 
 * ������Ա�� souvc </br>
 * ����ʱ�䣺 2015-9-30 </br>
 * �����汾��V1.0 </br>
 */
public class MusicMessage extends BaseMessage {
	// ����
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}