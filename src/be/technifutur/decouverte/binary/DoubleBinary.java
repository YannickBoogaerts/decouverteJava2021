package be.technifutur.decouverte.binary;

public class DoubleBinary {

    public static void main(String[] args) {
        long l = Double.doubleToLongBits(0.2);
        String format = "-%64s-%n";
        System.out.printf(format,Long.toBinaryString(l).length());
        System.out.printf(format,Long.toBinaryString(Double.doubleToLongBits(0.5 )));
        System.out.printf(format,Long.toBinaryString(Double.doubleToLongBits(1 )));

        System.out.println(Double.doubleToLongBits(0.5)&0x000fffffffffffffL);
        System.out.println(Double.doubleToLongBits(1)&0x000fffffffffffffL);
        System.out.println(Double.doubleToLongBits(2)&0x000fffffffffffffL);
        System.out.println(Double.doubleToLongBits(0.2)&0x000fffffffffffffL);

    }
}
