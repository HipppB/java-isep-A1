package com.hippp;

public class Main {
    public static void main(String[] args) {
        if(args.length == 0) {
            System.out.println("No arguments passed");
            System.out.println("Please pass either 'harrypotter' or 'chessgame' as an argument");
            System.exit(0);
        }
        switch (args[0]) {
            case "harrypotter":
                com.hippp.harrypotter.Main.main(args);
                break;
            case "chessgame":
                com.hippp.chessgame.Main.main(args);
                break;
            default:
                System.out.println("Invalid argument passed");
                System.out.println("Please pass either 'harrypotter' or 'chessgame' as an argument");
                System.exit(0);
        }


    }
}
