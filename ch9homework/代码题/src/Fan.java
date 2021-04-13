public class Fan {
    public static final int SLOW = 1;
    public static final int MEDIUM = 2;
    public static final int FAST = 3;
    private int speed;
    private boolean on;
    private double radius;
    public String color;
    public void setSpeed(int s){
        if (s<=3 && s>=1){
            this.speed = s;
        }
        else{
            System.out.println("Set an error speed level.");
        }
    }
    public int getSpeed(){
        return this.speed;
    }
    public void setOn(boolean o){
        this.on = o;
    }
    public boolean getOn(){
        return this.on;
    }
    public void setRadius(double r){
        if(r<0){
            System.out.println("The radius cannot be lower than zero!");
        }
        else{
            this.radius = r;
        }
    }
    public double getRadius(){
        return this.radius;
    }
    public void setColor(String c){
        this.color = c;
    }
    public String getColor(){
        return this.color;
    }
    public Fan(){
        this.speed = Fan.SLOW;
        this.on = false;
        this.radius = 5;
        this.color = "blue";
    }
    @Override
    public String toString(){
        if(on){
            switch (this.speed){
                case Fan.FAST:
                    return "speed: FAST; color: " + this.getColor() + "; radius: " + this.getRadius();
                case Fan.MEDIUM:
                    return "speed: MEDIUM; color: " + this.getColor() + "; radius: " + this.getRadius();
                case Fan.SLOW:
                    return "speed: SLOW; color: " + this.getColor() + "; radius: " + this.getRadius();
                default:
                    return "ERROR";
            }
        }
        else{
            return "fan is off; color: " + this.getColor() + "; radius: " + this.getRadius();
        }
    }
}
