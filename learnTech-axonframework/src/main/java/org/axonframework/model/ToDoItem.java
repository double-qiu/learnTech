package org.axonframework.model;

import org.axonframework.command.CreateToDoItemCommand;
import org.axonframework.command.MarkCompletedCommand;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.event.ToDoItemCompletedEvent;
import org.axonframework.event.ToDoItemCreatedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

@SuppressWarnings({ "rawtypes", "serial" })
public class ToDoItem extends AbstractAnnotatedAggregateRoot {
	@AggregateIdentifier
    private String id;
 
    public ToDoItem() {
    }
 
    @CommandHandler
    public ToDoItem(CreateToDoItemCommand command) {
        apply(new ToDoItemCreatedEvent(command.getTodoId(), command.getDescription()));
    }
 
    @EventHandler
    public void on(ToDoItemCreatedEvent event) {
        this.id = event.getTodoId();
    }
 
    @CommandHandler
    public void markCompleted(MarkCompletedCommand command) {
        apply(new ToDoItemCompletedEvent(id));
    }
}
