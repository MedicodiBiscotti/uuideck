package dk.kavv.uuideck.version;

import picocli.CommandLine;

import java.io.InputStream;
import java.util.Properties;

public class PropertyVersionProvider implements CommandLine.IVersionProvider {
    @Override
    public String[] getVersion() throws Exception {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("version.properties")) {
            if (is != null) {
                Properties properties = new Properties();
                properties.load(is);
                return new String[]{
                        "@|yellow %s %s|@".formatted(properties.getProperty("name"), properties.getProperty("version")),
                        "Built %s".formatted(properties.getProperty("build.timestamp")),
                        "(c) %s kavv.dk".formatted(properties.getProperty("copyright.year"))
                };
            }
        }
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("META-INF/maven/dk.kavv.uuideck/uuideck/pom.properties")) {
            if (is != null) {
                Properties properties = new Properties();
                properties.load(is);
                return new String[]{
                        "@|yellow %s %s|@".formatted(properties.getProperty("artifactId"), properties.getProperty("version")),
                };
            }
        }
        return new String[]{
                "@|red Version files not found|@"
        };
    }
}
