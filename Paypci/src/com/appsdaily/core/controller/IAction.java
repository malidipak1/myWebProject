/**
 * @author Dipak.Mali
 *
 */
package com.appsdaily.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAction {
	
	String SUCCESS = "success";
	String FAILED = "failed";
	String ERROR = "error";
	String LOGIN = "login";

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public void handleAction(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
