package live.midreamsheep.simplemarkdown.editor.pojo;

import androidx.annotation.NonNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ChangeState {
    ADD(1),
    DELETE(2),
    MODIFY(3);

    private final int i;

    public static ChangeState valueOf(int i) {
        switch (i) {
            case 1:
                return ADD;
            case 2:
                return DELETE;
            case 3:
                return MODIFY;
            default:
                return null;
        }
    }
    public static String getString(ChangeState i) {
        switch (i) {
            case ADD:
                return "ADD";
            case DELETE:
                return "DELETE";
            case MODIFY:
                return "MODIFY";
            default:
                return "NULL";
        }
    }

    @NonNull
    @Override
    public String toString() {
        return getString(this);
    }
}
