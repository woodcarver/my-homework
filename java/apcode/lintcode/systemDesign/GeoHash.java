package lintcode.systemDesign;

public class GeoHash {
    /**
     * @param latitude, longitude a location coordinate pair 
     * @param precision an integer between 1 to 12
     * @return a base32 string
     */
    public String encode(double latitude, double longitude, int precision) {
        // 简述geohash算法：这是一个分形算法，即可用随着长度的展开，其得到的图形
        // 和原图像“一样”。其实就是类似地图上的放大缩小功能，可以无限放大，也可以
        // 无限缩小。
        // 算法关键词：二分法 + base32编码 + Peano空间填充曲线
        // 放弃byte运算，使用字符串，byte拼接base32太麻烦了
        if (latitude > 90 || latitude < -90) {
            return "";
        }
        if (longitude > 180 || longitude < -180) {
            return "";
        }
        if (precision > 12 || precision < 1) {
            return "";
        }
        
        int subLength = (precision * 5 + 1) / 2;
        String latitudeByte = getGeoByte(latitude, subLength, -90, 90);
        String longitudeByte = getGeoByte(longitude, subLength, -180, 180);
        // System.out.println("--" + latitudeByte + "," + longitudeByte);
        return toGeoBase32(latitudeByte, longitudeByte);
    }
    private String getGeoByte(double number, int length, double start, double end) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            double middle = start + (end - start) / 2;
            // System.out.println(middle);
            if (number > middle) {
                result.append("1");
                start = middle;
            } else {
                result.append("0");
                end = middle;
            }
        }
        return result.toString();
    }
    private String toGeoBase32(String latitudeByte, String longitudeByte) {
        String base32 = "0123456789bcdefghjkmnpqrstuvwxyz";
        StringBuilder hash = new StringBuilder();
        StringBuilder mergeStr = new StringBuilder();
        for (int i = 0, len = latitudeByte.length(); i < len; i++) {
            mergeStr.append(longitudeByte.charAt(i));
            mergeStr.append(latitudeByte.charAt(i));
        }
        String merge = mergeStr.toString();
        for (int j = 0, len = merge.length(); j <= len - 5; j += 5) {
            String segement = merge.substring(j, j + 5);
            int slot = Integer.parseInt(segement, 2);
            // System.out.println("##" + segement + "," + slot);
            hash.append(base32.charAt(slot));
        }
        return hash.toString();
    }
    // private String toGeoBase32(byte[] latitudeByte, byte[] longitudeByte) {
    //     String base32 = "0123456789bcdefghjkmnpqrstuvwxyz";
    //     int len = latitudeByte.length;
    //     byte[] tmp;
    //     ArrayList<Byte> mergeByte = new ArrayList<Byte>();
    //     StringBuilder hashStr = new StringBuilder();
    //     for (int i = 0; i < len; i += 2) {
    //         byte[] tmp1 = mergeTwoByte(latitudeByte[i], longitudeByte[i]);
    //         mergeByte.add(tmp1[0]);
    //         mergeByte.add(tmp1[1]);
    //         byte[] tmp2 = mergeTwoByte(latitudeByte[i + 1],
    //             longitudeByte[i + 1]);
    //         mergeByte.add(tmp2[0]);
    //         mergeByte.add(tmp2[1]);
    //     }
    //     for (int i = 0; i < len * 2; i += 5) {
    //         int slot = mergeByte.get(i * 5 / 8) >> (3 - (i * 5 % 8)) & (0xff >> 3);
    //         hashStr.append(base32.charAt(slot));
    //     }
    //     return hashStr.toString();
    // }
    // private byte[] mergeTwoByte(byte b1, byte b2) {
    //     // bit处理真的很难写
    //     byte[] result = new byte[2];
    //     for (int i = 0; i < 8; i++) {
    //         result[i / 4] |= (b1 >> (8 - i)  & 1) << (i * 2);
    //         result[i / 4] |= (b2 >> (8 - i)  & 1) << (i * 2 + 1);
    //     }
    //     return result;
    // }
}
