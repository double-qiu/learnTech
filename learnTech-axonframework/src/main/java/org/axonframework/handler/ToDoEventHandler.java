package org.axonframework.handler;

import org.axonframework.event.ToDoItemCompletedEvent;
import org.axonframework.event.ToDoItemCreatedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToDoEventHandler {
	
	  private Logger logger = LoggerFactory.getLogger(ToDoEventHandler.class);
	 
	    @EventHandler
	    public void handle(ToDoItemCreatedEvent event) {
	        logger.info("We've got something to do: " + event.getDescription() + " (" + event.getTodoId() + ")");
	    }
	 
	    @EventHandler
	    public void handle(ToDoItemCompletedEvent event) {
	        logger.info("We've completed a task: " + event.getTodoId());
	    }
}
