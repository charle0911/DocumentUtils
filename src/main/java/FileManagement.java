import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class FileManagement {
    static List<File> getFilesFromDir(String dirPath) {
        List<File> files = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(dirPath))) {
            paths.filter(Files::isRegularFile)
                    .forEach(x -> files.add(x.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }
}
