package secured
import groovy.transform.ToString

class Raspberry {

    String ip
    String block
    int floor
    String department

    static constraints = {
        ip unique: true
        block inList: ['A','B','C','D']
    }

    @Override
    String toString() {
        return ("Block: "+block+" floor: "+floor)
    }


}
