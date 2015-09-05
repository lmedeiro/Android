package com.tradersgem2.stockLibs;


//<!-- saved from url=(0055)http://introcs.cs.princeton.edu/java/stdlib/StdOut.java -->
//<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><style type="text/css"></style></head><body class=" hasGoogleVoiceExt"><pre style="word-wrap: break-word; white-space: pre-wrap;">
/*************************************************************************
 *  Compilation:  javac StdOut.java
 *  Execution:    java StdOut
 *
 *  Writes data of various types to standard output.
 *
 *************************************************************************/

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

/**
 *  &lt;i&gt;Standard output&lt;/i&gt;. This class provides methods for writing strings
 *  and numbers to standard output.
 *  &lt;p&gt;
 *  For additional documentation, see &lt;a href="http://introcs.cs.princeton.edu/15inout"&gt;Section 1.5&lt;/a&gt; of
 *  &lt;i&gt;Introduction to Programming in Java: An Interdisciplinary Approach&lt;/i&gt; by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  
 *  Modified by Luiz Medeiros 
 */
public final class StdOut {

    // force Unicode UTF-8 encoding; otherwise it's system dependent
    private static final String charsetName = "UTF-8";

    // assume language = English, country = US for consistency with StdIn
    private static final Locale US_LOCALE = new Locale("en", "US");

    // send output here
    private static PrintWriter out;

    // this is called before invoking any methods
    static {
        try {
            out = new PrintWriter(new OutputStreamWriter(System.out, charsetName), true);
        }
        catch (UnsupportedEncodingException e) { System.out.println(e); }
    }

    // don't instantiate
    private StdOut() { }

    // close the output stream (not required)
   /**
     * Close standard output.
     */
    public static void close() {
        out.close();
    }

   /**
     * Terminate the current line by printing the line separator string.
     */
    public static void println() {
        out.println();
    }

   /**
     * Print an object to standard output and then terminate the line.
     */
    public static Object println(Object x) {
        //out.println(x);
        return x;
    }

   /**
     * Print a boolean to standard output and then terminate the line.
     */
    public static boolean println(boolean x) {
        //out.println(x);
        return x;
    }

   /**
     * Print a char to standard output and then terminate the line.
     */
    public static char println(char x) {
        //out.println(x);
        return x;
    }

   /**
     * Print a double to standard output and then terminate the line.
     */
    public static double println(double x) {
        //out.println(x);
        return x;
    }

   /**
     * Print a float to standard output and then terminate the line.
     */
    public static float println(float x) {
        //out.println(x);
        return x;
    }

   /**
     * Print an int to standard output and then terminate the line.
     */
    public static int println(int x) {
        //out.println(x);
        return x;
    }

   /**
     * Print a long to standard output and then terminate the line.
     */
    public static long println(long x) {
        //out.println(x);
        return x;
    }

   /**
     * Print a short to standard output and then terminate the line.
     */
    public static short println(short x) {
        //out.println(x);
        return x;
    }

   /**
     * Print a byte to standard output and then terminate the line.
     */
    public static byte println(byte x) {
        //out.println(x);
        return x;
    }

   /**
     * Flush standard output.
     */
    public static void print() {
        out.flush();
    }

   /**
     * Print an Object to standard output and flush standard output.
     */
    public static void print(Object x) {
        out.print(x);
        out.flush();
    }

   /**
     * Print a boolean to standard output and flush standard output.
     */
    public static void print(boolean x) {
        out.print(x);
        out.flush();
    }

   /**
     * Print a char to standard output and flush standard output.
     */
    public static void print(char x) {
        out.print(x);
        out.flush();
    }

   /**
     * Print a double to standard output and flush standard output.
     */
    public static void print(double x) {
        out.print(x);
        out.flush();
    }

   /**
     * Print a float to standard output and flush standard output.
     */
    public static void print(float x) {
        out.print(x);
        out.flush();
    }

   /**
     * Print an int to standard output and flush standard output.
     */
    public static void print(int x) {
        out.print(x);
        out.flush();
    }

   /**
     * Print a long to standard output and flush standard output.
     */
    public static void print(long x) {
        out.print(x);
        out.flush();
    }

   /**
     * Print a short to standard output and flush standard output.
     */
    public static void print(short x) {
        out.print(x);
        out.flush();
    }

   /**
     * Print a byte to standard output and flush standard output.
     */
    public static void print(byte x) {
        out.print(x);
        out.flush();
    }

   /**
     * Print a formatted string to standard output using the specified
     * format string and arguments, and flush standard output.
     */
    public static void printf(String format, Object... args) {
        out.printf(US_LOCALE, format, args);
        out.flush();
    }

   /**
     * Print a formatted string to standard output using the specified
     * locale, format string, and arguments, and flush standard output.
     */
    public static void printf(Locale locale, String format, Object... args) {
        out.printf(locale, format, args);
        out.flush();
    }

    // This method is just here to test the class
    public static void main(String[] args) {

        // write to stdout
        StdOut.println("Test");
        StdOut.println(17);
        StdOut.println(true);
        StdOut.printf("%.6f\n", 1.0/7.0);
    }

}
//</pre></body></html>