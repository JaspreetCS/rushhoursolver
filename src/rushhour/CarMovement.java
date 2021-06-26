package rushhour;
import java.util.List;

public class CarMovement {
    int x;
    int y;
    final char carName;
    int[] direction = new int[4];
    int left = 0;
    int right = 0;
    int up = 0;
    int down = 0;
    public static int count = 0;


    public CarMovement(int x, int y, char carLetter) {
        this.x = x;
        this.y = y;
        this.carName = carLetter;
    }

    public void updateMovement(Car car, List<String> outString) {
        if( (car.x - this.x) > 0 ){
            int d = car.x - this.x;
            down+=d;
            outString.add( this.carName +"D" + String.valueOf( d ) );
        }
        else if ( (car.x - this.x) < 0 )
        {
            int u = Math.abs(car.x - this.x);
            up+=u;
            outString.add( this.carName + "U" + String.valueOf( u ) );
        }
        if( (car.y - this.y) > 0 ){
            int r = car.y - this.y;
            right+=r;
            outString.add( this.carName + "R" + String.valueOf( r ) );
        }
        else if ( (car.y - this.y) < 0 )
        {
            int l = Math.abs(car.y - this.y);
            left+=l;
            outString.add( this.carName + "L" +String.valueOf( l ) );
        }
        this.x = car.x;
        this.y = car.y;
    }

    @Override
    public String toString() {
        String out = carName +
                (right > 0 ? "R" + right :"") +
                (left > 0 ? "L" + left :"") +
                (up > 0 ? "U" + up :"") +
                (down > 0 ? "D" + down :"");
        if ( out.length() != 1 ) {
            return out;
        }
        else {
            return "";
        }
    }
}
