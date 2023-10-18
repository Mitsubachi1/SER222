package Module_1.shortanswerquiz;

public class AnotherCounter implements IncrementCounter {
    private String count;
    private String id;

    public AnotherCounter(String ID) { // constructor
        this.count = "";
        this.id = ID;
    }

    public void increment() {
        count += 'x';
    }

    public int tally() {
        return count.length();
    }

    public String toString() {
        return "ID: " + id + " Count: " + tally() + "\n";
    }

    public static void main(String[] args){
        AnotherCounter newcounter = new AnotherCounter("3355");
        newcounter.increment();
        newcounter.increment();
        newcounter.tally();
        System.out.print(newcounter.toString());

    }
}
