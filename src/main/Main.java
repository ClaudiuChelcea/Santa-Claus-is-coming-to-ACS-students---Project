package main;

import checker.Checker;
import common.Constants;
import database.SantaDatabase;
import dataobjects.SantaChildDatabase;
import helpers.Helper;
import inputoutput.InfoReaderWriter;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) {
        /* Get the database */
        SantaDatabase database = SantaDatabase.getInstance();

        /* Get the JSON reader and writer */
        InfoReaderWriter my_reader = new InfoReaderWriter();

        /* Execute tests */
        for(int i = 1; i <= Constants.TESTS_NUMBER; ++i) {
            /* Input and output files */
            String inFile = Constants.INPUT_PATH + i + Constants.FILE_EXTENSION;
            String outFile = Constants.OUTPUT_PATH + i + Constants.FILE_EXTENSION;

            /* READ */
            database.clear();
            my_reader.readInfo(database, inFile);

            /* Refresh update for new tests */
            SantaDatabase.updateNumber = 0;

            /* Add observer */
            database.addObserver(new SantaChildDatabase(database.getStartingData().getChildrenList()));

            /* EXECUTE */
            var tmp1 = SantaChildDatabase.newChildList;
            for(int j = 0; j < SantaDatabase.getInstance().getNumberOfYears(); ++j) {
                SantaChildDatabase.giveGifts();
                SantaChildDatabase.increaseAge();
                SantaChildDatabase.executeUpdate(j+1);
            }
            var tmp2 = SantaChildDatabase.newChildList;
            /* WRITE OUTPUT */
            my_reader.writeInfo(database, outFile);
        }

        /* Calculate score */
        Checker.calculateScore();
    }
}

