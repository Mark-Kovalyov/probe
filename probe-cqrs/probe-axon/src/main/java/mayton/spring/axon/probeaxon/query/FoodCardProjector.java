package mayton.spring.axon.probeaxon.query;

import mayton.spring.axon.probeaxon.command.FoodCard;
import mayton.spring.axon.probeaxon.command.FoodCardCreateEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class FoodCardProjector {

    private final FoodCardViewRepository foodCardViewRepository;

    public FoodCardProjector(FoodCardViewRepository foodCardViewRepository) {
        this.foodCardViewRepository = foodCardViewRepository;
    }

    @EventHandler
    public void on(FoodCardCreateEvent event) {
        FoodCardView foodCardView = new FoodCardView();
    }
}
