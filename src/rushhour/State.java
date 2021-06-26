package rushhour;
import java.util.ArrayList;
import java.util.LinkedList;

public class State {

    Puzzle puzzle;
    int cost;


    public State(Puzzle puzzle) {
        super();
        this.puzzle = puzzle;
        this.cost = 10000000;
    }

    public ArrayList<State> getNeighbors(){
        ArrayList<State> neighbors = new ArrayList<>();
        LinkedList<Car> cars = this.puzzle.cars;
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            if(car.isVertical()){
                LinkedList<Car> newcars = cloneCars(cars);
                Car newcar = newcars.get(i);
                while(puzzle.canMoveDown(newcar)){
                    newcar.moveDown();
                    neighbors.add(new State(new Puzzle(this.puzzle.gridSize, newcars)));
                    newcars = cloneCars(newcars);
                    newcar = newcars.get(i);
                }
                newcars = cloneCars(cars);
                newcar = newcars.get(i);
                while(puzzle.canMoveUp(newcar)){
                    newcar.moveUp();
                    neighbors.add(new State(new Puzzle(this.puzzle.gridSize, newcars)));
                    newcars = cloneCars(newcars);
                    newcar = newcars.get(i);
                }
            }
            else if(car.isHorizontal()){
                LinkedList<Car> newcars = cloneCars(cars);
                Car newcar = newcars.get(i);
                while(puzzle.canMoveRight(newcar)){
                    newcar.moveRight();
                    neighbors.add(new State(new Puzzle(this.puzzle.gridSize, newcars)));
                    newcars = cloneCars(newcars);
                    newcar = newcars.get(i);
                }
                newcars = cloneCars(cars);
                newcar = newcars.get(i);
                while(puzzle.canMoveLeft(newcar)){
                    newcar.moveLeft();
                    neighbors.add(new State(new Puzzle(this.puzzle.gridSize, newcars)));
                    newcars = cloneCars(newcars);
                    newcar = newcars.get(i);
                }
            }
        }

        return neighbors;
    }

    private LinkedList<Car> cloneCars(LinkedList<Car> cars) {
        LinkedList<Car> newcars = new LinkedList<>();
        for(Car car : cars){
            newcars.add(car.clone());
        }
        return newcars;
    }


    public boolean isGoal(){
        return puzzle.getRedCar().y == puzzle.gridSize - 2;
    }

    public void print(){
        System.out.println(this.toString());
    }

    public String toString(){
        char[][] output = new char[puzzle.gridSize][puzzle.gridSize];
        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output.length; j++) {
                output[i][j] = '.';
            }
        }
        for(Car car : puzzle.cars){
            if(car.isHorizontal()){
                if(car.size == 2){
                    output[car.x][car.y] = car.carLetter;
                    output[car.x][car.y+1] = car.carLetter;

                }
                else if(car.size == 3){
                    output[car.x][car.y] = car.carLetter;
                    output[car.x][car.y+1] = car.carLetter;
                    output[car.x][car.y+2] = car.carLetter;
                }
            }
            else if(car.isVertical()){
                if(car.size == 2){
                    output[car.x][car.y] = car.carLetter;
                    output[car.x+1][car.y] = car.carLetter;
                }
                else if(car.size == 3){
                    output[car.x][car.y] = car.carLetter;
                    output[car.x +1][car.y] = car.carLetter;
                    output[car.x +2][car.y] = car.carLetter;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (char[] chars : output) {
            result.append(new String(chars)).append("\n");
        }

        return result.toString();
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public State clone(){
        return new State(this.puzzle);
    }

}

