package com.keyin;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

public class SuggestionEngineTest {


    SuggestionEngine suggestionEngine = new SuggestionEngine();

    @Test
public void testMultipleCorrections() throws URISyntaxException, IOException {
    // First: Load The Dictionary Data
    suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").toURI()));

    // Give An Incorrectly Spelled Word
    String word = "helo";

    // Attempt To Generate Suggestions For Incorrectly Spelled Word
    String suggestions = suggestionEngine.generateSuggestions(word);

    // Print The Suggestions To The Console (just for fun)
    System.out.println("Suggestions: \n" + suggestions);

    // The Suggestions Should Not Be Empty
    assertTrue(suggestions.length() > 0);

    // Results Should Contain "hello" and "help"
    System.out.println("Suggestions: \n" + suggestions);
    assertContainsWord(suggestions, "hello");
    assertContainsWord(suggestions, "halo");
}
@Test
public void testNoCorrections() throws URISyntaxException, IOException {
    // First: Load The Dictionary Data
    suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").toURI()));

    // Give A Word With No Possible Corrections
    String word = "xyzzgndjhbfvbljhvkjdgvefy";

    // Attempt To Generate Suggestions For Known Word
    String suggestions = suggestionEngine.generateSuggestions(word);

    // Suggestions Should Be Empty For Known Words
    System.out.println("Suggestions: \n" + suggestions);
    assertTrue(suggestions.length() == 0);
}

    @Test
    public void testEmptyStringInput() throws URISyntaxException, IOException {
        // First: Load The Dictionary Data
        suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").toURI()));
    
        // Give An Empty String As Input
        String word = "";
    
        // Attempt To Generate Suggestions For The Empty String
        String suggestions = suggestionEngine.generateSuggestions(word);
        System.out.println("Suggestions: \n" + suggestions);
    
        // Suggestions Should Be Empty Because The Input Is An Empty String
        assertTrue(suggestions.length()== 0);
        
    }

    private void assertContainsWord(String suggestions, String word) {
        // Helper Method To Check If Suggestions Contain A Specific Word
        if (!suggestions.contains(word)) {
            throw new AssertionError("Suggestions do not contain the word: " + word);
        }
    }
}
