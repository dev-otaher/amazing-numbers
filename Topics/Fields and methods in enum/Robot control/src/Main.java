
enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Direction turnLeft() {
        switch (this) {
            case UP:
                return LEFT;
            case DOWN:
                return RIGHT;
            case LEFT:
                return DOWN;
            case RIGHT:
                return UP;
            default:
                throw new IllegalStateException();
        }
    }

    public Direction turnRight() {
        switch (this) {
            case UP:
                return RIGHT;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            case RIGHT:
                return DOWN;
            default:
                throw new IllegalStateException();
        }
    }

    public int dx() {
        return dx;
    }

    public int dy() {
        return dy;
    }
}

class Robot {
    private int x;
    private int y;
    private Direction direction;

    public Robot(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void turnLeft() {
        direction = direction.turnLeft();
    }

    public void turnRight() {
        direction = direction.turnRight();
    }

    public void stepForward() {
        x += direction.dx();
        y += direction.dy();
    }

    public Direction getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
//Don't change upper code

class Move {
    public static boolean isAtDestination(Robot robot, int toX, int toY) {
        return robot.getX() == toX && robot.getY() == toY;
    }

    public static void setDirection(Robot robot, Direction direction) {
        while (robot.getDirection() != direction) {
            robot.turnLeft();
        }
    }

    public static void moveRobot(Robot robot, int toX, int toY) {
        if (isAtDestination(robot, toX, toY)) {
            return;
        }

        if (robot.getX() > toX) {
            setDirection(robot, Direction.LEFT);
        } else {
            setDirection(robot, Direction.RIGHT);
        }

        while (robot.getX() != toX) {
            robot.stepForward();
        }

        if (robot.getY() > toY) {
            setDirection(robot, Direction.DOWN);
        } else {
            setDirection(robot, Direction.UP);
        }

        while (robot.getY() != toY) {
            robot.stepForward();
        }
    }
}
