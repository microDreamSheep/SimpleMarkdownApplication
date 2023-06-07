package live.midreamsheep.simplemarkdown.editor.span.standard.head;

import android.text.style.RelativeSizeSpan;
import live.midreamsheep.markdown.parser.element.line.head.HeadLevel;

public class HeadSpan extends RelativeSizeSpan {

    public HeadSpan(HeadLevel headLevel) {
        super(headLevel.getLevel());
    }
}
