package cxg;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.sword.lang.HttpUtils;
import org.sword.wechat4j.message.TemplateMsg;
import org.sword.wechat4j.message.template.TemplateMsgBody;
import org.sword.wechat4j.message.template.TemplateMsgData;
import org.sword.wechat4j.token.TokenProxy;
import org.sword.wechat4j.user.UserManager;

import com.alibaba.fastjson.JSONObject;

public class Wechat {
/**
 *刷新关注列表
 * 有点乱，不应该和微信掺和在一起
	 * 
	 * @Title:  
	 * @param:
	 * @return:void 
	 * @author: nani	
	 * @createtime:2015-5-20下午3:23:39
 */
	public void getWechatId() {
	//	String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=";
		String token = TokenProxy.accessToken();
		System.out.println(token);
		/*UserManager userManager = new UserManager();
		List<Object> userList = userManager.subscriberList();
		for (Object object : userList) {
			String openId = object.toString();
				String userInfo = HttpUtils.get(url + token + "&&openid="
						+ openId + "&lang=zh_CN");
				try {
					userInfo = new String(userInfo.getBytes("iso-8859-1"),
							"utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (!"".equals(userInfo)) {
					JSONObject user = JSONObject.parseObject(userInfo);
					if (user.containsKey("errcode")){
						System.out.print("该账户信息无法获得:"
								+ user.getString("errmsg") + "--openid:"
								+ openId);
					}else{
						System.out.println(openId+"<===>"+user.getString("nickname"));
					}
				
				}
			}*/
	}

	public boolean send(String templateId, String wechatId, String title,
			String object, String info, String remark) {
		if (templateId == null || wechatId == null || title == null
				|| object == null || info == null || remark == null) {
			return false;
		}
		TemplateMsgBody modBody = new TemplateMsgBody();
		TemplateMsg templateMsg = new TemplateMsg();
		modBody.setTemplateId(templateId);
		modBody.setTouser(wechatId);
		List<TemplateMsgData> lData = new ArrayList<TemplateMsgData>();
		TemplateMsgData data = new TemplateMsgData();
		data.setName("first");
		data.setValue(title);
		data.setColor("#FF0000");
		TemplateMsgData data1 = new TemplateMsgData();
		data1.setName("keyword1");
		data1.setValue(object);
		// data1.setColor("#FF0000");
		TemplateMsgData data2 = new TemplateMsgData();
		data2.setName("keyword2");
		data2.setValue(info);
		// data2.setColor("#FF0000");
		TemplateMsgData data3 = new TemplateMsgData();
		data3.setName("remark");
		data3.setValue(remark);
		// data3.setColor("#FF0000");
		lData.add(data);
		lData.add(data1);
		lData.add(data2);
		lData.add(data3);
		modBody.setData(lData);
		String result = templateMsg.send(modBody);
		// System.out.println(result);
		// Gson gson = new Gson();
		// ResultBean resultBean=gson.fromJson(result, ResultBean.class);
		return true;
		// int flag = resultBean.getErrcode();
		// return flag==0;
	}

	public static void main(String[] args) {
		Wechat wechat=new Wechat();
		// String token = TokenProxy.accessToken(); 
		 wechat.getWechatId();
		// System.out.println(token);
		

	}

}
