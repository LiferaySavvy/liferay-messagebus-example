/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferaysavvy.messagebus.portlet.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferaysavvy.messagebus.constants.LiferayMessageBusPortletKeys;
import com.liferaysavvy.messagebus.service.MessageSenderServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 * @author Wilson S. Man
 * @author Thiago Moreira
 * @author Juan Fernández
 * @author Zsolt Berentey
 * @author Levente Hudák
 */

@Component(
	immediate = true,
	property = {
			"javax.portlet.name=" + LiferayMessageBusPortletKeys.LIFERAYMESSAGEBUSEXAMPLE,
			"mvc.command.name=/send/message"
	},
	service = MVCActionCommand.class
)
public class SendMessageMVCActionCommand extends BaseMVCActionCommand {

	
	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {
		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);
		String destinationName = ParamUtil.getString(actionRequest, "destination");
		String message = ParamUtil.getString(actionRequest, "message");
		_log.info("destination"+destinationName);
		_log.info("message"+message);
		sendMessage(message, destinationName);
		actionResponse.setRenderParameter("mvcRenderCommandName", "/view/send_message");
	}
	protected void sendMessage(String message, String destinationName)
		throws Exception {
		messageSenderService.sendMessageToDestination(message, destinationName);
		//messageSenderService.sendSynchronousMessageToDestination(message, destinationName);
	}

	
	

	private static final Log _log = LogFactoryUtil.getLog(
		SendMessageMVCActionCommand.class);
	@Reference
    private MessageSenderServiceImpl messageSenderService;
}