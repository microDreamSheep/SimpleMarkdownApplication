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
    int startLine = 1;

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //获取修改的起始行
        String str = s.toString().substring(start, start + count);
        startLine = SimpleMarkdownStringUtil.countLine(s.toString().substring(0, start));
        originLine = SimpleMarkdownStringUtil.countLine(str);
        for (int i = startLine; i < originLine+startLine; i++) {
            ChangeText delete = new ChangeText();
            delete.setLine(i);
            delete.setStart(-1);
            delete.setState(ChangeState.DELETE);
            changeTexts.add(delete);
        }}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        //计算修改的起始
        int line = 1;
        int i = 0;
        int lastStart = 0;
        for(; i < start; i++) {
            if (s.charAt(i) == '\n') {
                line++;
                lastStart = i+1;
            }
        }
        //获取加入的内容
        String addContent = s.toString().substring(start, start + count);
        int totalAddLine =  SimpleMarkdownStringUtil.countLine(addContent);
        String[] lines = SimpleMarkdownStringUtil.spilt(s.toString(),'\n');
        for (int i1 = line; i1 < totalAddLine+line; i1++) {
            ChangeText changeText = new ChangeText();
            changeText.setLine(i1);
            changeText.setContent(lines[i1-1]);
            changeText.setStart(lastStart);
            changeText.setState(ChangeState.ADD);
            changeTexts.add(changeText);
            lastStart = lastStart + lines[i1-1].length()+1;
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        for (ChangeText next : changeTexts) {
            if (next.getState() == ChangeState.DELETE) {
                changeTexts.remove(next);
                continue;
            }
            String content = next.getContent();
            System.out.println("第" + next.getLine() + "行，内容：" + content + "，起始：" + next.getStart());
            //获取当前行的index
            int lineStart = s.toString().lastIndexOf("\n", next.getStart());
            //清空当前行Span
            ForegroundColorSpan[] spans = s.getSpans(next.getStart(), lineStart + content.length()-1, ForegroundColorSpan.class);
            for (ForegroundColorSpan span : spans) {
                s.removeSpan(span);
            }
            //设置当前行Span
            if (content.startsWith("##")) {
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(content);
                spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.RED), 0, content.length(), SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE);
                s.setSpan(new ForegroundColorSpan(Color.RED), next.getStart(), lineStart + content.length()+1, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            changeTexts.remove(next);
        }
    }

    public EditorListener(EditText editor) {
        this.editor = editor;
    }
}
