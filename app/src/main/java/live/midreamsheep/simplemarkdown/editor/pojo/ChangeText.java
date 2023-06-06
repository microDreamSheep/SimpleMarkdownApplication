package live.midreamsheep.simplemarkdown.editor.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangeText {
    private ChangeState state;
    private int line;
    private String content;
    private int start;
}
