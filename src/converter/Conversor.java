/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Guilherme
 */
public class Conversor {

    public ArrayList<pontos> converter() {

        ArrayList<pontos> p = new ArrayList<>();

        String txt = JOptionPane.showInputDialog("Digite o texto");

        System.out.println("texto:" + txt);

        byte[] bytes = txt.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            //binary.append(' ');
        }
        System.out.println("'" + txt + "' to binary: " + binary);

        p = AMI(binary);

        return p;
    }

    public ArrayList<pontos> NRZ(StringBuilder bt) {
        ArrayList<pontos> listPontos = new ArrayList<>();

        String bits = bt.toString();

        for (int x = 0; x < bits.length(); x++) {
            System.out.println("bit: " + bits.charAt(x));
            if (bits.charAt(x) == '0') {

                pontos p1 = new pontos();
                p1.setX(x);
                p1.setY(x - x);
                listPontos.add(p1);

                pontos p2 = new pontos();
                p2.setX(x + 1);
                p2.setY(x - x);
                listPontos.add(p2);
            } else {
                pontos p3 = new pontos();
                p3.setX(x);
                p3.setY(1);
                listPontos.add(p3);

                pontos p4 = new pontos();
                p4.setX(x + 1);
                p4.setY(1);
                listPontos.add(p4);
            }

        }
        return listPontos;
    }

    public ArrayList<pontos> NRZL(StringBuilder bt) {
        ArrayList<pontos> listPontos = new ArrayList<>();

        StringBuilder a = new StringBuilder();
        a.append(0);
        a.append(1);
        a.append(1);

        String bits = bt.toString();

        for (int x = 0; x < bits.length(); x++) {
            System.out.println("bit: " + bits.charAt(x));
            if (bits.charAt(x) == '0') {

                pontos p1 = new pontos();
                p1.setX(x);
                p1.setY(1);
                listPontos.add(p1);

                pontos p2 = new pontos();
                p2.setX(x + 1);
                p2.setY(1);
                listPontos.add(p2);
            } else {
                pontos p3 = new pontos();
                p3.setX(x);
                p3.setY(-1);
                listPontos.add(p3);

                pontos p4 = new pontos();
                p4.setX(x + 1);
                p4.setY(-1);
                listPontos.add(p4);
            }

        }
        return listPontos;
    }

    public ArrayList<pontos> NRZI(StringBuilder bt) {
        ArrayList<pontos> listPontos = new ArrayList<>();

        String bits = "01001110";
        //bt.toString();

        pontos p = new pontos(0, 1);
        listPontos.add(p);
        boolean bool = false;
        for (int x = 0; x < bits.length() - 1; x++) {
            System.out.println("bit posicao x: " + bits.charAt(x));
            System.out.println("bit posicao x+1: " + bits.charAt(x + 1));

            if (bits.charAt(x + 1) == '0') {
                //desce
                if (bool != false) {
                    pontos p1 = new pontos();
                    p1.setX(x + 2);
                    p1.setY(-1);
                    listPontos.add(p1);
                } else {
                    pontos p2 = new pontos();
                    p2.setX(x + 2);
                    p2.setY(1);
                    listPontos.add(p2);
                }

            } else if (bits.charAt(x + 1) == '1' && bool == false) {
                //bit 1 desce 
                pontos p3 = new pontos();
                p3.setX(x + 1);
                p3.setY(1);
                listPontos.add(p3);

                pontos p4 = new pontos();;
                p4.setX(x + 1);
                p4.setY(-1);
                listPontos.add(p4);
                bool = true;

            } else {
                //bit 1 sobe
                pontos p5 = new pontos();
                p5.setX(x + 1);
                p5.setY(-1);
                listPontos.add(p5);

                pontos p6 = new pontos();;
                p6.setX(x + 1);
                p6.setY(1);
                listPontos.add(p6);
                bool = false;
            }

        }
        return listPontos;
    }

    public ArrayList<pontos> RZ(StringBuilder bt) {
        ArrayList<pontos> listPontos = new ArrayList<>();

        String bits = "01001110";
        //bt.toString();

        pontos p = new pontos(0, 1);
        listPontos.add(p);
        boolean bool = false;
        for (int x = 0; x < bits.length() - 1; x++) {
            System.out.println("bit posicao x: " + bits.charAt(x));
            System.out.println("bit posicao x+1: " + bits.charAt(x + 1));

            if (bits.charAt(x + 1) == '0') {
                //desce
                if (bool != false) {
                    pontos p1 = new pontos();
                    p1.setX(x + 2);
                    p1.setY(-1);
                    listPontos.add(p1);
                } else {
                    pontos p2 = new pontos();
                    p2.setX(x + 2);
                    p2.setY(1);
                    listPontos.add(p2);
                }

            } else if (bits.charAt(x + 1) == '1' && bool == false) {
                //bit 1 desce 
                pontos p3 = new pontos();
                p3.setX(x + 1);
                p3.setY(1);
                listPontos.add(p3);

                pontos p4 = new pontos();;
                p4.setX(x + 1);
                p4.setY(-1);
                listPontos.add(p4);
                bool = true;

            } else {
                //bit 1 sobe
                pontos p5 = new pontos();
                p5.setX(x + 1);
                p5.setY(-1);
                listPontos.add(p5);

                pontos p6 = new pontos();;
                p6.setX(x + 1);
                p6.setY(1);
                listPontos.add(p6);
                bool = false;
            }

        }
        return listPontos;
    }//terminar

    public ArrayList<pontos> MANCHESTER(StringBuilder bt) {
        ArrayList<pontos> listPontos = new ArrayList<>();

        String bits = "010011";
        //bt.toString();

        boolean bool = false;
        for (int x = 0; x < bits.length(); x++) {
            System.out.println("bit posicao x: " + bits.charAt(x));
            //System.out.println("bit posicao x+1: " + bits.charAt(x + 1));

            if (bits.charAt(x) == '0') {

                pontos p1 = new pontos();
                p1.setX(x);
                p1.setY(1);
                listPontos.add(p1);

                pontos p2 = new pontos();
                p2.setX(x + (float) 0.5);
                p2.setY(1);
                listPontos.add(p2);

                pontos p3 = new pontos();
                p3.setX(x + (float) 0.5);
                p3.setY(-1);
                listPontos.add(p3);

                pontos p4 = new pontos();
                p4.setX((float) (x + 1));
                p4.setY(-1);
                listPontos.add(p4);

            } else {

                pontos p5 = new pontos();
                p5.setX((float) (x));
                p5.setY(-1);
                listPontos.add(p5);

                pontos p6 = new pontos();
                p6.setX((float) (x + 0.5));
                p6.setY(-1);
                listPontos.add(p6);

                pontos p7 = new pontos();
                p7.setX((float) (x + 0.5));
                p7.setY(1);
                listPontos.add(p7);

                pontos p8 = new pontos();
                p8.setX((float) (x + 1));
                p8.setY(1);
                listPontos.add(p8);
            }

        }
        return listPontos;
    }

    public ArrayList<pontos> DIFMANCHESTER(StringBuilder bt) {
        ArrayList<pontos> listPontos = new ArrayList<>();

        String bits = "010011";
        //bt.toString();

        boolean bool = false;
        for (int x = 0; x < bits.length(); x++) {
            System.out.println("bit posicao x: " + bits.charAt(x));
            //System.out.println("bit posicao x+1: " + bits.charAt(x + 1));

            if (bits.charAt(x) == '0') {

                pontos p1 = new pontos();
                p1.setX(x);
                p1.setY(1);
                listPontos.add(p1);

                pontos p2 = new pontos();
                p2.setX(x + (float) 0.5);
                p2.setY(1);
                listPontos.add(p2);

                pontos p3 = new pontos();
                p3.setX(x + (float) 0.5);
                p3.setY(-1);
                listPontos.add(p3);

                pontos p4 = new pontos();
                p4.setX((float) (x + 1));
                p4.setY(-1);
                listPontos.add(p4);

            } else {

                pontos p5 = new pontos();
                p5.setX((float) (x));
                p5.setY(-1);
                listPontos.add(p5);

                pontos p6 = new pontos();
                p6.setX((float) (x + 0.5));
                p6.setY(-1);
                listPontos.add(p6);

                pontos p7 = new pontos();
                p7.setX((float) (x + 0.5));
                p7.setY(1);
                listPontos.add(p7);

                pontos p8 = new pontos();
                p8.setX((float) (x + 1));
                p8.setY(1);
                listPontos.add(p8);
            }

        }
        return listPontos;
    } //terminar

    public ArrayList<pontos> AMI(StringBuilder bt) {
        ArrayList<pontos> listPontos = new ArrayList<>();

        String bits = "010010";
        //bt.toString();

        boolean bool = false;
        for (int x = 0; x < bits.length(); x++) {
            System.out.println("bit posicao x: " + bits.charAt(x));
            //System.out.println("bit posicao x+1: " + bits.charAt(x + 1));

            if (bits.charAt(x) == '0') {

                pontos p1 = new pontos();
                p1.setX(x);
                p1.setY(0);
                listPontos.add(p1);

                pontos p2 = new pontos();
                p2.setX(x + 1);
                p2.setY(0);
                listPontos.add(p2);

            } else if (bool == false) {
                pontos p5 = new pontos();
                p5.setX(x);
                p5.setY(1);
                listPontos.add(p5);

                pontos p6 = new pontos();
                p6.setX(x+1);
                p6.setY(1);
                listPontos.add(p6);
                bool = true;
            } else {
                pontos p7 = new pontos();
                p7.setX(x);
                p7.setY(-1);
                listPontos.add(p7);

                pontos p8 = new pontos();
                p8.setX(x+1);
                p8.setY(-1);
                listPontos.add(p8);
                bool = false;
            }

        }
        return listPontos;
    } //quase pronto
    
    public ArrayList<pontos> PSEUDOTERNARY(StringBuilder bt) {
        ArrayList<pontos> listPontos = new ArrayList<>();

        String bits = "010010";
        //bt.toString();

        boolean bool = false;
        for (int x = 0; x < bits.length(); x++) {
            System.out.println("bit posicao x: " + bits.charAt(x));
            //System.out.println("bit posicao x+1: " + bits.charAt(x + 1));

            if (bits.charAt(x) == '0') {

                pontos p1 = new pontos();
                p1.setX(x);
                p1.setY(0);
                listPontos.add(p1);

                pontos p2 = new pontos();
                p2.setX(x + 1);
                p2.setY(0);
                listPontos.add(p2);

            } else if (bool == false) {
                pontos p5 = new pontos();
                p5.setX(x);
                p5.setY(1);
                listPontos.add(p5);

                pontos p6 = new pontos();
                p6.setX(x+1);
                p6.setY(1);
                listPontos.add(p6);
                bool = true;
            } else {
                pontos p7 = new pontos();
                p7.setX(x);
                p7.setY(-1);
                listPontos.add(p7);

                pontos p8 = new pontos();
                p8.setX(x+1);
                p8.setY(-1);
                listPontos.add(p8);
                bool = false;
            }

        }
        return listPontos;
    } //terminar
    
    
}
