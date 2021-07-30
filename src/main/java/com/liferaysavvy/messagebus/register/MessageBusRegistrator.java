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

package com.liferaysavvy.messagebus.register;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferaysavvy.messagebus.constants.LiferayMessageBusPortletKeys;
import com.liferaysavvy.messagebus.event.listener.MessageBusRegisteredParallelMessageListener;
import com.liferaysavvy.messagebus.event.listener.MessageBusRegisteredSerialMessageListener;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;


@Component (
	    immediate = true,
	    service = MessageBusRegistrator.class
	)
public class MessageBusRegistrator{
	@Activate
    protected void activate() {
		_messageListenerParallel = new MessageBusRegisteredParallelMessageListener();
        _messageBus.registerMessageListener(LiferayMessageBusPortletKeys.DESTINATION_PARALLEL, _messageListenerParallel);
        _log.info("Message Listener Registered.."+_messageListenerParallel);
        
        _messageListenerSerail = new MessageBusRegisteredSerialMessageListener();
        _messageBus.registerMessageListener(LiferayMessageBusPortletKeys.DESTINATION_SERIAL, _messageListenerSerail);
        _log.info("Message Listener Registered.."+_messageListenerSerail);
        
        _messageListenerSynchronius = new MessageBusRegisteredParallelMessageListener();
        _messageBus.registerMessageListener(LiferayMessageBusPortletKeys.DESTINATION_SYNCHRONOUS, _messageListenerSynchronius);
        _log.info("Message Listener Registered.."+_messageListenerSynchronius);
    }

    @Deactivate
    protected void deactivate() {
        _messageBus.unregisterMessageListener(LiferayMessageBusPortletKeys.DESTINATION_PARALLEL,  _messageListenerParallel);
        _log.info("Message Listener Unregistered.."+_messageListenerParallel);
        
        _messageBus.unregisterMessageListener(LiferayMessageBusPortletKeys.DESTINATION_SERIAL,  _messageListenerSerail);
        _log.info("Message Listener Unregistered.."+_messageListenerSerail);
        
        _messageBus.unregisterMessageListener(LiferayMessageBusPortletKeys.DESTINATION_SYNCHRONOUS,  _messageListenerSynchronius);
        _log.info("Message Listener Unregistered.."+_messageListenerSynchronius);
    }

    @Reference
    private MessageBus _messageBus;
    private MessageListener _messageListenerParallel;
    private MessageListener _messageListenerSerail;
    private MessageListener _messageListenerSynchronius;
	private static final Log _log = LogFactoryUtil.getLog(MessageBusRegistrator.class);
}
