import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

public class ConflationExample {

    private static final Set<String> STOP_WORDS = new HashSet<>(Arrays.asList(
            "a", "about", "above", "after", "again", "against", "all", "am", "an", "and",
            "any", "are", "aren't", "as", "at", "be", "because", "been", "before", "being",
            "below", "between", "both", "but", "by", "can't", "cannot", "could", "couldn't",
            "did", "didn't", "do", "does", "doesn't", "doing", "don't", "down", "during",
            "each", "few", "for", "from", "further", "had", "hadn't", "has", "hasn't",
            "have", "haven't", "having", "he", "he'd", "he'll", "he's", "her", "here",
            "here's", "hers", "herself", "him", "himself", "his", "how", "how's", "i",
            "i'd", "i'll", "i'm", "i've", "if", "in", "into", "is", "isn't", "it", "it's",
            "its", "itself", "let's", "me", "more", "most", "mustn't", "my", "myself",
            "no", "nor", "not", "of", "off", "on", "once", "only", "or", "other", "ought",
            "our", "ours", "ourselves", "out", "over", "own", "same", "shan't", "she",
            "she'd", "she'll", "she's", "should", "shouldn't", "so", "some", "such",
            "than", "that", "that's", "the", "their", "theirs", "them", "themselves",
            "then", "there", "there's", "these", "they", "they'd", "they'll", "they're",
            "they've", "this", "those", "through", "to", "too", "under", "until", "up",
            "very", "was", "wasn't", "we", "we'd", "we'll", "we're", "we've", "were",
            "weren't", "what", "what's", "when", "when's", "where", "where's", "which",
            "while", "who", "who's", "whom", "why", "why's", "with", "won't", "would",
            "wouldn't", "you", "you'd", "you'll", "you're", "you've", "your", "yours",
            "yourself", "yourselves"));

    public static void main(String[] args) throws FileNotFoundException, IOException {

        String filePath = "C:/Users/Sakshi/OneDrive/Desktop/LB Project/Marvellous File Packer - Unpacker/input.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            // Read the entire text from file
            String text = reader.lines().collect(Collectors.joining("\n"));

            // Split text into words
            List<String> words = Arrays.asList(text.toLowerCase().split("\\W+"));

            // Remove stop words
            List<String> filteredWords = words.stream()
                    .filter(word -> !STOP_WORDS.contains(word))
                    .collect(Collectors.toList());

            // Apply stemming (using a simple PorterStemmer implementation)
            List<String> stemmedWords = filteredWords.stream()
                    .map(PorterStemmer::stem)
                    .collect(Collectors.toList());

            // Calculate word frequencies
            Map<String, Long> wordFrequencies = stemmedWords.stream()
                    .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

            // Print results
            System.out.println("Original text: " + text);
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("Filtered words: " + filteredWords);
            System.out.println(
                    "-----------------------------------------------------------------------------------------");
            System.out.println("Stemmed words: " + stemmedWords);
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("Word frequencies: ");

            wordFrequencies.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

            System.out.println("----------------------------------------------------------------------------------");
        }
    }
}

class PorterStemmer {
    // Implement Porter Stemmer algorithm
    // For brevity, this is a simplified version of the algorithm
    public static String stem(String word) {
        if (word.length() <= 2) {
            return word;
        }

        // Remove plural 's'
        if (word.endsWith("s")) {
            word = word.substring(0, word.length() - 1);
        }

        // Simple stemming rules
        if (word.endsWith("ing") && word.length() > 4) {
            return word.substring(0, word.length() - 3);
        }

        else if (word.endsWith("ed") && word.length() > 3) {
            return word.substring(0, word.length() - 2);
        }

        return word;
    }
}
