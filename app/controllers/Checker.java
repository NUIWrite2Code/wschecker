package controllers;
import javax.tools.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Locale;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class Checker
{
    /**
     * <b>Description: </b> Method in charge of the syntax revision of the Java Code.\n
     * Sample code retrieved and adapted from: https://stackoverflow.com/questions/11298856/syntax-checking-in-java
     * @param pInputText text with the code
     * @return returns a text with the compilation units with details on errors and aspects to check
     * @throws Exception
     */
    public static String checkCode(String pInputText) throws Exception
    {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        File javaFile = new File("../app/target/universal/stage/Test.java");
        //File javaFile = new File("./Test.java");
        PrintWriter pw = new PrintWriter(javaFile);
        pw.print(pInputText);
        pw.close();

        String fileName = javaFile.getName();


        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromStrings(Arrays.asList(fileName));

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits).call();

        String response = "";
        for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics())
        {
            response += diagnostic.getKind() + ":\t Line [" + diagnostic.getLineNumber() + "] \t Position [" + diagnostic.getPosition() + "]\t" + diagnostic.getMessage(Locale.ROOT) + "\n";
        }

        javaFile.delete();

        return response;
    }
}
