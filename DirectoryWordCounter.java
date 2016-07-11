import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class Document {
    private final List<String> lines;
    
    Document(List<String> lines) {
        this.lines = lines;
    }
    
    List<String> getLines() {
        return this.lines;
    }
    
    static Document fromFile(File file) throws IOException {
        List<String> lines = new LinkedList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        }
        return new Document(lines);
    }
}


class Folder {
    private final List<Folder> subFolders;
    private final List<Document> documents;
    
    Folder(List<Folder> subFolders, List<Document> documents) {
        this.subFolders = subFolders;
        this.documents = documents;
    }
    
    List<Folder> getSubFolders() {
        return this.subFolders;
    }
    
    List<Document> getDocuments() {
        return this.documents;
    }
    
    static Folder fromDirectory(File dir) throws IOException {
        List<Document> documents = new LinkedList<>();
        List<Folder> subFolders = new LinkedList<>();
        for (File entry : dir.listFiles()) {
            if (entry.isDirectory()) {
                subFolders.add(Folder.fromDirectory(entry));
            } else {
                documents.add(Document.fromFile(entry));
            }
        }
        return new Folder(subFolders, documents);
    }
}


public class DirectoryWordCounter {
	private final ForkJoinPool forkJoinPool = new ForkJoinPool();
	
    String[] wordsIn(String line) {
        return line.trim().split("(\\s|\\p{Punct})+");//separated with whitespace or punctuation;
    }
  
    //Computing the count of word in a document file;
    Long occurrencesCount(Document document, String searchedWord) {
        long count = 0;
        for (String line : document.getLines()) {
            for (String word : wordsIn(line)) {
                if (searchedWord.toLowerCase().equals(word.toLowerCase())) { //if requires case sensitive, delete the .toLowerCase();
                    count = count + 1;
                }
            }
        }
        return count;
    }
    
    class DocumentSearchTask extends RecursiveTask<Long> {
        /**
		 * RecursiveAction's instance can't generate a return value;
		 * however,RecursiveTask's instance can generate a  return value;
		 */
		private static final long serialVersionUID = 1L; //added by default;
		private final Document document; 
        private final String searchedWord;
      
        DocumentSearchTask(Document document, String searchedWord) {
            super();
            this.document = document;
            this.searchedWord = searchedWord;
        }
      
        @Override
        protected Long compute() {
            return occurrencesCount(document, searchedWord);
        }
    }
    
    class FolderSearchTask extends RecursiveTask<Long> {
    	
		private static final long serialVersionUID = 1L; //added by default;
		private final Folder folder;
        private final String searchedWord;
      
        FolderSearchTask(Folder folder, String searchedWord) {
            super();
            this.folder = folder;
            this.searchedWord = searchedWord;
        }
      
        @Override
        protected Long compute() {
            long count = 0L;
            List<RecursiveTask<Long>> forks = new LinkedList<>();
            for (Folder subFolder : folder.getSubFolders()) {
                FolderSearchTask task = new FolderSearchTask(subFolder, searchedWord);
                forks.add(task);
                task.fork();
           }
            for (Document document : folder.getDocuments()) {
                DocumentSearchTask task = new DocumentSearchTask(document, searchedWord);
                forks.add(task);
                task.fork();
           }
            for (RecursiveTask<Long> task : forks) {
                count = count + task.join();
           }
        return count;
      }
    }

    Long countOccurrencesInParallel(Folder folder, String searchedWord) {
        return forkJoinPool.invoke(new FolderSearchTask(folder, searchedWord));
    }
    
    public static void main(String[] args) throws IOException {
    	System.out.println("Please enter the Folder Path and Search Word seperated with whitespace : ");
    	Scanner scanner = new Scanner(System.in);
    	String str = scanner.nextLine();
    	String[] arg = str.split(" ");
    	DirectoryWordCounter wordCounter = new DirectoryWordCounter();
        Folder folder = Folder.fromDirectory(new File(arg[0]));
        System.out.println(wordCounter.countOccurrencesInParallel(folder, arg[1]));
        scanner.close();
    }
    
}

