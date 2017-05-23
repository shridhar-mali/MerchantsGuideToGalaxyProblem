import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by Shridhar on 5/21/2017.
 */
public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {

        Problem problem = new Problem("/resources/input.txt");
        Merchant merchant = new Merchant(problem);
        merchant.solveProblem();
    }

}
