package rushhour;

public class Heuristic {

    public int getValue(State state) {
        Car redcar = state.puzzle.getRedCar();
        int gridSize = state.puzzle.gridSize;
        int numberOfMovements = 0;
        for (int i = redcar.y+2; i < gridSize; i++) {
            int x = redcar.x;
            int y = i;
            if(state.puzzle.crashCars(x, y)){
                Car car = state.puzzle.crashedCar;
                if(!state.puzzle.canMoveDown(car) && !state.puzzle.canMoveUp(car))
                    numberOfMovements += 2;
                else
                    numberOfMovements ++;
            }
        }
        return numberOfMovements;
    }

}
