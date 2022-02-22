package SongOLayin;

import SongOLayin.DataPersistence.SingerToDB;
import SongOLayin.DataPersistence.SongOLayinTableCreator;
import SongOLayin.Mingler.CrossedRhyme;
import SongOLayin.Mingler.PairedRhyme;
import SongOLayin.Service.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class LyricGenerator {
    public static void main(String[] args) throws SQLException, IOException {

        File folderName = new File("C:\\Users\\ovidi\\Desktop\\SongOLayin");
// Create tables

        SongOLayinTableCreator creator = new SongOLayinTableCreator();
        creator.createTableOfSingers();
        creator.createTableOfSongs();
        creator.createTableOfLyrics();

        TypeGuesser typeGuesser = new TypeGuesser();

        // Write all singers in DB

        try {
            SingerService.getInstance().singersToDB();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //introduce songs in db
        try {
            SongService.getInstance().songsToDB();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //introduce lyrics in db

        try {
            LyricService.getInstance().lyricsToDB();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //get lyrics paired two by two

        PairedRhyme.getInstance().getPairedLyrics(8);

        // get lyrics with crossed rhymes
        System.out.println(generateDedicatie("Roxana", 5));
        CrossedRhyme crossedRhyme = new CrossedRhyme();
        crossedRhyme.getCrossedLyrics(8);

        System.out.println(typeGuesser.getGuessedType(typeGuesser.getNameOfSongsAndLyrics(FileService.getInstance().allInformations())));
 //       inner join
        System.out.println(SingerToDB.getInstance().getSongsNameAndSinger());
        System.out.println(SingerToDB.getInstance().getNumberOfLyricsOfEverySinger());

        LyricService lyricService = new LyricService();
       System.out.println(lyricService.verifyRhyme("m-am nascut sa fiu talent de occident.!?"));

    }

    static List<String> texts() {
        List<String> allTexts = new ArrayList<>();

        String text1 = "HAAAUUSSSS SA TERMINAT JMEKERIA! DE LA  %s  nebunu cand da cu pumnu zici ca trage tunu!";
        String text2 = "SPECIALA ASTA 50 DA MII DA EURO A IESIT JUPANU %s LA JOC! SE ANUNTA PLOAIE DE DOLARI!";
        String text3 = "DE LA O FINETE DIN AIA MARE MARE %s DEDICA LA TOTI DUSMANII SANATATE SI NOROC SI MUIERI IOC";
        String text4 = "DE LA %s SE STIE JUPANU BANILOR DA CU BANU MOARE DUSMANU!";
        String text5 = "UNU UNU UNU DOI TREI SI! AU LUAT JMEKERII VACANTA CAND DUMNEZEU IA DAT VIATA LUI %s";
        String text6 = "FACE BANU MOARE DUSMANU! VALOARE DIN AIA MARE MARE DISTRUGATOARE! OHOHOU BOMBA NUCLEARA! CAPITANU FEMEILOR %s !";
        String text7 = "CAND SE PREDA JMEKERIA A INVATATO IN CLASA A INTAIA! UNIKAT SI DE DAME CAUTAT %s SEFU LA BANI SI AMANTU LA FEMEI!";
        allTexts.add(text1);
        allTexts.add(text2);
        allTexts.add(text3);
        allTexts.add(text4);
        allTexts.add(text5);
        allTexts.add(text6);
        allTexts.add(text7);
        return allTexts;
    }

    static int getRandomValue(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max) + min;
    }

    static String generateDedicatie(String name, int random) {
        String result = "";
        for (int i = 0; i < 100; i++) {
            if (i == random) {
                result += setString(texts().get(i), name);
            }
        }
        return result;
    }

    static String setString(String text, String value) {
        return String.format(text, value);
    }
}
