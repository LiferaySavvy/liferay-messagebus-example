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
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferaysavvy.messagebus.constants.LiferayMessageBusPortletKeys;
import com.liferaysavvy.messagebus.event.listener.DestinationRegisteredParallelMessageListener;
import com.liferaysavvy.messagebus.event.listener.DestinationRegisteredSerialMessageListener;
import com.liferaysavvy.messagebus.event.listener.DestinationRegisteredSynchronousMessageListener;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;


@Component (
	    immediate = true,
	    service = DestinationListenerRegistrator.class
	)
public class DestinationListenerRegistrator{
	@Activate
    protected void activate() {
		_messageListenerParallel = new DestinationRegisteredParallelMessageListener();
		_destinationParellel.register(_messageListenerParallel);
        _log.info("Message Listener Registered.."+_messageListenerParallel);
        
        _messageListenerSerail = new DestinationRegisteredSerialMessageListener();
        _destinationSerial.register(_messageListenerSerail);
        _log.info("Message Listener Registered.."+_messageListenerSerail);
        
        _messageListenerSynchronius = new DestinationRegisteredSynchronousMessageListener();
        _destinationSynchronius.register(_messageListenerSynchronius);
        _log.info("Message Listener Registered.."+_messageListenerSynchronius);
    }

   
    @Deactivate
    protected void deactivate() {
    	_destinationParellel.unregister(_messageListenerParallel);
        _log.info("Message Listener Unregistered.."+_messageListenerParallel);
        
        _destinationSerial.unregister(_messageListenerSerail);
        _log.info("Message Listener Unregistered.."+_messageListenerSerail);
        
        _destinationSynchronius.unregister(_messageListenerSynchronius);
        _log.info("Message Listener Unregistered.."+_messageListenerSynchronius);
        
    }
    

    @Reference(target = "(destination.name="+LiferayMessageBusPortletKeys.DESTINATION_PARALLEL+")")
    private Destination _destinationParellel;
    
    @Reference(target = "(destination.name="+LiferayMessageBusPortletKeys.DESTINATION_SERIAL+")")
    private Destination _destinationSerial;
    
    @Reference(target = "(destination.name="+LiferayMessageBusPortletKeys.DESTINATION_SYNCHRONOUS+")")
    private Destination _destinationSynchronius;
    

    private MessageListener _messageListenerParallel;
    private MessageListener _messageListenerSerail;
    private MessageListener _messageListenerSynchronius;
	private static final Log _log = LogFactoryUtil.getLog(DestinationListenerRegistrator.class);
}