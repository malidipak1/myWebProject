package com.appsdaily.core.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appsdaily.util.LogUtils;
import com.onward.payment.controller.ActionMapper;
import com.onward.payment.helpers.Helper;

/**
 * @author Dipak.Mali
 *
 */
public class ActionHandlerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7491040387967605570L;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 	throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String actionName = request.getParameter("action");
		String url = request.getRequestURI() + "?" + Helper.nullToBlank(request.getQueryString());
	
		LogUtils.info("Running action: '" + actionName + "', url: " + url);
		
		try {
			/**
			 * get Action Handler
			 */
			ActionHandler actionHandlerObj = ActionMapper.getActionHandler(actionName);
			if(actionHandlerObj != null) {
				
				actionHandlerObj.handleAction(request, response);
			} else {
				LogUtils.info("Unknown action: '" + actionName + "'");
        		//throw new IllegalArgumentException("Unknown action: '" + actionName + "'");
        	}
			
		} catch (Exception e) {
			LogUtils.logError("Error while running command\t" + actionName + "\turl\t" + url, e);
			throw new RuntimeException(e);
		}
	}
	
}
