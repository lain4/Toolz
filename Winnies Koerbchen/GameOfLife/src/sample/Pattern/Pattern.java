package sample.Pattern;

public class Pattern implements Comparable<Pattern>{

    private String name, pattern;
    private int x,y;

    public Pattern(String name, int x, int y, String pattern){
        this.name = name;
        this.x = x; this.y = y;
        this.pattern = pattern;

    }

    @Override
    public String toString(){
        return name;
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }

    @Override
    public int compareTo(Pattern other){
        return this.name.hashCode() > other.name.hashCode() ? 1 : this.name.hashCode() < other.name.hashCode() ? -1 : 0;

    }

    @Override
    public boolean equals(Object other){
        if(this==other) return true;
        if (other==null) return false;
        return this.hashCode()==other.hashCode();
    }


    public String getName(){
        return name;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public String getPattern(){
        return pattern;
    }
}
