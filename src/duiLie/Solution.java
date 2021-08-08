package duiLie;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Solution {
        public int firstUniqChar(String s) {
            Map<Character, Boolean> map=new HashMap<Character, Boolean>();
            Queue<Character> queue=new LinkedList<Character>();
            for(int i=0;i<s.length();i++){
                if(!map.containsKey(s.charAt(i))){
                    map.put(s.charAt(i),true);
                    queue.add(s.charAt(i));

                }else {
                    map.put(s.charAt(i),false);
                }
            }
            while (!queue.isEmpty()&&!map.get(queue.peek())){
                queue.poll();
            }
            return queue.isEmpty()? ' ':queue.peek();
        }
}

