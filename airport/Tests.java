package aed.airport;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import es.upm.aedlib.positionlist.*;


public class Tests {

    //Creamos tester de la Propiedad 1.
    @Test
    public void testPropiedad1() {
    IncomingFlightsRegistry airport = new IncomingFlightsRegistry();
    airport.arrivesAt("avion",(long) 1050);
    airport.arrivesAt("avion",(long) 1200);
    assertEquals(1900, airport.arrivalTime("avion"));
    }


    @Test
    public void testPropiedad2() {
    IncomingFlightsRegistry airport = new IncomingFlightsRegistry();
    airport.arrivesAt("avion1",(long) 20);
    airport.arrivesAt("avion2",(long) 10);
    PositionList<FlightArrival> result = new NodePositionList<FlightArrival>();
    result.addLast(new FlightArrival("avion2",10));
    result.addLast(new FlightArrival("avion1",20));
    assertEquals(result, airport.arriving((long) 0));
    }


}
