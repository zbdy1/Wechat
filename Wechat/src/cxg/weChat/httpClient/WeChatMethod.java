package cxg.weChat.httpClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSON;
import cxg.weChat.model.TokenBean;
import cxg.weChat.model.UserBean;
import cxg.weChat.model.UserListBean;


public class WeChatMethod {
	public static String appid="wxa2d4ef48164d8096";
	public static String secret="61bcf2f07d26c5d4574649f73a86ebd3";
	public static String modelId="GARua10e8nc8ROuSnMZUmNzJl774En73YdE1h0IREfw";
	
	public static String user_openid="ojCyawizIY_zAO6vYnBbLIfmXaLo";//陈晓光
	
	/**
	 * 根据应用ID、应用密钥生成临时TOKEN（两小时内有效，如再生成新的，旧的自动失效）
	 */
	public TokenBean getTokenJson(String appid,String secret){
		String getTokenUrl="https://api.weixin.qq.com/cgi-bin/token";
		List<NameValuePair> list=new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("grant_type","client_credential"));
		list.add(new BasicNameValuePair("appid",appid));
		list.add(new BasicNameValuePair("secret",secret));
		String json=WeChatQuest.doGet(getTokenUrl,list);
		TokenBean result=JSON.parseObject(json, TokenBean.class);
		return result;
	}
	/**
	 * 获取关注者列表
	 * 根据TOKEN和上次最后一个用户获取关注者列表
	 */
	public UserListBean getUserOpenidList(String access_token,String next_openid){
		String getTokenUrl="https://api.weixin.qq.com/cgi-bin/user/get";
		List<NameValuePair> list=new ArrayList<NameValuePair>();
		if(access_token==null){
			access_token=getTokenJson(appid,secret).getAccess_token();
		}
		list.add(new BasicNameValuePair("access_token",access_token));
		if(next_openid!=null){
			list.add(new BasicNameValuePair("next_openid",next_openid));
		}
		String json=WeChatQuest.doGet(getTokenUrl,list);
		UserListBean result=JSON.parseObject(json, UserListBean.class);
		return result;
	}
	/**
	 * 获取关注者详细信息
	 */
	public UserBean getUserDetails(String access_token,String openid){
		String getTokenUrl="https://api.weixin.qq.com/cgi-bin/user/info";
		List<NameValuePair> list=new ArrayList<NameValuePair>();
		if(access_token==null){
			access_token=getTokenJson(appid,secret).getAccess_token();
		}
		list.add(new BasicNameValuePair("access_token",access_token));
		list.add(new BasicNameValuePair("openid",openid));
		list.add(new BasicNameValuePair("lang","zh_CN"));
		
		String json=WeChatQuest.doGet(getTokenUrl,list);
		UserBean result=JSON.parseObject(json, UserBean.class);
		return result;
	}
	public void getUserListDetails(String access_token,List<String> openidL){
		if(access_token==null){
			access_token=getTokenJson(appid,secret).getAccess_token();
		}
		String url="https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token="+access_token;
		//{     "user_list": [         {             "openid": "otvxTs4dckWG7imySrJd6jSi0CWE",              "lang": "zh-CN"         },          {             "openid": "otvxTs_JZ6SEiP0imdhpi50fuSZg",              "lang": "zh-CN"         }     ] }
		List<HashMap> openidList=new ArrayList<HashMap>();
		for(String openid:openidL){
			HashMap<String,Object> temp=new HashMap<String,Object>();
			temp.put("openid", openid);
			temp.put("lang", "zh_CN");
			openidList.add(temp);
		}
		HashMap<String,Object> param=new HashMap<String,Object>();
		param.put("user_list", openidList);
		String json=JSON.toJSONString(param);
		String result=WeChatQuest.doPost(url, json);
		System.out.println(result);
	}
	/**
	 * 发送模板消息
	 */
	public void sendMessage(String access_token,String openid,String template_id,String title,String alarmObj,String alarmMessage,String remark){
		if(access_token==null){
			access_token=getTokenJson(appid,secret).getAccess_token();
		}
		String url="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
		HashMap<String,Object> param=new HashMap<String,Object>();
		param.put("touser", openid);
		param.put("template_id", template_id);
		HashMap<String,Object> data=new HashMap<String,Object>();
		HashMap<String,Object> first=new HashMap<String,Object>();
		first.put("value", title);
		first.put("color", "#173177");
		data.put("first", first);
		HashMap<String,Object> keyword1=new HashMap<String,Object>();
		keyword1.put("value", alarmObj);
		keyword1.put("color", "#173177");
		data.put("keyword1", keyword1);
		HashMap<String,Object> keyword2=new HashMap<String,Object>();
		keyword2.put("value", alarmMessage);
		keyword2.put("color", "#173177");
		data.put("keyword2", keyword2);
		HashMap<String,Object> remarkH=new HashMap<String,Object>();
		remarkH.put("value", remark);
		remarkH.put("color", "#173177");
		data.put("remark", remarkH);
		param.put("data", data);
		String json=JSON.toJSONString(param);
		//System.out.println(json);
		String result=WeChatQuest.doPost(url, json);
		System.out.println(result);
	}
	//K9CvsZtehfZS6BCfBZAlk1_HYnkMTXE0m-HAU_yepWQ
	//o3HSmjp9As6u-UGJjmq9zVUhshNM
	public static void main(String[] args) {
		WeChatMethod wechartMethod=new WeChatMethod();
		String token=wechartMethod.getTokenJson(appid, secret).getAccess_token();
		UserListBean ulb=wechartMethod.getUserOpenidList(token, null);
		//List<String> openidList=ulb.getUserOpenIdList();
		//List<String> list=new ArrayList<String>();
		wechartMethod.sendMessage(token,user_openid,modelId,"中财大厦备用1【功率因数】报警","中财大厦备用1【功率因数】","报警级别:中;上限值:1;下限值:0.9;当前值:0.872.【节能监管平台】","能源平台");
		//wechartMethod.getUserListDetails(token,openidList.subList(0, 100));
		//UserListBean userlistBean=wechartMethod.getUserOpenidList(token, null);
		//List<String> openIdList=userlistBean.getUserOpenIdList();
		/*for(String openid:openIdList){
			System.out.println(wechartMethod.getUserDetails(token, openid));
		}
		*/
		//System.out.println(new WeChatMethod().getUserOpenidList(null, null).getData().get("openid").size());
	}
}
