package Optional;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    List<Pair<String,String>> distribution;
    Solution(){
        distribution=new ArrayList<Pair<String,String>>();
    }
    public void addMatch(String left, String right){
        distribution.add(new Pair(left,right));
    }

    @Override
    public String toString() {
        return "Solution{" +
                "Distribution [<<Student>>,<<School>>]:\n{\n" + distribution +
                '}';
    }
}
