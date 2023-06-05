//building an Interpreter as per the book Crafting Interpreters by X
//Started project on 5 june 2023
//Goals: to gain an understanding of how programming languages and computers work.
//       Compiler design in University was absolutely fascinating but there was design constraints that I did not agree with
//       One was that the target language was BASIC, a language not highly valued today as its time has mostly passed.
//       I would have prefered to have C as the target as it is so often used when talking about compilers and operating systems.
//       The first part of the book uses java and the later part uses C to implement LOX, a language by the book author.
//       I am very interested to compare the development experience and performnace differences when done.
//       A design goal is to extend LOX to have all primitive data types be classes, this is not done in the book, primatives are simple variables
//       Another design goal is to later implement LOX in either Kotlin or Zig as I am interested in both and would like to compare them with Java and C
//       At the time of writing I have no Java experience and limited C experience. I am currently studying 'The C Programming Languages' by Denis Richie and Brian Kerrigan


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


class Lox{

    static boolean hadError = false;
    public static void main(String[] args) throws IOException {
        if(args.length > 1){
            System.out.println("Usage jlox [script]");
            System.exit(64);
        }
        else if(args.length == 1){
            runFile(args[0]);
        }
        else{
            runPrompt();
        }
    }

    private static void runFile(String path) throws IOException{
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
        if(hadError)
        {
            System.exit(65);
        }
    }

    //run interactively; if run without arguments jlox enters a prompt where you can enter and execute code one line at a time
    //control D causes readline() to return null. If that happens break
    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for(;;)
        {
            System.out.print("> ");
            String line = reader.readLine();
            if(line == null) break;
            run(line);
            hadError = false;
        }
    }

    // the prompt aand file runner are both thin wrappers around this core function:
    private static void run(String source){
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        for(Token token: tokens){
            System.out.println(token);
        }
    }

    //seperate code that generates code from code that reports them
    static void error(int line, String message){
        report(line, "", message);
    }

    private static void report(int line, String where, String message){
        System.err.println("[line " + line + "] Error" + where + ": " + message);
        hadError = true;
    }
}