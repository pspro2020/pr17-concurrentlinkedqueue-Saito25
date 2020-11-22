package garbage;

import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedDeque;


/**
 * IMPORTANTE
 * <p>
 * Entiendo perfectamente que el ejercicio pedía hacerlo con ConcurrentLinkedQueue, no obstante,
 * me ha parecido mejor hacerlo con ConcurrentLinkedDeque , por el modo de acceder a una cinta
 * transportadora (se inserta al inicio y se extrae al final)
 */
public class ConveyerBelt {
    private final ConcurrentLinkedDeque<Trash> conveyerBelt;

    public ConveyerBelt() {
        conveyerBelt = new ConcurrentLinkedDeque<>();
    }

    public void put(Trash trash) {
        conveyerBelt.addFirst(trash);
    }

    public Trash take() throws NoSuchElementException {
        return conveyerBelt.removeLast();
    }

    public boolean isNotEmpty() {
        return !conveyerBelt.isEmpty();
    }
}
