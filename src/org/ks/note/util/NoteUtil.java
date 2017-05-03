package org.ks.note.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;


public class NoteUtil {
	/**
	 * 用户密码的加密处理
	 * 
	 * md5加密的特点：
	 * 1.不可逆
	 * 2.不管明文的长度是否一致，加密后的密文的长度都一致
	 * @param msg 明文
	 * @return 密文
	 * @throws NoSuchAlgorithmException 
	 */
	public static String md5(String msg){
		//返回的对象是具有md5算法的对象
		try {
			MessageDigest md=
					MessageDigest.getInstance("MD5");
			byte[] input=msg.getBytes();
			//对传入的字节数组进行加密并返回
			byte[] output=md.digest(input);
			//md5处理后的结果转换为字符串会乱码，
			//用base64算法解决,需要引入相关的jar包
			String str=Base64.encodeBase64String(output);
			return str;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("密码加密异常");
			return "";
		}
	}
	/*
	 * 生成ID的方法
	 * 不重复,不连续
	 */
	public static String createId(){
		UUID uuid=UUID.randomUUID();
		return uuid.toString();
	}
	public static void main(String[] args) {
//		System.out.println(md5("1234"));//gdyb21LQTcIANtvYMT7QVQ==
		System.out.println(createId());
	}
}
