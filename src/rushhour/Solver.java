package rushhour;
import java.io.*;
import java.util.*;

public class Solver {


    public PriorityQueue<State> pq;
    private String outputPath;

    private LinkedList<State> searchAStar(Puzzle puzzle, Heuristic heuristic) {
        HashMap<State, State> predecessor = new HashMap<>();
        HashMap<String, Boolean> visited = new HashMap<>();
        State src = new State(puzzle);
        State goal = null;
        src.setCost(0);
        pq.add(src);
        visited.put(src.toString(), true);
        while(!pq.isEmpty()){
            State u = pq.poll();
            if(u.isGoal()){
                goal = u;
                break;
            }
            for(State v : u.getNeighbors()){
                int cost = u.cost + 1 + heuristic.getValue(v);
                if(!contains(visited, v)){
                    v.setCost(cost);
                    pq.add(v);
                    predecessor.put(v, u);
                    visited.put(v.toString(), true);
                }
            }
        }

        return getPath(predecessor, goal);
    }

    private boolean contains(HashMap<String, Boolean> visited, State v) {
        return visited.containsKey(v.toString());
    }

    private void print(LinkedList<State> path) {
        System.out.println("Number of optimal movements = " + (path.size()-1) + "\n");
        Map<Character, CarMovement> movementMap = new HashMap<>();
        List<String> outString = new ArrayList<>();
        int index = 0;
        for(State s : path){
            if(index == 0)
                System.out.println("Initial state:");
            else
                System.out.println("Step " + index + ":");
            s.print();
            index ++;
            for (Car car : s.puzzle.cars) {
                CarMovement carMovement;
                if ( movementMap.get( car.carLetter ) != null) {
                    carMovement = movementMap.get( car.carLetter );
                    carMovement.updateMovement( car, outString );
                }
                else{
                    carMovement = new CarMovement( car.x,car.y, car.carLetter  );
                    movementMap.put( car.carLetter, carMovement );
                }
            }
        }

        writeToTheFile( outString );
    }

    private void writeToTheFile( List<String> outString) {
        try {
            FileWriter writer = new FileWriter( outputPath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            for (String s : outString) {
                bufferedWriter.write( s );
                bufferedWriter.newLine();

            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private LinkedList<State> getPath(HashMap<State, State> pred, State goal) {

        LinkedList<State> path =  new LinkedList<>();
        State u = goal;
        path.addFirst(u.clone());
        while(pred.get(u) != null){
            State parent = pred.get(u);
            path.addFirst(parent.clone());
            u = parent;
        }

        return path;
    }

    private Puzzle readInput(char[][] grid) {

        LinkedList<Car> cars = new LinkedList<>();

        Map<Character,Car>  carMap = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (carMap.get( grid[i][j] ) != null) {
                    Car car = carMap.get( grid[i][j] );
                    if ((car.x < i)) {
                        car.orientation = "v";
                        car.size+=1;
                    }
                    else if( car.y < j )
                    {
                        car.orientation = "h";
                        car.size+=1;
                    }
                }
                else if ( grid[i][j] != '.' )
                {
                    Car newCar = new Car(i, j, grid[i][j], "", 1);
                    carMap.put(grid[i][j], newCar );
                    if (grid[i][j] == 'X') {
                        cars.addFirst( newCar );
                    }
                    else{
                        cars.add( newCar );
                    }
                }
            }
        }

        return new Puzzle(grid.length, cars);
    }

    private void run(char[][] grid) {
        pq = new PriorityQueue<>(10, Comparator.comparingInt(o -> o.cost));

        Puzzle puzzle = readInput( grid );

        Heuristic heuristic = new Heuristic();

        long startTime = System.currentTimeMillis();
        LinkedList<State> path1 = searchAStar(puzzle, heuristic);
        long endTime = System.currentTimeMillis();
        long timeTaken1 = endTime - startTime;

        System.out.println("Solution using Heuristic.");
        System.out.println("#########################");
        print(path1);
        System.out.println("Time taken using heuristic : " + timeTaken1);


    }

    public static void solveFromFile(String inputPath, String outputPath ){

        char[][] grid = getTheGridFromFile( inputPath );

        Solver solver = new Solver();
        solver.setOutPutPath(outputPath);
        solver.run( grid );
    }

    private static char[][] getTheGridFromFile(String inputPath) {
        char[][] grid = null;
        try {
            FileReader reader = new FileReader( inputPath );
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            int i = 0;
            if (line != null) {
                int size = line.length();
                grid = new char[size][size];
                grid[i++] = line.toCharArray();
            }
            while ((line = bufferedReader.readLine()) != null && grid != null ) {

                grid[i++] = line.toCharArray();
                System.out.println(line);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return grid;
    }

    private void setOutPutPath(String outputPath) {
        this.outputPath = outputPath;
    }

}
