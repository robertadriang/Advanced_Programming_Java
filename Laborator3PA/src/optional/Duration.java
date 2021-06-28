package optional;

public class Duration {
    double time;
    Duration(double time){
        if(time-(int)(time)>0.6)
        {
            this.time=time-0.4;
        }
        else
            this.time=time;
    }
}
