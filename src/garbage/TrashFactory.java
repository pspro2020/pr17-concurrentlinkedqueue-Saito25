package garbage;

import java.util.ArrayList;
import java.util.List;

public class TrashFactory {

    private TrashFactory(){};

    public static List<Trash> generateTrash(String hopperNumber, int numberOfTrash) {
        List<Trash> listOfTrash = new ArrayList<>();

        for (int i = 1; i <= numberOfTrash; i++) {
            listOfTrash.add(new Trash(hopperNumber, Integer.toString(i)));
        }

        return listOfTrash;
    }
}
