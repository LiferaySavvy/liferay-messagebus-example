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

package com.liferaysavvy.messagebus.event.listener;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;

public class DestinationRegisteredSynchronousMessageListener implements MessageListener {
	@Override
	public void receive(Message message) {

		try {
			_log.info("Message::"+message);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	

	private static final Log _log = LogFactoryUtil.getLog(
		DestinationRegisteredSynchronousMessageListener.class);

}