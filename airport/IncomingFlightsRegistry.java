package aed.airport;

import es.upm.aedlib.Entry;
import es.upm.aedlib.Pair;
import es.upm.aedlib.priorityqueue.*;
import es.upm.aedlib.map.*;
import es.upm.aedlib.positionlist.*;

/**
 * A registry which organizes information on airplane arrivals.
 */
public class IncomingFlightsRegistry {

	private Map<String, Entry<Long, FlightArrival>> registro;
	private PriorityQueue<Long, FlightArrival> torre;

	/**
	 * Constructs an class instance.
	 */
	public IncomingFlightsRegistry() {
		torre = new HeapPriorityQueue<Long, FlightArrival>();
		registro = new HashTableMap<String, Entry<Long, FlightArrival>>();
	}

	/**
	 * A flight is predicted to arrive at an arrival time (in seconds).
	 */
	public void arrivesAt(String flight, Long time) {
		FlightArrival llegada = new FlightArrival(flight, time);
		if (registro.containsKey(flight)) {
			torre.remove(registro.get(flight));
		}
		registro.put(flight, torre.enqueue(time, llegada));
	}

	/**
	 * A flight has been diverted, i.e., will not arrive at the airport.
	 */
	public void flightDiverted(String flight) {
		if(torre.isEmpty()||!(registro.containsKey(flight))) return;
		torre.remove(registro.remove(flight));
		
	}

	/**
	 * Returns the arrival time of the flight.
	 * 
	 * @return the arrival time for the flight, or null if the flight is not
	 *         predicted to arrive.
	 */
	public Long arrivalTime(String flight) {
		
		if(registro.get(flight)!=null) {
			if(registro.get(flight).getValue().getRight()!=null) 
			return registro.get(flight).getValue().getRight();
		}
		return null;
	}

	/**
	 * Returns a list of "soon" arriving flights, i.e., if any is predicted to
	 * arrive at the airport within nowTime+180 then adds the predicted earliest
	 * arriving flight to the list to return, and removes it from the registry.
	 * Moreover, also adds to the returned list, in order of arrival time, any other
	 * flights arriving withinfirstArrivalTime+120; these flights are also removed
	 * from the queue of incoming flights.
	 * 
	 * @return a list of soon arriving flights.
	 */
	public PositionList<FlightArrival> arriving(Long nowTime) {
		PositionList<FlightArrival> llegando = new NodePositionList<FlightArrival>();
		try {
			Entry<Long, FlightArrival> first = torre.first();
			if (first.getKey() <= nowTime + 180) {
				removeadd(first.getValue().getLeft(), llegando);
				boolean stop = false;
				while (!stop) {
					Entry<Long, FlightArrival> entrada = torre.first();
					if (entrada.getKey() <= first.getKey() + 120) {
						removeadd(entrada.getValue().getLeft(), llegando);
					} else
						stop = true;
				}
			}
		} catch (EmptyPriorityQueueException e) {
		}
		return llegando;
	}

	private void removeadd(String flight, PositionList<FlightArrival> lista) {
		registro.remove(torre.first().getValue().flight());
		lista.addLast(torre.dequeue().getValue());
	}
}