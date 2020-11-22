package garbage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class GarbageSorter implements Runnable {

    private final ConveyerBelt conveyerBelt;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public GarbageSorter(ConveyerBelt conveyerBelt) {
        this.conveyerBelt = conveyerBelt;
    }

    @Override
    public void run() {
        Trash trash;
        while(conveyerBelt.isNotEmpty()) {
            try {
                work();
                trash = conveyerBelt.take();
                System.out.printf("%s -> El clasificador ha clasificado el paquete de plástico con id %s\n",
                        LocalTime.now().format(dateTimeFormatter),
                        trash.getId());
            } catch (InterruptedException e) {
                System.out.printf("%s -> El clasificador fue interrumpido antes de terminar su trabajo\n",
                        LocalTime.now().format(dateTimeFormatter));
                return;
            }
        }
        System.out.printf("%s -> La cinta no tiene más elementos, el clasificador ha terminado su trabajo por hoy\n",
                LocalTime.now().format(dateTimeFormatter));
    }

    private void work() throws InterruptedException {
        System.out.printf("%s -> El clasificador se está preparando para coger elementos de la cinta\n",
                LocalTime.now().format(dateTimeFormatter));
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(1, 3));
    }
}
