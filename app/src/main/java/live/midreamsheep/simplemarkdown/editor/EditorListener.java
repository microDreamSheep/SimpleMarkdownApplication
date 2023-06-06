package live.midreamsheep.simplemarkdown.editor;

import android.graphics.Color;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;
import live.midreamsheep.simplemarkdown.editor.pojo.ChangeState;
import live.midreamsheep.simplemarkdown.editor.pojo.ChangeText;
import live.midreamsheep.simplemarkdown.editor.tool.str.SimpleMarkdownStringUtil;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

@NoArgsConstructor
public class EditorListener implements TextWatcher {

    EditText editor;
    List<ChangeText> changeTexts = new CopyOnWriteArrayList<>();

    int originLine = 0;

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //获取被替代的内容
        originLine = SimpleMarkdownStringUtil.countLine(s.toString().substring(start, start + count));
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        //计算修改的起始
        int line = 1;
        int i = 0;
        for(; i < start; i++) {
            if (s.charAt(i) == '\n') {
                line++;
            }
        }
        //获取加入的内容
        String addContent = s.toString().substring(start, start + count);
        int totalAddLine =  SimpleMarkdownStringUtil.countLine(addContent);
        String[] lines = SimpleMarkdownStringUtil.spilt(s.toString(),'\n');

        boolean isAdd = totalAddLine>originLine;
        int modifyLine = Math.abs(originLine-totalAddLine);
        System.out.println("修改的行数："+modifyLine+"，是否是新增："+isAdd);
        for (int i1 = line; i1 < totalAddLine+line; i1++) {
            System.out.println(i1);
            ChangeText changeText = new ChangeText();
            changeText.setLine(i1);
            changeText.setContent(lines[i1-1]);
            changeText.setStart(i);
            if(i1-line<modifyLine||i1-line==0){
                changeText.setState(ChangeState.MODIFY);
            }else if(isAdd){
                changeText.setState(ChangeState.ADD);
            }else {
                changeText.setState(ChangeState.DELETE);
            }

            changeTexts.add(changeText);
            i = i + lines[i1-1].length()+1;
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        ListIterator<ChangeText> changeTextListIterator = changeTexts.listIterator();
        while (changeTextListIterator.hasNext()){
            ChangeText next = changeTextListIterator.next();
            String content = next.getContent();
            System.out.println("第"+next.getLine()+"行，内容："+content+"，是否是新增："+next.getState().toString());
            //获取当前行的index
            int lineStart = s.toString().lastIndexOf("\n", next.getStart());
            lineStart = lineStart==-1?0:lineStart;
            if (content.startsWith("##")){
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(content);
                spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.RED), 0, content.length()-1, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE);
                s.setSpan(new ForegroundColorSpan(Color.RED), lineStart, lineStart+content.length(), SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            changeTexts.remove(next);
        }
    }

    public EditorListener(EditText editor) {
        this.editor = editor;
    }
}
