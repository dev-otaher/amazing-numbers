
enum DangerLevel {
    HIGH(3),
    MEDIUM(2),
    LOW(1);

    int level;

    DangerLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }
}
public class Main {
    public static void main(String[] args) {
        System.out.println(DangerLevel.HIGH.toString());
    }
}
