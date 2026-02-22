package manager;

import commands.Executable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Stack;

public class StackManager {

    private static Stack<String> fileStack = new Stack<>();
    private static HashSet<Executable> commandSet = new HashSet<>();

    public static void pushFileToStack(File file) throws FileNotFoundException {
        if (file.exists() && file.isFile()) {
            fileStack.push(file.getAbsolutePath());
        }
    }

    public static void addCommandToSet(Executable command) {
        commandSet.add(command);
    }

    public static boolean isRecursive(File file) {
        return fileStack.contains(file.getAbsolutePath());
    }

    public static void popFileFromStack() {
        fileStack.pop();
    }

    public static void removeCommandFromSet(Executable command) {
        commandSet.remove(command);
    }

    public static void clearCommandSet() {
        commandSet.clear();
    }
}
