/**
 * 
 */
package com.shanlin.demo.dto;

/**
 * @author shanlin
 *
 */
public class CommonResult {
	private boolean isSuccess;
	private String returnMsg;
	/**
	 * @return the isSuccess
	 */
	public boolean getIsSuccess() {
		return isSuccess;
	}
	/**
	 * @param isSuccess the isSuccess to set
	 */
	public void setIsSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	/**
	 * @return the returnMsg
	 */
	public String getReturnMsg() {
		return returnMsg;
	}
	/**
	 * @param returnMsg the returnMsg to set
	 */
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
}
