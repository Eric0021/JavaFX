package sample.Handler;

public class NoteTranslator {
    public static String translate(String noteString) {
        String notes = "C C#D D#E F F#G G#A A#B ";
        int noteNum = 0;
        try{
            // remove any spaces in the string.
            noteString = noteString.replaceAll("\\s", "");
            noteNum = Integer.parseInt(noteString);
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        String noteResult;
        int octave = noteNum / 12;
        noteResult = notes.substring((noteNum % 12) * 2, (noteNum % 12) * 2 + 2);
        if(noteResult.charAt(1)==' '){
            // remove the space if the note is not a sharp.
            noteResult = Character.toString(noteResult.charAt(0));
        }
        return noteResult+octave;
    }
}
