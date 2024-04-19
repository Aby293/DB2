import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

@SuppressWarnings({"rawtypes"})
public class Page implements Serializable {
    private static final long serialVersionUID = 1L;
    String name;
    int N;
    Vector<Object> tuples = new Vector<>(N);

    public Page(String name) {
        this.name = name;
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("resources/DBApp.config")) {
            properties.load(input);
            N = Integer.parseInt(properties.getProperty("MaximumRowsCountinPage"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName(){
        return name;
    }

    public int getN(){
        return N;
    }

    public Vector<Object> getTuples(){
        return tuples;
    }

    public void setName(String s){
        this.name=s;
    }

    public void setTuples(Vector<Object> v){
        this.tuples=v;
    }

    public void insert(Object x) {
        if (!isFull()) {
            tuples.add(x);
        }
    }

    public void delete(Object x) {
        tuples.remove(x);
    }

    public void remove(int i) {
        tuples.remove(i);
    }

    public boolean isEmpty() {
        return tuples.isEmpty();
    }

    public boolean isFull() {
        return tuples.size() == N;
    }

    public Object getLastTuple(){
        return tuples.lastElement();
    }

    public Object getFirstTuple(){
        return tuples.get(0);
    }

    public int linearTupleSearch(String key,Object o){
        for (int i = 0; i < tuples.size(); i++) {
            Hashtable tuple = (Hashtable) tuples.elementAt(i);
            String s=tuple.get(key).toString();
            if(s.equals(o.toString())){
                return i;
            }  
            
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    public int BinaryTupleSearch(String ckey,Object o){
        int max=tuples.size()-1;
        int min=0;
        int mid;
        while(min<=max){
            mid=(max+min)/2;
            Hashtable tuple = (Hashtable) tuples.elementAt(mid);
            Comparable c=(Comparable)tuple.get(ckey);
            if(c.compareTo(o)==0)
              return mid;
            else if(c.compareTo(o)>0)
              max=mid-1;
            else
              min=mid+1;
        }
        return -1;
    }

    public int getInsertLoc(String ckey,Object o){
        int max=tuples.size()-1;
        int min=0;
        int mid;
        int res = -1;



        while(min<=max){
            mid=(max+min)/2;
            Hashtable tuple = (Hashtable) tuples.elementAt(mid);
            Object s=tuple.get(ckey);
            if(o instanceof String){
                if(s.toString().compareTo(o.toString())>=0){
                    res= mid;
                    max=mid-1;
                  }
                  else
                    min=mid+1;
            }
            else if (o instanceof Integer){
                if((Integer)s>=(Integer)o){
                    res= mid;
                    max=mid-1;
                  }
                  else
                    min=mid+1;
            }
            else{
                if((Double)s>=(Double)o){
                    res= mid;
                    max=mid-1;
                  }
                  else
                    min=mid+1;
            }
        }
        if(res==-1){
            return min;
        }
        return res;
    }

    @Override
    public String toString() {
        String r = "";
        for (int i = 0; i < tuples.size(); i++) {
            Hashtable tuple = (Hashtable) tuples.elementAt(i);
            if (i == tuples.size() - 1) {
                r += "" + this.printTuple(tuple);
            } else {
                r += "" + this.printTuple(tuple) + ",";
            }
        }
        return r;
    }

    public String printTuple(Hashtable ht) {
        Object[] arr = ht.values().toArray();
        String s = "";
        for (int i = arr.length - 1; i >= 0; i--) {
            s += "" + arr[i];
            if (i != 0) {
                s += ",";
            }
        }
        return s;
    }

}
