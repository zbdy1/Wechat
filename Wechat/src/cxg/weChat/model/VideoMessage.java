package cxg.weChat.model;

/**
* 类名: VideoMessage </br>
* 描述: 请求消息之视频消息 </br>
* 开发人员： souvc </br>
* 创建时间：  Sep 29, 2015 </br>
* 发布版本：V1.0  </br>
 */
public class VideoMessage  extends BaseMessage{

    // 媒体ID
    private String MediaId;
    // 语音格式
    private String ThumbMediaId;

    public String getMediaId() {
        return MediaId;
    }
    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
    public String getThumbMediaId() {
        return ThumbMediaId;
    }
    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }
    
    public static void main(String[] args) {
    	int count=5;
    	final Object prev=3;
    	final Object self=5;
    	String name="a";
    	Thread t=new Thread(new Runnable() {
    		Object i=1;
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(1000);
						System.out.println("sleep 1000");
						prev.notifyAll();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}catch (Exception e){
						e.printStackTrace();
					}
					
					
				}
			}
		});
    	t.start();
    	Thread t2=new Thread(new Runnable() {
    		Object i=1;
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(1000);
						System.out.println("sleep 1000");
						prev.notifyAll();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}catch (Exception e){
						e.printStackTrace();
					}
					
					
				}
			}
		});
    	t2.start();
	}
    
}