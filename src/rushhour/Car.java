package rushhour;

public class Car {

    int x, y, size;
    String orientation;
    char carLetter;

    public Car(int x, int y, char carLetter, String orientation, int size) {
        super();
        this.x = x;
        this.y = y;
        this.size = size;
        this.orientation = orientation;
        this.carLetter = carLetter;
    }

    public void moveDown() {
        this.x ++;
    }

    public void moveUp() {
        this.x --;
    }

    public void moveRight() {
        this.y ++;
    }

    public void moveLeft() {
        this.y --;
    }

    public boolean isHorizontal() {
        return orientation.equals("h");
    }

    public boolean isVertical() {
        return orientation.equals("v");
    }

    public Car clone(){
        return new Car(this.x, this.y, this.carLetter, this.orientation, this.size);
    }
}

