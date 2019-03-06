package core;

import java.io.File;

public abstract class FinalVariables {
    public static String version94 = "9.4";
    public static String version95 = "9.5";

    public static final File plusFold94 = new File("E:\\9.4\\plus");
    public static final File enterFold94 = new File("E:\\9.4\\enterprise");
    public static final File plusFold95 = new File("E:\\9.5\\plus");
    public static final File enterFold95 = new File("E:\\9.5\\enterprise");

    public static final File[] pathsFor94 = {plusFold94, enterFold94};
    public static final File[] pathsFor95 = {plusFold95, enterFold95};
}
