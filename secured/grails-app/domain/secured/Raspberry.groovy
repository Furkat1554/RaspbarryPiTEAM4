package secured
import groovy.transform.ToString


class Raspberry {

    String IP;
    String block;
    int floor;
    String department;

    static constraints = {

    }

    @Override
    String toString() {
        return ("Block: "+block+" floor: "+floor)
    }


}
