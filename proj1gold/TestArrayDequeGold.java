import static org.junit.Assert.*;
import org.junit.Test;
import edu.princeton.cs.introcs.StdRandom;
public class TestArrayDequeGold {


    @Test
    public void TestArrayDeque() {

        //instantiate Demo and test class
        StudentArrayDeque<Integer> StudentL = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> DemoL = new ArrayDequeSolution<>();
        String log = "";
        for (int i = 0; i < 1000; i++) {
            //a random number add to the list
            int addNumber = StdRandom.uniform(100);
            Integer removeExpect = null;
            Integer removActual = null;

            //to choose add at first or last
            //Tips:the function unfirom is [0.0,2)
            int x = StdRandom.uniform(4);
            switch (x) {
                case 0:
                    StudentL.addFirst(addNumber);
                    DemoL.addFirst(addNumber);
                    log = log + "addFirst(" + addNumber + ")\n";
                    break;
                case 1:
                    StudentL.addLast(addNumber);
                    DemoL.addLast(addNumber);
                    log = log + "addLast(" + addNumber + ")\n";
                    break;
                case 2:
                    removeExpect = DemoL.removeFirst();
                    removActual = StudentL.removeFirst();
                    log = log + "removeFirst()\n";
                    break;
                case 3:
                    removeExpect = DemoL.removeLast();
                    removActual = StudentL.removeLast();
                    log = log + "removeLast()\n";
                    break;
            }
            assertEquals(log, removeExpect, removActual);
        }
    }
}
