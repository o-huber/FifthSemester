package com.company;
import java.io.*;
import spos.lab1.demo.*;
import java.util.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

class escLitnr extends KeyAdapter {
    public static boolean contin = true;

    public void keyPressed(KeyEvent evt) {
        if(evt.getKeyCode() == 27){
            contin = false;
        }
    }
}

class funcIntF {
    public static void main(String[] args) throws InterruptedException {
        int x = Integer.parseInt(args[0]);
        System.out.println(IntOps.funcF(x));
    }
}

class funcDoubleF {
    public static void main(String[] args) throws InterruptedException {
        int x = Integer.parseInt(args[0]);
        System.out.println(DoubleOps.funcF(x));
    }
}

class funcConjF {
    public static void main(String[] args) throws InterruptedException {
        int x = Integer.parseInt(args[0]);
        if(Conjunction.funcF(x)){
            System.out.println(1);
        }
        else{
            System.out.println(0);
        }
    }
}

class funcDisjF {
    public static void main(String[] args) throws InterruptedException {
        int x = Integer.parseInt(args[0]);
        if(Disjunction.funcF(x)){
            System.out.println(1);
        }
        else{
            System.out.println(0);
        }
    }
}

class funcIntG {
    public static void main(String[] args) throws InterruptedException {
        int x = Integer.parseInt(args[0]);
        System.out.println(IntOps.funcG(x));
    }
}

class funcDoubleG {
    public static void main(String[] args) throws InterruptedException {
        int x = Integer.parseInt(args[0]);
        System.out.println(DoubleOps.funcG(x));
    }
}

class funcConjG {
    public static void main(String[] args) throws InterruptedException {
        int x = Integer.parseInt(args[0]);
        if(Conjunction.funcG(x)){
            System.out.println(1);
        }
        else{
            System.out.println(0);
        }
    }
}

class funcDisjG {
    public static void main(String[] args) throws InterruptedException {
        int x = Integer.parseInt(args[0]);
        if(Disjunction.funcG(x)){
            System.out.println(1);
        }
        else{
            System.out.println(0);
        }
    }
}

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        long startTime = System.nanoTime();
        Scanner scn = new Scanner(System.in);

        System.out.println("Enter desirable func (1 - IntOps, 2 - DoubleOps, 3 - Conjunction, 4 - Disjunction");
        int choice = scn.nextInt();

        String className;
        switch (choice){
            case 1:
                className = "com.company.funcInt";
                break;
            case 2:
                className = "com.company.funcDouble";
                break;
            case 3:
                className = "com.company.funcConj";
                break;
            case 4:
                className = "com.company.funcDisj";
                break;
            default:
                System.out.println("Entered wrong choice, app will use IntOps");
                className = "com.company.funcInt";
                choice = 1;
                break;
        }

        System.out.println("Enter integer X");
        int x = scn.nextInt();

        File tempF = new File("D:\\Merger\\tempF.txt");
        File tempG = new File("D:\\Merger\\tempG.txt");

        ProcessBuilder builder = new ProcessBuilder("C:\\Program Files\\Java\\jdk-14.0.2\\bin\\java", "-cp", "D:\\Merger\\out\\production\\Merger;D:\\Merger\\lab1.jar", className + "F", Integer.toString(x));
        builder.redirectErrorStream(true);
        builder.redirectOutput(tempF);
        Process procF = builder.start();

        builder = new ProcessBuilder("C:\\Program Files\\Java\\jdk-14.0.2\\bin\\java", "-cp", "D:\\Merger\\out\\production\\Merger;D:\\Merger\\lab1.jar", className + "G", Integer.toString(x));
        builder.redirectErrorStream(true);
        builder.redirectOutput(tempG);
        Process procG = builder.start();

        Scanner scanF = new Scanner(tempF);
        Scanner scanG = new Scanner(tempG);

        double resF = 1, resG = 1;

        JFrame f = new JFrame();

        f.addKeyListener(new escLitnr());
        f.setSize(300, 300);
        f.setVisible(true);

        int fCheck = 0;

        while(escLitnr.contin){
            if(tempF.length() != 0){
                fCheck = 1;
                break;
            }
            if(tempG.length() != 0){
                fCheck = 2;
                break;
            }
        }

        if(fCheck == 1){
            resF = scanF.nextDouble();
        }
        else if(fCheck == 2){
            resG = scanG.nextDouble();
        }

        if((resF == 0)){
            System.out.println("Function f(x) returned 0");
            double elapsedTime = (System.nanoTime() - startTime)/1000000000;
            System.out.println("Elapsed time (in seconds)" + elapsedTime);
            procF.destroy();
            procG.destroy();
            f.dispose();
        }
        else if(resG == 0){
            System.out.println("Function g(x) returned 0");
            double elapsedTime = (System.nanoTime() - startTime)/1000000000;
            System.out.println("Elapsed time (in seconds)" + elapsedTime);
            procF.destroy();
            procG.destroy();
            f.dispose();
        }

        else {
            while (escLitnr.contin) {
                if ((tempF.length() != 0) && (tempG.length() != 0)) {
                    break;
                }
            }

            if (escLitnr.contin) {
                if(fCheck == 1){
                    resG = scanG.nextDouble();
                }
                else if(fCheck == 2){
                    resF = scanF.nextDouble();
                }
                System.out.println("Functions results");
                boolean F,G;
                switch (choice){
                    case 1:
                        System.out.println(resF * resG);
                        break;
                    case 2:
                        System.out.println(resF * resG);
                        break;
                    case 3:
                    case 4:
                        if(resF == 1){
                            F = true;
                        }
                        else{
                            F = false;
                        }
                        if(resG == 1){
                            G = true;
                        }
                        else{
                            G = false;
                        }
                        System.out.println(F && G);
                        break;
                }
                double elapsedTime = (System.nanoTime() - startTime)/1000000000;
                System.out.println("Elapsed time (in seconds) " + elapsedTime);
                f.dispose();
            } else {
                System.out.println("Processes were terminated by user");
                double elapsedTime = (System.nanoTime() - startTime)/1000000000;
                System.out.println("Elapsed time (in seconds) " + elapsedTime);
                procF.destroy();
                procG.destroy();
                f.dispose();
            }
        }

        scanF.close();
        scanG.close();
    }
}
