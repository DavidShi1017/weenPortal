package com.jingtong.platform.login.service;

import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.login.pojo.ValidateResult;


/**
 * 权限验证
 * 
 * @author xujiakun
 * 
 */
public interface ICAService {

	/**
	 * 成功
	 */
	public static final String RESULT_SUCCESS = "0";

	/**
	 * 失败
	 */
	public static final String RESULT_FAILED = "1";

	/**
	 * 系统级错误
	 */
	public static final String RESULT_ERROR = "2";

	public static final String INCORRECT_NULL = "User name or password can not be empty！";

	//public static final String INCORRECT_LOGINID = "The user does not exist in the system！";
	public static final String INCORRECT_LOGINID = "User name or password entered is incorrect！";
	public static final String INCORRECT_LOGIN = "User name or password entered is incorrect！";

	public static final String INCORRECT_TOKEN = "token验证失败！";

	/**
	 * 验证登录（不通过域验证）
	 * 
	 * @param passport
	 * @param password
	 * @return
	 */
	public ValidateResult validateUser(String passport, String password);

	/**
	 * 验证登录（通过域验证）
	 * 
	 * @param passport
	 * @param password
	 * @return
	 */
	public ValidateResult validateUserByLDAP(String passport, String password);
	
	/**
	 * 单点登录验证登录（不通过域验证）
	 * 
	 * @param passport
	 * @param password
	 * @return
	 */
	public ValidateResult singleValidateUser(String passport, String password);
	
	/**
	 * 单点登录验证登录（通过域验证）
	 * 
	 * @param passport
	 * @param password
	 * @return
	 */
	public ValidateResult singleValidateUserByLDAP(String passport, String password);
	
	

	/**
	 * 验证token
	 * 
	 * @param token
	 * @return
	 */
	public ValidateResult validateToken(String token);

	/**
	 * 生成免登token
	 * 
	 * @param object
	 * @return
	 */
	public String generateToken(Object object);
	
	/**
	 * 单点登录时查询用户
	 * @return
	 */
	public AllUsers queryAllUser(AllUsers user);

}
