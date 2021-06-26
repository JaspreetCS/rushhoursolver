package rushhour;
import java.util.LinkedList;

public class Puzzle {

    int gridSize;
    LinkedList<Car> cars;
    Car crashedCar;

    public Puzzle(int gridSize, LinkedList<Car> cars) {
        super();
        this.gridSize = gridSize;
        this.cars = cars;
    }

    public Car getRedCar(){
        return cars.get(0);
    }

    public boolean canMoveDown(Car car) {
        if(car.x + car.size < gridSize && !crashCars(car.x+car.size, car.y))
            return true;
        return false;
    }

    public boolean canMoveUp(Car car) {
        if(car.x > 0 && !crashCars(car.x-1, car.y))
            return true;
        return false;
    }

    public boolean canMoveRight(Car car) {
        if(car.y + car.size < gridSize && !crashCars(car.x, car.y+car.size))
            return true;
        return false;
    }

    public boolean canMoveLeft(Car car) {
        if(car.y > 0 && !crashCars(car.x, car.y-1))
            return true;
        return false;
    }

    public boolean crashCars(int x, int y) {
        for(Car car : cars){
            if(car.isHorizontal()){
                if(x == car.x && y >= car.y && y < car.y + car.size){
                    crashedCar = car;
                    return true;
                }
            }
            else if(car.isVertical()){
                if(y == car.y && x >= car.x && x < car.x + car.size){
                    crashedCar = car;
                    return true;
                }
            }
        }
        return false;
    }

}

