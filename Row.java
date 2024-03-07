import java.util.*;

public class Row {
    Vector<Object> vRow = new Vector<>();

    public Row(Object ... o){
        if(o != null) {
            vRow.addAll(java.util.Arrays.asList(o));
        }
    }


    @Override
    public String toString() {
        String r = "";

        for (int i = 0; i < vRow.size(); i++) {

            if(i == vRow.size() - 1){
                r += "" + vRow.elementAt(vRow.size() - 1);
            }
            else {
                r += "" + vRow.elementAt(i) + ",";
            }
        }

        return r;
    }

    public static void main(String[] args) {
        Row r = new Row("Ahmed", 20, "Zamalek");

        System.out.println(r);
    }
}
