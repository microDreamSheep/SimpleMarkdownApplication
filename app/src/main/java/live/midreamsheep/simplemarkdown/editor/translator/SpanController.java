package live.midreamsheep.simplemarkdown.editor.translator;

import live.midreamsheep.markdown.parser.MarkdownParser;
import live.midreamsheep.markdown.parser.page.MarkdownPage;
import live.midreamsheep.simplemarkdown.editor.span.MarkdownSpanUnit;
import live.midreamsheep.simplemarkdown.editor.tool.str.SimpleMarkdownStringUtil;

public class SpanController {
    MarkdownPage page = new MarkdownPage();

    public SpanController(String content) {
        page.parse(content==null?new String[]{""}: SimpleMarkdownStringUtil.spilt(content,'\n'));
    }

    public void removeSpan(int line) {
        //TODO
    }
    public MarkdownSpanUnit addNewLine(int line,String content) {
        //TODO
        return null;

    }
}
