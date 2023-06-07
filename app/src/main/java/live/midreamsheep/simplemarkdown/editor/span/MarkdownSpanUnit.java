package live.midreamsheep.simplemarkdown.editor.span;

import live.midreamsheep.markdown.parser.element.span.Span;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarkdownSpanUnit {
    private String content;
    private Span[] spans;
}
