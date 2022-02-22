package Service;

import SongOLayin.Service.LyricService;
import org.junit.Test;

import static org.junit.Assert.*;

public class LyricServiceTests {
    private LyricService ls = new LyricService();

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void verifyRhymeTest() throws StringIndexOutOfBoundsException {
        LyricService lyricService = new LyricService();
        //verify empty spaces
        String line = "Otelul Galati cea mai tare din Carpati      ";
        assertEquals("ti", lyricService.verifyRhyme(line));

        String line1 = "Nopti si zile ma gandesc la tine...?!?!?!?!?!";
        assertNotEquals("ne", lyricService.verifyRhyme(line1));

        String line3 = "ah..";
        assertEquals("ah", lyricService.verifyRhyme(line3));
    }

    @Test
    public void verifyLyricLength(){
        String line3 = "ah..";
        assertEquals("ah", ls.verifyRhyme(line3));
    }

    //verify rhyme generator at limit length line.length() == 8;
    @Test
    public void verifyLyricLengthLimit(){
        String line4 = "Via  taaa.";
        assertEquals("aa", ls.verifyRhyme(line4));
    }
}
