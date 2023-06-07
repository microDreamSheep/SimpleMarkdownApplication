package live.midreamsheep.simplemarkdown.editor.pojo;

import androidx.annotation.NonNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ChangeState {
    ADD(1),
    DELETE(2);

    private final int i;

    public static ChangeState valueOf(int i) {
        switch (i) {
            case 1:
                return ADD;
            case 2:
                return DELETE;
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
