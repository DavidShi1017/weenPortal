package com.jingtong.platform.message.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.message.pojo.Message;
import com.jingtong.platform.message.service.IMessageService;

public class MessageAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private IMessageService MessageService;
	private Message c;
	private List<Message> cList;
	public IMessageService getMessageService() {
		return MessageService;
	}
	public void setMessageService(IMessageService messageService) {
		MessageService = messageService;
	}
	public Message getC() {
		return c;
	}
	public void setC(Message c) {
		this.c = c;
	}
	public List<Message> getcList() {
		return cList;
	}
	public void setcList(List<Message> cList) {
		this.cList = cList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
