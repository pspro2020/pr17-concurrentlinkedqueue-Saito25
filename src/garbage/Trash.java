package garbage;

public class Trash {
    private final String id;

    public Trash(String Hopper, String number) {
        this.id = Hopper + number;
    }

    public String getId() {
        return id;
    }
}
