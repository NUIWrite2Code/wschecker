package controllers;
import javax.tools.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Locale;
import java.io.File;
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
        pInputText = pInputText.replace("<br/>","\n");
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        long x = System.currentTimeMillis();
        File javaFile = new File("../app/target/universal/stage/Test"+x+".java");
        //File javaFile = new File("./Test.java");
        PrintWriter pw = new PrintWriter(javaFile);
        pw.println("public class Test"+x+"{");
        pw.println(pInputText);
        pw.println("}");
        pw.close();

        String fileName = javaFile.getName();

        /*
         * We read the file as a Text file, to get lines of text to display to the
         */
        FileReader fr = new FileReader(javaFile);
        BufferedReader buf = new BufferedReader(fr);
        ArrayList<String> lines = new ArrayList<String>();
        
        String line = buf.readLine();
        
        while(line != null)
        {
            lines.add(line);
            line = buf.readLine();
        }
        buf.close();


        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromStrings(Arrays.asList(fileName));

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits).call();

        int count = 0;
        String response = "Please address the following errors:\n\n";
        for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics())
        {

            //response += diagnostic.getKind() + ":\t Line [" + diagnostic.getLineNumber() + "] \t Position [" + diagnostic.getPosition() + "]\t" + diagnostic.getMessage(Locale.ROOT) + "\n";
            //long myLine = diagnostic.getLineNumber() - 1;
            //response += "Line [" + Long.toString(myLine) + "] \t Position [" + diagnostic.getPosition() + "]\t" + diagnostic.getMessage(Locale.ROOT) + "\n";
            
            /* 
             * Here we include the tracking on the line of codes input by the user
             */
            long l = diagnostic.getLineNumber() -1;
            int index = (int) l;
            response = lines.get(index).trim() +":"+ diagnostic.getMessage(Locale.ROOT) + "\n";
            count++;
        }

        javaFile.delete();

        if(count == 0)
        {
            response = "Great job! You have no compilation errors!";
        }

        return response;
    }
}
