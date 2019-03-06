package core;

import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class FinalVariables {
    public static String version94 = "9.4";
    public static String version95 = "9.5";

    public static final Path buildStorage = Paths.get("E:", "builds");
    public static final Path build94 = Paths.get("E:", "builds", "9.4");
    public static final Path build95 = Paths.get("E:", "builds", "9.5");

}
