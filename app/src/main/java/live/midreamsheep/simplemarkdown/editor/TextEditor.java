package live.midreamsheep.simplemarkdown.editor;

import android.annotation.SuppressLint;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import live.midreamsheep.simplemarkdown.R;


public class TextEditor extends AppCompatActivity {

    EditText editor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_editor);
        editor = findViewById(R.id.text_editor);
        editor.setMaxLines(Integer.MAX_VALUE);
        editor.addTextChangedListener(new EditorListener(editor,""));

    }
}