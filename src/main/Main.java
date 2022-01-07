package main;

import checker.Checker;
import common.Constants;
import database.SantaDatabase;
import database_interfaces.Observer;
import dataobjects.SantaChildDatabase;
import inputoutput.InfoReaderWriter;

import javax.xml.crypto.Data;

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
        for(int i = 1; i < Constants.TESTS_NUMBER; ++i) {
            /* Input and output files */
            String inFile = Constants.INPUT_PATH + i + Constants.FILE_EXTENSION;
            String outFile = Constants.OUTPUT_PATH + i + Constants.FILE_EXTENSION;

            /* READ */
            my_reader.readInfo(database, inFile);

            /* Add observer */
            database.addObserver(new SantaChildDatabase(database.getStartingData().getChildrenList()));

            /* EXECUTE */
            for(int j = 0; j < SantaDatabase.getInstance().getNumberOfYears(); ++j) {
                SantaChildDatabase.giveGifts();
                SantaChildDatabase.increaseAge();
                SantaChildDatabase.executeUpdate();
            }

            /* WRITE OUTPUT */
            my_reader.writeInfo(database, outFile);
        }

        /* Calculate score */
        Checker.calculateScore();
    }
}
