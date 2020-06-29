package sample.FlyWeight.Notes;

import javafx.scene.text.Text;
import sample.Handler.NoteTranslator;

public class NoteImpl implements Note {
    private final String note;

    public NoteImpl(String note){
        this.note = NoteTranslator.translate(note);
        NoteFactory.addNote(this.note);
    }

    public String getNote(){
        return this.note;
    }

    public Text getText(){
        return NoteFactory.getNote(this.note);
    }
}
