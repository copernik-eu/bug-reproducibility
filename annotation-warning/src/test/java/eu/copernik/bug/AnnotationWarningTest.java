package eu.copernik.bug;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class AnnotationWarningTest {

    @Test
    void when_class_annotation_missing_then_no_warning(@TempDir Path target) throws URISyntaxException, IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        WarningCollector collector = new WarningCollector();

        Path sourceFile = Paths.get(Objects.requireNonNull(getClass().getResource("/package/AnnotationWarning.java"))
                .toURI());
        Path sourceDirectory = sourceFile.getParent().getParent();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(collector, null, StandardCharsets.UTF_8);
        fileManager.setLocation(StandardLocation.SOURCE_PATH, Collections.singletonList(sourceDirectory.toFile()));
        fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Collections.singletonList(target.toFile()));

        boolean result = compiler.getTask(
                        null,
                        fileManager,
                        collector,
                        Arrays.asList("-Xlint:all", "-Werror"),
                        null,
                        fileManager.getJavaFileObjects(sourceFile.toFile()))
                .call();
        assertThat(collector.getWarnings())
                .extracting(d -> d.getMessage(Locale.ROOT))
                .isEmpty();
        assertThat(result).as("Compilation was successful").isTrue();
        assertThat(target.resolve("package/AnnotationWarning.class")).exists();
    }

    private static class WarningCollector implements DiagnosticListener<JavaFileObject> {

        private final List<Diagnostic<? extends JavaFileObject>> warnings = new ArrayList<>();

        @Override
        public void report(Diagnostic<? extends JavaFileObject> diagnostic) {
            switch (diagnostic.getKind()) {
                case ERROR:
                case WARNING:
                case MANDATORY_WARNING:
                    warnings.add(diagnostic);
                    break;
                default:
            }
        }

        public List<Diagnostic<? extends JavaFileObject>> getWarnings() {
            return Collections.unmodifiableList(warnings);
        }
    }
}
