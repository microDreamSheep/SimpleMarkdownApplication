package live.midreamsheep.simplemarkdown.editor.tool.str;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleMarkdownStringUtil {
    public static int countLine(String str) {
        if (str == null || str.isEmpty()){
            return 1;
        }
        if (str.equals("\n")){
            return 2;
        }
        return spilt(str,'\n').length;
    }
    public static String[] spilt(String str,char c){
        List<String> list = new ArrayList<>();
        //对str根据c分割，当多个c连续出现时，用空字符串代替
        char[] chars = str.toCharArray();
        int start = 0;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i]==c){
                list.add(start==i?"":str.substring(start,i));
                start = i+1;
            }
        }
        if (start-1!=chars.length){
            list.add(str.substring(start));
        }
        return list.toArray(new String[0]);

    }
}
