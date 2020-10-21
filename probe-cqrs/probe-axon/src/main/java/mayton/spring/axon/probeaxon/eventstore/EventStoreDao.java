package mayton.spring.axon.probeaxon.eventstore;


import akka.actor.ActorRef;
import akka.actor.Props;
import akka.http.impl.util.JavaMapping;
import eventstore.akka.Settings;
import eventstore.akka.tcp.ConnectionActor;
import eventstore.core.*;
import eventstore.j.*;


import java.util.UUID;

public class EventStoreDao {

    // The same as:
    // curl -i -d "@event.json" "http://127.0.0.1:2113/streams/newstream" -H "Content-Type:application/vnd.eventstore.events+json" -u "admin:changeit"
    public void write(String event) {
        /*final Settings settings = new SettingsBuilder()
                .address(new JavaMapping.InetSocketAddress("127.0.0.1", 1113))
                .defaultCredentials("admin", "changeit")
                .build();
        final ActorRef connection = system.actorOf(ConnectionActor.getProps(settings));
        final ActorRef writeResult = system.actorOf(Props.create(WriteResult.class));

        final EventData event = new EventDataBuilder("event-type")
                .eventId(UUID.randomUUID())
                .jsonData("{\"a\": \"1\"}")
                .build();

        final WriteEvents writeEvents = new WriteEventsBuilder("newstream")
                .addEvent(event)
                .expectAnyVersion()
                .build();

        connection.tell(writeEvents, writeResult);*/
    }

}
