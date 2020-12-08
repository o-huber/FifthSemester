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


class funcIntG {
    public static void main(String[] args) throws InterruptedException {
        int x = Integer.parseInt(args[0]);
        System.out.println(IntOps.funcG(x));
    }
}

class myFuncF{
    public static void main(String[] args) throws InterruptedException {
        int x = Integer.parseInt(args[0]);
        switch(x){
            case 1:
                System.out.println(x);
            case 2:
                Thread.sleep(5000);
                System.out.println(x);
            case 3:
                System.out.println(0);
            case 4:
                Thread.sleep(5000);
                System.out.println(x);
            case 5:
                System.out.println(x);
            case 6:
                Thread.sleep(5000);
                System.out.println(x);
            default:
                System.out.println(x);
        }
        System.out.println(x);
    }
}

class myFuncG{
    public static void main(String[] args) throws InterruptedException {
        int x = Integer.parseInt(args[0]);
        switch(x){
            case 1:
                Thread.sleep(5000);
                System.out.println(x);
            case 2:
                System.out.println(x);
            case 3:
                Thread.sleep(5000);
                System.out.println(0);
            case 4:
                System.out.println(x);
            case 5:
                Thread.sleep(5000);
                System.out.println(x);
            case 6:
                System.out.println(x);
            default:
                System.out.println(x);
        }
        System.out.println(x);
    }
}

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        long startTime = System.nanoTime();
        Scanner scn = new Scanner(System.in);

        System.out.println("Enter desirable func (0 - function from library, 1 to 6 - variants of testing from task document (custom function))");
        int choice = scn.nextInt();

        String className;
        int x;

        if(choice == 0){
            className = "com.company.funcInt";
            System.out.println("Enter integer X");
            x = scn.nextInt();
        }
        else{
            className = "com.company.myFunc";
            x = choice;
        }
        File tempF = new File("D:\\tempF.txt");
        File tempG = new File("D:\\tempG.txt");

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