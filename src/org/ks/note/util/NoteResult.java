package org.ks.note.util;

/**
 * 作为服务器返回数据格式的约定的对象
 * @author Cappuccino
 *
 */
public class NoteResult {
	private Integer status;//返回的状态
	private String msg;//返回的信息
	private Object data;//返回的数据
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
