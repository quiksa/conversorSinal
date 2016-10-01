/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

/**
 *
 * @author Guilherme
 */
public class Tabela2B1Q {

    private String bits, positive, negative;

    public Tabela2B1Q(String bits, String positive, String negative) {
        this.bits = bits;
        this.positive = positive;
        this.negative = negative;
    }

    public String getBits() {
        return bits;
    }

    public void setBits(String bits) {
        this.bits = bits;
    }

    public String getPositive() {
        return positive;
    }

    public void setPositive(String positive) {
        this.positive = positive;
    }

    public String getNegative() {
        return negative;
    }

    public void setNegative(String negative) {
        this.negative = negative;
    }

    Tabela2B1Q a = new Tabela2B1Q("00", "+1", "-1");
    Tabela2B1Q b = new Tabela2B1Q("01", "+3", "-3");
    Tabela2B1Q c = new Tabela2B1Q("10", "-1", "+1");
    Tabela2B1Q d = new Tabela2B1Q("11", "-3", "+3");
}
