package core;

import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class FinalVariables {
    public static String version94 = "9.4";
    public static String version95 = "9.5";

    public static final Path spttFolder = Paths.get("C:", "Program Files (x86)", "SmartPTT");
    public static final Path programData = Paths.get("C:", "ProgramData");
    public static final Path buildStorage = Paths.get(programData.toString(), "builds");
    public static final Path build94 = Paths.get(programData.toString(), "builds", "9.4");
    public static final Path build95 = Paths.get(programData.toString(), "builds", "9.5");

    public static final Path spttFolderConfig = Paths.get(spttFolder.toString(),
            "RadioService.exe.Config");

    public static final Path temp = Paths.get("C:", "temp");


}
