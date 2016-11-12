/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import java.awt.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Guilherme
 */
public class Conversor {

    public ArrayList<pontos> converter(String metodo, String bits) {

        ArrayList<pontos> listPontos = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        sb.append(bits);

//        String txt = JOptionPane.showInputDialog("Digite o texto");
//
//        System.out.println("texto:" + txt);
//
//        byte[] bytes = txt.getBytes();
//        StringBuilder binary = new StringBuilder();
//        for (byte b : bytes) {
//            int val = b;
//            for (int i = 0; i < 8; i++) {
//                binary.append((val & 128) == 0 ? 0 : 1);
//                val <<= 1;
//            }
//            //binary.append(' ');
//        }
//        System.out.println("'" + txt + "' to binary: " + binary);
//        if (metodo.equalsIgnoreCase("NRZ")) {
//            listPontos = NRZ(sb);
//        }
        switch (metodo.toString()) {
            case "NRZ":
                System.out.println("NRZ");
                listPontos = NRZ(sb);
                break;
            case "NRZ-L":
                System.out.println("NRZL");
                listPontos = NRZL(sb);
                break;
            case "NRZ-I":
                System.out.println("NRZI");
                listPontos = NRZI(sb);
                break;
            case "RZ":
                System.out.println("RZ");
                listPontos = RZ(sb);
                break;
            case "MANCHESTER":
                System.out.println("MANCHESTER");
                listPontos = MANCHESTER(sb);
                break;
            case "DIFFERENTIAL MANCHESTER":
                System.out.println("DIFFERENTIAL MANCHESTER");
                listPontos = DIFMANCHESTER(sb);
                break;
            case "AMI":
                System.out.println("AMI");
                listPontos = AMI(sb);
                break;
            case "PSEUDOTERNARY":
                System.out.println("PSEUDOTERNARY");
                listPontos = PSEUDOTERNARY(sb);
                break;
            case "4B/5B":
                System.out.println("4B/5B");
                listPontos = _4B5B(sb);
                break;
            case "2B/1Q":
                System.out.println("2B/1Q");
                listPontos = _2B1Q(sb);
                break;
            default:
                System.out.println("Esta opcão não é valida");
        }

        return listPontos;
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
    } //testar

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
    } //testar

    public ArrayList<pontos> NRZI(StringBuilder bt) {
        ArrayList<pontos> listPontos = new ArrayList<>();

        // String bits = "01001110";
        String bits = bt.toString();

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
                    System.out.println("valor de x:"+x);
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

                pontos p4 = new pontos();
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
    } //testar

    public ArrayList<pontos> RZ(StringBuilder bt) {
        ArrayList<pontos> listPontos = new ArrayList<>();

        //String bits = "011001";
        String bits = bt.toString();

        for (int x = 0; x < bits.length(); x++) {
            System.out.println("bit posicao x: " + bits.charAt(x));

            if (bits.charAt(x) == '0') {

                pontos p1 = new pontos();
                p1.setX(x);
                p1.setY(-1);
                listPontos.add(p1);

                pontos p2 = new pontos();
                p2.setX((float) (x + 0.5));
                p2.setY(-1);
                listPontos.add(p2);

                pontos p3 = new pontos();
                p3.setX((float) (x + 0.5));
                p3.setY(0);
                listPontos.add(p3);

                pontos p4 = new pontos();;
                p4.setX(x + 1);
                p4.setY(0);
                listPontos.add(p4);

            } else {

                pontos p5 = new pontos();
                p5.setX(x);
                p5.setY(1);
                listPontos.add(p5);

                pontos p6 = new pontos();;
                p6.setX((float) (x + 0.5));
                p6.setY(1);
                listPontos.add(p6);

                pontos p7 = new pontos();;
                p7.setX((float) (x + 0.5));
                p7.setY(0);
                listPontos.add(p7);

                pontos p8 = new pontos();;
                p8.setX(x + 1);
                p8.setY(0);
                listPontos.add(p8);

            }

        }//terminar
        return listPontos;
    } //testar

    public ArrayList<pontos> MANCHESTER(StringBuilder bt) {
        ArrayList<pontos> listPontos = new ArrayList<>();

        //String bits = "010011";
        String bits = bt.toString();

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
    } //testar

    public ArrayList<pontos> DIFMANCHESTER(StringBuilder bt) {
        ArrayList<pontos> listPontos = new ArrayList<>();

        //String bits = "0100110101";
        String bits = bt.toString();

        pontos p = new pontos(0, 1);
        pontos p0 = new pontos(0, -1);
        pontos p1 = new pontos((float) 0.5, -1);
        pontos p2 = new pontos((float) 0.5, 1);
        pontos p3 = new pontos(1, 1);
        listPontos.add(p);
        listPontos.add(p0);
        listPontos.add(p1);
        listPontos.add(p2);
        listPontos.add(p3);

        boolean bool = false;
        for (int x = 0; x < bits.length() - 1; x++) {
            System.out.println("bit posicao x: " + bits.charAt(x));
            System.out.println("bit posicao x+1: " + bits.charAt(x + 1));

            if (bits.charAt(x + 1) == '0') {
                if (bool == false) {
                    // sinal 0 up
                    pontos p5 = new pontos();
                    p5.setX(x + 1);
                    p5.setY(-1);
                    listPontos.add(p5);

                    pontos p6 = new pontos();
                    p6.setX((float) (x + 1.5));
                    p6.setY(-1);
                    listPontos.add(p6);

                    pontos p8 = new pontos();
                    p8.setX((float) (x + 1.5));
                    p8.setY(1);
                    listPontos.add(p8);

                    pontos p9 = new pontos();
                    p9.setX((float) (x + 2));
                    p9.setY(1);
                    listPontos.add(p9);

                } else {
                    // sinal 0 down
                    pontos p5 = new pontos();
                    p5.setX(x);
                    p5.setY(1);
                    listPontos.add(p5);

                    pontos p6 = new pontos();
                    p6.setX(x + 1);
                    p6.setY(1);
                    listPontos.add(p6);

                    pontos p8 = new pontos();
                    p8.setX((float) (x + 1.5));
                    p8.setY(1);
                    listPontos.add(p8);

                    pontos p9 = new pontos();
                    p9.setX((float) (x + 1.5));
                    p9.setY(-1);
                    listPontos.add(p9);

                    pontos p7 = new pontos();
                    p7.setX((float) (x + 2));
                    p7.setY(-1);
                    listPontos.add(p7);
                }

            } else if (bool == false) {
                //sinal 1 down
                pontos p5 = new pontos();
                p5.setX(x + 1);
                p5.setY(1);
                listPontos.add(p5);

                pontos p6 = new pontos();
                p6.setX((float) (x + 1.5));
                p6.setY(1);
                listPontos.add(p6);

                pontos p7 = new pontos();
                p7.setX((float) (x + 1.5));
                p7.setY(-1);
                listPontos.add(p7);

                pontos p8 = new pontos();
                p8.setX((float) (x + 2));
                p8.setY(-1);
                listPontos.add(p8);

                bool = true;

            } else {
                //sinal 1 up
                pontos p5 = new pontos();
                p5.setX(x + 1);
                p5.setY(-1);
                listPontos.add(p5);

                pontos p6 = new pontos();
                p6.setX((float) (x + 1.5));
                p6.setY(-1);
                listPontos.add(p6);

                pontos p7 = new pontos();
                p7.setX((float) (x + 1.5));
                p7.setY(1);
                listPontos.add(p7);

                pontos p8 = new pontos();
                p8.setX((float) (x + 2));
                p8.setY(1);
                listPontos.add(p8);

                bool = false;
            }
        }

        return listPontos;
    } //testar

    public ArrayList<pontos> AMI(StringBuilder bt) {
        ArrayList<pontos> listPontos = new ArrayList<>();

        //String bits = "010010";
        String bits = bt.toString();

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
                p6.setX(x + 1);
                p6.setY(1);
                listPontos.add(p6);
                bool = true;
            } else {
                pontos p7 = new pontos();
                p7.setX(x);
                p7.setY(-1);
                listPontos.add(p7);

                pontos p8 = new pontos();
                p8.setX(x + 1);
                p8.setY(-1);
                listPontos.add(p8);
                bool = false;
            }

        }
        return listPontos;
    } //testar

    public ArrayList<pontos> PSEUDOTERNARY(StringBuilder bt) {
        ArrayList<pontos> listPontos = new ArrayList<>();

        //String bits = "010010";
        String bits = bt.toString();

        boolean bool = false;
        for (int x = 0; x < bits.length(); x++) {
            System.out.println("bit posicao x: " + bits.charAt(x));
            //System.out.println("bit posicao x+1: " + bits.charAt(x + 1));

            if (bits.charAt(x) == '0') {
                //verifica se bool = false, se sim, cria pontos acima de 0v
                if (bool == false) {
                    pontos p1 = new pontos();
                    p1.setX(x);
                    p1.setY(1);
                    listPontos.add(p1);

                    pontos p2 = new pontos();
                    p2.setX(x + 1);
                    p2.setY(1);
                    listPontos.add(p2);
                    bool = true;

                } else {
                    //cria pontos abaixo de 0v
                    pontos p1 = new pontos();
                    p1.setX(x);
                    p1.setY(-1);
                    listPontos.add(p1);

                    pontos p2 = new pontos();
                    p2.setX(x + 1);
                    p2.setY(-1);
                    listPontos.add(p2);

                    pontos p3 = new pontos();
                    p3.setX(x + 1);
                    p3.setY(0);
                    listPontos.add(p3);
                    bool = false;
                }

            } else {
                // cria pontos 0v
                pontos p3 = new pontos();
                p3.setX(x);
                p3.setY(0);
                listPontos.add(p3);

                pontos p4 = new pontos();
                p4.setX(x + 1);
                p4.setY(0);
                listPontos.add(p4);
            }

        }
        return listPontos;
    } //testar

    public ArrayList<pontos> _4B5B(StringBuilder bt) {
        ArrayList<pontos> listPontos = new ArrayList<>();

        String bits = bt.toString();
        
        StringBuilder novaSequencia = new StringBuilder();
        
        ArrayList<String> strings = new ArrayList<String>();
        //quebra string numa sequencia de 4 bits
        int index = 0;
        while (index < bits.length()) {
            strings.add(bits.substring(index, Math.min(index + 4, bits.length())));
            index += 4;
        }
        //constroi a nova sequencia de bits
        for(int y=0;y<strings.size();y++){
            System.out.println("sequencia 4 de bits: "+strings.get(y).toString());
            
            if(strings.get(y).toString().equals("0000")){
                novaSequencia.append("11110");
            }else if(strings.get(y).toString().equals("0001")){
                novaSequencia.append("01001");
            }else if(strings.get(y).toString().equals("0010")){
                novaSequencia.append("10100");
            }else if(strings.get(y).toString().equals("0011")){
                novaSequencia.append("10101");
            }else if(strings.get(y).toString().equals("0100")){
                novaSequencia.append("01010");
            }else if(strings.get(y).toString().equals("0101")){
                novaSequencia.append("01011");
            }else if(strings.get(y).toString().equals("0110")){
                novaSequencia.append("01110");
            }else if(strings.get(y).toString().equals("0111")){
                novaSequencia.append("01111");
            }else if(strings.get(y).toString().equals("1000")){
                novaSequencia.append("10010");
            }else if(strings.get(y).toString().equals("1001")){
                novaSequencia.append("10011");
            }else if(strings.get(y).toString().equals("1010")){
                novaSequencia.append("10110");
            }else if(strings.get(y).toString().equals("1011")){
                novaSequencia.append("10111");
            }else if(strings.get(y).toString().equals("1100")){
                novaSequencia.append("11010");
            }else if(strings.get(y).toString().equals("1101")){
                novaSequencia.append("11011");
            }else if(strings.get(y).toString().equals("1110")){
                novaSequencia.append("11100");
            }else if(strings.get(y).toString().equals("1111")){
                novaSequencia.append("11101");
            }
        }
        
        
        System.out.println("novaSequencia:"+novaSequencia.toString());
        
        String bitsSequence = novaSequencia.toString();
        
        pontos p = new pontos(0, 1);
        listPontos.add(p);
        boolean bool = false;
        for (int x = 0; x < bitsSequence.length() - 1; x++) {
            System.out.println("bit posicao x: " + bitsSequence.charAt(x));
            System.out.println("bit posicao x+1: " + bitsSequence.charAt(x + 1));

            if (bitsSequence.charAt(x + 1) == '0') {
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

            } else if (bitsSequence.charAt(x + 1) == '1' && bool == false) {
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
    } //terminar

    public ArrayList<pontos> _2B1Q(StringBuilder bt) {
        ArrayList<pontos> listPontos = new ArrayList<>();

        String bits = bt.toString();
                //"0010011101";

        ArrayList<String> strings = new ArrayList<String>();
        int index = 0;
        while (index < bits.length()) {
            strings.add(bits.substring(index, Math.min(index + 2, bits.length())));
            index += 2;
        }

        //bt.toString();
        //definicção do ponto de partida
        pontos p1 = new pontos();
        p1.setX(0);
        p1.setY(3);
        listPontos.add(p1);

        pontos p2 = new pontos();
        p2.setX(1);
        p2.setY(3);
        listPontos.add(p2);

        boolean sinalPositivoNegativo = true;

        for (int x = 0; x < strings.size() - 1; x++) {
            System.out.println("bit posicao x: " + bits.charAt(x));
            //System.out.println("bit posicao x+1: " + bits.charAt(x + 1));

            if (strings.get(x).equals("00") && strings.get(x + 1).equals("00")) {

                if (sinalPositivoNegativo == true) {
                    pontos p3 = new pontos(x + 1, +1);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, +1);
                    listPontos.add(p4);
                    sinalPositivoNegativo = true;
                } else {
                    pontos p3 = new pontos(x + 1, -1);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, -1);
                    listPontos.add(p4);
                    sinalPositivoNegativo = false;
                }

            } else if (strings.get(x).equals("00") && strings.get(x + 1).equals("01")) {

                if (sinalPositivoNegativo == true) {
                    pontos p3 = new pontos(x + 1, +3);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, +3);
                    listPontos.add(p4);
                    sinalPositivoNegativo = true;
                } else {
                    pontos p3 = new pontos(x + 1, -3);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, -3);
                    listPontos.add(p4);
                    sinalPositivoNegativo = false;
                }

            } else if (strings.get(x).equals("00") && strings.get(x + 1).equals("10")) {

                if (sinalPositivoNegativo == true) {
                    pontos p3 = new pontos(x + 1, -1);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, -1);
                    listPontos.add(p4);
                    sinalPositivoNegativo = false;
                } else {
                    pontos p3 = new pontos(x + 1, +1);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, +1);
                    listPontos.add(p4);
                    sinalPositivoNegativo = true;
                }

            } else if (strings.get(x).equals("00") && strings.get(x + 1).equals("11")) {

                if (sinalPositivoNegativo == true) {
                    pontos p3 = new pontos(x + 1, -3);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, -3);
                    listPontos.add(p4);
                    sinalPositivoNegativo = false;
                } else {
                    pontos p3 = new pontos(x + 1, +3);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, +3);
                    listPontos.add(p4);
                    sinalPositivoNegativo = true;
                }
            } else if(strings.get(x).equals("01") && strings.get(x + 1).equals("00")){
                
                if (sinalPositivoNegativo == true) {
                    pontos p3 = new pontos(x + 1, +1);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, +1);
                    listPontos.add(p4);
                    sinalPositivoNegativo = true;
                } else {
                    pontos p3 = new pontos(x + 1, -1);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, -1);
                    listPontos.add(p4);
                    sinalPositivoNegativo = false;
                }
                
            } else if(strings.get(x).equals("01") && strings.get(x + 1).equals("01")){
                
                if (sinalPositivoNegativo == true) {
                    pontos p3 = new pontos(x + 1, +3);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, +3);
                    listPontos.add(p4);
                    sinalPositivoNegativo = true;
                } else {
                    pontos p3 = new pontos(x + 1, -3);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, -3);
                    listPontos.add(p4);
                    sinalPositivoNegativo = false;
                }
                
            } else if(strings.get(x).equals("01") && strings.get(x + 1).equals("10")){
                
                if (sinalPositivoNegativo == true) {
                    pontos p3 = new pontos(x + 1, -1);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, -1);
                    listPontos.add(p4);
                    sinalPositivoNegativo = false;
                } else {
                    pontos p3 = new pontos(x + 1, +1);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, +1);
                    listPontos.add(p4);
                    sinalPositivoNegativo = true;
                }
                
            } else if(strings.get(x).equals("01") && strings.get(x + 1).equals("11")){
                
                if (sinalPositivoNegativo == true) {
                    pontos p3 = new pontos(x + 1, -3);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, -3);
                    listPontos.add(p4);
                    sinalPositivoNegativo = false;
                } else {
                    pontos p3 = new pontos(x + 1, +3);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, +3);
                    listPontos.add(p4);
                    sinalPositivoNegativo = true;
                }
                
            } else if(strings.get(x).equals("10") && strings.get(x + 1).equals("00")){
                
                if (sinalPositivoNegativo == true) {
                    pontos p3 = new pontos(x + 1, +1);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, +1);
                    listPontos.add(p4);
                    sinalPositivoNegativo = true;
                } else {
                    pontos p3 = new pontos(x + 1, -1);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, -1);
                    listPontos.add(p4);
                    sinalPositivoNegativo = false;
                }
                
            } else if(strings.get(x).equals("10") && strings.get(x + 1).equals("01")){
                
                if (sinalPositivoNegativo == true) {
                    pontos p3 = new pontos(x + 1, +3);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, +3);
                    listPontos.add(p4);
                    sinalPositivoNegativo = true;
                } else {
                    pontos p3 = new pontos(x + 1, -3);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, -3);
                    listPontos.add(p4);
                    sinalPositivoNegativo = false;
                }
                
            } else if(strings.get(x).equals("10") && strings.get(x + 1).equals("10")){
                
                if (sinalPositivoNegativo == true) {
                    pontos p3 = new pontos(x + 1, -1);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, -1);
                    listPontos.add(p4);
                    sinalPositivoNegativo = false;
                } else {
                    pontos p3 = new pontos(x + 1, +1);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, +1);
                    listPontos.add(p4);
                    sinalPositivoNegativo = true;
                }
                
            } else if(strings.get(x).equals("10") && strings.get(x + 1).equals("11")){
                
                if (sinalPositivoNegativo == true) {
                    pontos p3 = new pontos(x + 1, -3);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, -3);
                    listPontos.add(p4);
                    sinalPositivoNegativo = false;
                } else {
                    pontos p3 = new pontos(x + 1, +3);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, +3);
                    listPontos.add(p4);
                    sinalPositivoNegativo = true;
                }
                
            } else if(strings.get(x).equals("11") && strings.get(x + 1).equals("00")){
                
                if (sinalPositivoNegativo == true) {
                    pontos p3 = new pontos(x + 1, +1);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, +1);
                    listPontos.add(p4);
                    sinalPositivoNegativo = true;
                } else {
                    pontos p3 = new pontos(x + 1, -1);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, -1);
                    listPontos.add(p4);
                    sinalPositivoNegativo = false;
                }
                
            } else if(strings.get(x).equals("11") && strings.get(x + 1).equals("01")){
                
                if (sinalPositivoNegativo == true) {
                    pontos p3 = new pontos(x + 1, +3);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, +3);
                    listPontos.add(p4);
                    sinalPositivoNegativo = true;
                } else {
                    pontos p3 = new pontos(x + 1, -3);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, -3);
                    listPontos.add(p4);
                    sinalPositivoNegativo = false;
                }
                
            } else if(strings.get(x).equals("11") && strings.get(x + 1).equals("10")){
                
                if (sinalPositivoNegativo == true) {
                    pontos p3 = new pontos(x + 1, -1);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, -1);
                    listPontos.add(p4);
                    sinalPositivoNegativo = false;
                } else {
                    pontos p3 = new pontos(x + 1, +1);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, +1);
                    listPontos.add(p4);
                    sinalPositivoNegativo = true;
                }
                
            } else if(strings.get(x).equals("11") && strings.get(x + 1).equals("11")){
                
                if (sinalPositivoNegativo == true) {
                    pontos p3 = new pontos(x + 1, -3);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, -3);
                    listPontos.add(p4);
                    sinalPositivoNegativo = false;
                } else {
                    pontos p3 = new pontos(x + 1, +3);
                    listPontos.add(p3);
                    pontos p4 = new pontos(x + 2, +3);
                    listPontos.add(p4);
                    sinalPositivoNegativo = true;
                }
                
            }
        }
        return listPontos;
    } //terminar
}
