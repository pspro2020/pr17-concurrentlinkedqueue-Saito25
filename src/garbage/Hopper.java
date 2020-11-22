package garbage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Hopper implements Runnable {

    private final String hopperId;
    private final ConveyerBelt conveyerBelt;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private List<Trash> trashInHopper;

    public Hopper(String hopperId, ConveyerBelt conveyerBelt, int numberOfTrashInHopper) {
        this.hopperId = hopperId;
        this.conveyerBelt = conveyerBelt;
        trashInHopper = TrashFactory.generateTrash(hopperId, numberOfTrashInHopper);
    }

    @Override
    public void run() {
        Trash trash;
        while (!trashInHopper.isEmpty()) {
            try {
                work();
                trash = trashInHopper.remove(0);
                conveyerBelt.put(trash);
                System.out.printf("%s -> La tolva %s ha puesto el paquete de plástico con id %s en la cinta\n",
                        LocalTime.now().format(dateTimeFormatter),
                        hopperId,
                        trash.getId());
            } catch (InterruptedException e) {
                System.out.printf("%s -> La tolva %s fue interrumpida antes de terminar su trabajo\n",
                        LocalTime.now().format(dateTimeFormatter),
                        hopperId);
                return;
            }
        }
        System.out.printf("%s -> La tolva %s ha finalizado su trabajo y se apagará por hoy...\n",
                LocalTime.now().format(dateTimeFormatter),
                hopperId);
    }

    private void work() throws InterruptedException {
        System.out.printf("%s -> La tolva %s está comprimiendo el plástico y luego lo pondrá en la cinta\n",
                LocalTime.now().format(dateTimeFormatter),
                hopperId);
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(1, 3));
    }
}
