package lintcode.systemDesign;

import java.util.HashMap;
/*
 * This is a followup question for Geohash.

Convert a Geohash string to latitude and longitude.
Example
Given "wx4g0s", return lat = 39.92706299 and lng = 116.39465332.

Return double[2], first double is latitude and second double is longitude.
 * 
 * 
 * 39.92706299, 116.39465332
 */
public class GeoHashII {
    public double[] decode(String geohash) {
        if (geohash == null || geohash.length() == 0) {
            return new double[2];
        }
        // base32 to binary
        String binaryStr = base32ToBinary(geohash);
        System.out.println("binrayStr: " + binaryStr);
        StringBuilder latBinaryStr = new StringBuilder();
        StringBuilder longBinaryStr = new StringBuilder();
        int binaryLen = binaryStr.length();
        for (int i = 0; i < binaryLen - 1; i += 2) {
            longBinaryStr.append(binaryStr.charAt(i));
            latBinaryStr.append(binaryStr.charAt(i + 1));
        }
        // 遗漏了i是奇数的情况
        if (binaryLen % 2 == 1) {
            longBinaryStr.append(binaryStr.charAt(binaryLen - 1));
        }
        double[] latLong = new double[2];
        latLong[0] = binToDouble(latBinaryStr.toString(), -90, 90);
        latLong[1] = binToDouble(longBinaryStr.toString(), -180, 180);
        return latLong;
    }
    private String base32ToBinary(String geohash) {
        String base32 = "0123456789bcdefghjkmnpqrstuvwxyz";
        HashMap<Character, String> base32Tobin = new HashMap<Character, String>();
        for (int i = 0, len = base32.length(); i < len; i++) {
            System.out.println("$$" + i + "--" + Integer.toBinaryString(i | 0x20).substring(1, 6));
            base32Tobin.put(base32.charAt(i),
                Integer.toBinaryString(i | 0x20).substring(1, 6));
        }
        StringBuilder binaryString = new StringBuilder();
        for (int j = 0, len = geohash.length(); j < len; j++) {
            binaryString.append(base32Tobin.get(geohash.charAt(j)));
        }
        return binaryString.toString();
    }
    private double binToDouble(String binaryString, double start, double end) {
        for (int i = 0, len = binaryString.length(); i < len; i++) {
            double middle = start + (end - start) / 2;
            if (binaryString.charAt(i) == '1') {
                start = middle;
            } else if (binaryString.charAt(i) == '0') {
                end = middle;
            }
        }
        return start + (end - start) / 2;
    }
    public static void main(String[] args) {
    	GeoHashII geohash = new GeoHashII();
    	double[] result = geohash.decode("wx4g0s");
    	System.out.println(result[0] + "," + result[1]);
    }
}
