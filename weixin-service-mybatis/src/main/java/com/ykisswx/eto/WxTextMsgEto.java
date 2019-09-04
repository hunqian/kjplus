package com.ykisswx.eto;

/**
 * TWxAccount entity. @author MyEclipse Persistence Tools
 */

public class WxTextMsgEto implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6097041310940928917L;
	// 消息文本
		private int id;
		private int accid;
		private int userid;
		private String textMsg;
		private int createTime;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getAccid() {
			return accid;
		}
		public void setAccid(int accid) {
			this.accid = accid;
		}
		public int getUserid() {
			return userid;
		}
		public void setUserid(int userid) {
			this.userid = userid;
		}
		public String getTextMsg() {
			return textMsg;
		}
		public void setTextMsg(String textMsg) {
			this.textMsg = textMsg;
		}
		public int getCreateTime() {
			return createTime;
		}
		public void setCreateTime(int createTime) {
			this.createTime = createTime;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		
		
}