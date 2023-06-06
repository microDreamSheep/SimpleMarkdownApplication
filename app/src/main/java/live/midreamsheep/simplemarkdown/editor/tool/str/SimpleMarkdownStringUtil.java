package live.midreamsheep.simplemarkdown.editor.tool.str;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleMarkdownStringUtil {
    public static int countLine(String str) {
        if (str == null || str.isEmpty()){
            return 0;
        }
        if (str.equals("\n")){
            return 2;
        }
        return spilt(str,'\n').length;
    }
    public static String[] spilt(String str,char c){
        List<String> list = new ArrayList<>();
        Collections.addAll(list, str.split(String.valueOf(c)));
        //将开头的每个换行符转换为一个空字符串
        int j = 0;
        while (j < str.length() && str.charAt(j) == c) {
            list.add(0,"");
            j++;
        }


        //将结尾的每个换行符转换为一个空字符串
        j = str.length() - 1;
        while (j >= 0 && str.charAt(j) == c) {
            list.add("");
            j--;
        }
        return list.toArray(new String[0]);

    }
}
