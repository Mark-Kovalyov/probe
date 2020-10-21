package mayton.spring.axon.probeaxon.command;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class FoodCard {



    public FoodCard() {
        // Required by Axon
    }

    @CommandHandler
    public FoodCard(CreateFoodCardCommand createFoodCardCommand) {
        AggregateLifecycle.apply(new FoodCardCreateEvent(UUID.randomUUID()));
    }

    @CommandHandler
    public FoodCard(DeselectFoodCardCommand deselectFoodCardCommand) {

    }

    @CommandHandler
    public FoodCard(SelectFoodCardCommand selectFoodCardCommand) {

    }

    @EventSourcingHandler
    public void handle(FoodCardCreateEvent event) {

    }
}
