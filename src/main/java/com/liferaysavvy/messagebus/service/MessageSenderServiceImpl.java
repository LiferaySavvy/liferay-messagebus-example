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

package com.liferaysavvy.messagebus.service;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationStatistics;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageBusException;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


@Component(
	    immediate = true,
	    service = MessageSenderServiceImpl.class
	)
public class MessageSenderServiceImpl{
	public void sendMessageToDestination(String message,String destinationName) {

        Message messageobj = new Message();
        messageobj.put("message", message);
        _messageBus.sendMessage(destinationName, messageobj);
        //MessageBusUtil.sendMessage(destinationName, message);

    }
	
	public DestinationStatistics getDestinationStatistics(String destinationName) {
		Destination destination = _messageBus.getDestination(destinationName);
		Set<MessageListener> listeners = destination.getMessageListeners();
		for (MessageListener curListener : listeners) {
	     }
		DestinationStatistics dstatiStrics = destination.getDestinationStatistics();
		return dstatiStrics;

    }
	
	public List<String> getListeners(String destinationName) {
		Destination destination = _messageBus.getDestination(destinationName);
		Set<MessageListener> listeners = destination.getMessageListeners();
		List<String> listenersList = new ArrayList<String>();
		for (MessageListener curListener : listeners) {
			listenersList.add(curListener.toString());
	     }
		return listenersList;

    }
	public void sendSynchronousMessageToDestination(String message,String destinationName) {

        Message messageobj = new Message();
        messageobj.put("message", "abcdef");
        try {
			MessageBusUtil.sendSynchronousMessage(destinationName, messageobj);
			//MessageBusUtil.sendSynchronousMessage(destinationName, message, timeout)
		} catch (MessageBusException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

    }
	@Reference
    private MessageBus _messageBus;
	private static final Log _log = LogFactoryUtil.getLog(MessageSenderServiceImpl.class);
}
