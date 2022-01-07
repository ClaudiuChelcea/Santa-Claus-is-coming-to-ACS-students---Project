package main;

import checker.Checker;
import common.Constants;
import database.SantaDatabase;
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
        for(int i = 0; i < Constants.TESTS_NUMBER; ++i) {
            /* Input and output files */
            String inFile = Constants.INPUT_PATH + i + Constants.FILE_EXTENSION;
            String outFile = Constants.OUTPUT_PATH + i + Constants.FILE_EXTENSION;

            /* READ */
            my_reader.readInfo(database, inFile);

            /* EXECUTE */

            /* WRITE OUTPUT */
            my_reader.writeInfo(database, outFile);
        }

        /* Calculate score */
        Checker.calculateScore();
    }
}
