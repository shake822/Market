/******************************************************************************
 * Copyright (C) 2007 ShenZhen ComTop Power Automation Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、
 * 修改或发布本软件.
 *****************************************************************************/

package com.comtop.mobile.utils;

/**
 * MD5编码格式，可以把字符串	md5.getMD5ofStr(s)
 * @author 陈仁华
 * @since   JDK1.4
 * @History  2007-4-30 陈仁华 新建
 */
public class MD5Tools {
    /** 属性S11 */
    protected static final int S11 = 7;

    /** 属性S12 */
    protected static final int S12 = 12;

    /** 属性S13 */
    protected static final int S13 = 17;

    /** 属性S14 */
    protected static final int S14 = 22;

    /** 属性S21 */
    protected static final int S21 = 5;

    /** 属性S22 */
    protected static final int S22 = 9;

    /** 属性S23 */
    protected static final int S23 = 14;

    /** 属性S24 */
    protected static final int S24 = 20;

    /** 属性S31 */
    protected static final int S31 = 4;

    /** 属性S32 */
    protected static final int S32 = 11;

    /** 属性S33 */
    protected static final int S33 = 16;

    /** 属性S34 */
    protected static final int S34 = 23;

    /** 属性S41 */
    protected static final int S41 = 6;

    /** 属性S42 */
    protected static final int S42 = 10;

    /** 属性S43 */
    protected static final int S43 = 15;

    /** 属性S44 */
    protected static final int S44 = 21;

    /** 属性PADDING */
    protected static final byte PADDING[] = { -128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

    /** 属性state */
    private long[] state;

    /** 属性count */
    private long[] count;

    /** 属性buffer */
    private final byte[] buffer;

    /** 属性digestHexStr */
    public String digestHexStr;

    /** 属性digest */
    private final byte[] digest;

    public String getMD5ofStr(String s) {// 返回MD5串；
        int i;
        md5Init();
        md5Update(s.getBytes(), s.length());
        md5Final();
        digestHexStr = "";
        for (i = 0; i < 16; i++) {
            digestHexStr = digestHexStr + byteHEX(digest[i]);
        }
        return digestHexStr;
    }

    public MD5Tools() {
        state = new long[4];
        count = new long[2];
        buffer = new byte[64];
        digest = new byte[16];
        md5Init();
    }

    private void md5Init() {// 初始化部分变量；
        count[0] = 0L;
        count[1] = 0L;
        state[0] = 0x67452301L;
        state[1] = 0xefcdab89L;
        state[2] = 0x98badcfeL;
        state[3] = 0x10325476L;
    }

    private long methodF(long l, long l1, long l2) {
        return l & l1 | ~l & l2;
    }

    private long methodG(long l, long l1, long l2) {
        return l & l2 | l1 & ~l2;
    }

    private long methodH(long l, long l1, long l2) {
        return l ^ l1 ^ l2;
    }

    private long methodI(long l, long l1, long l2) {
        return l1 ^ (l | ~l2);
    }

    private long methodFF(long l, long l1, long l2, long l3, long l4, long l5, long l6) {
        l += methodF(l1, l2, l3) + l4 + l6;
        l = (int) l << (int) l5 | (int) l >>> (int) (32L - l5);
        l += l1;
        return l;
    }

    private long methodGG(long l, long l1, long l2, long l3, long l4, long l5, long l6) {
        l += methodG(l1, l2, l3) + l4 + l6;
        l = (int) l << (int) l5 | (int) l >>> (int) (32L - l5);
        l += l1;
        return l;
    }

    private long methodHH(long l, long l1, long l2, long l3, long l4, long l5, long l6) {
        l += methodH(l1, l2, l3) + l4 + l6;
        l = (int) l << (int) l5 | (int) l >>> (int) (32L - l5);
        l += l1;
        return l;
    }

    private long methodII(long l, long l1, long l2, long l3, long l4, long l5, long l6) {
        l += methodI(l1, l2, l3) + l4 + l6;
        l = (int) l << (int) l5 | (int) l >>> (int) (32L - l5);
        l += l1;
        return l;
    }

    private void md5Update(byte[] abyte0, int i) {// 补位操作，abyte0为需要进行MD5加密的字符串，i为字符串长度；
        byte abyte1[] = new byte[64];
        int k = (int) (count[0] >>> 3) & 0x3f;
        int iI = i << 3;
        count[0] += iI;
        if (count[0] < (long) iI) {
            count[1]++;
        }
        count[1] += i >>> 29;
        int l = 64 - k;
        int j;
        if (i >= l) {
            md5Memcpy(buffer, abyte0, k, 0, l);
            md5Transform(buffer);
            for (j = l; j + 63 < i; j += 64) {
                md5Memcpy(abyte1, abyte0, 0, j, 64);
                md5Transform(abyte1);
            }
            k = 0;
        } else {
            j = 0;
        }
        md5Memcpy(buffer, abyte0, k, j, i - j);
    }

    private void md5Final() {// 最终处理，将得到的128位（16字节）MD5码存放在digest数组中
        byte abyte0[] = new byte[8];
        methodEncode(abyte0, count, 8);
        int i = (int) (count[0] >>> 3) & 0x3f;
        int j = i >= 56 ? 120 - i : 56 - i;
        md5Update(PADDING, j);
        md5Update(abyte0, 8);
        methodEncode(digest, state, 16);
    }

    private void md5Memcpy(byte[] abyte0, byte[] abyte1, int i, int j, int k) {
        for (int l = 0; l < k; l++) {
            abyte0[i + l] = abyte1[j + l];
        }
    }

    private void md5Transform(byte[] abyte0) {
        long l = state[0];
        long l1 = state[1];
        long l2 = state[2];
        long l3 = state[3];
        long al[] = new long[16];
        methodDecode(al, abyte0, 64);
        l = methodFF(l, l1, l2, l3, al[0], 7L, 0xd76aa478L);
        l3 = methodFF(l3, l, l1, l2, al[1], 12L, 0xe8c7b756L);
        l2 = methodFF(l2, l3, l, l1, al[2], 17L, 0x242070dbL);
        l1 = methodFF(l1, l2, l3, l, al[3], 22L, 0xc1bdceeeL);
        l = methodFF(l, l1, l2, l3, al[4], 7L, 0xf57c0fafL);
        l3 = methodFF(l3, l, l1, l2, al[5], 12L, 0x4787c62aL);
        l2 = methodFF(l2, l3, l, l1, al[6], 17L, 0xa8304613L);
        l1 = methodFF(l1, l2, l3, l, al[7], 22L, 0xfd469501L);
        l = methodFF(l, l1, l2, l3, al[8], 7L, 0x698098d8L);
        l3 = methodFF(l3, l, l1, l2, al[9], 12L, 0x8b44f7afL);
        l2 = methodFF(l2, l3, l, l1, al[10], 17L, 0xffff5bb1L);
        l1 = methodFF(l1, l2, l3, l, al[11], 22L, 0x895cd7beL);
        l = methodFF(l, l1, l2, l3, al[12], 7L, 0x6b901122L);
        l3 = methodFF(l3, l, l1, l2, al[13], 12L, 0xfd987193L);
        l2 = methodFF(l2, l3, l, l1, al[14], 17L, 0xa679438eL);
        l1 = methodFF(l1, l2, l3, l, al[15], 22L, 0x49b40821L);
        l = methodGG(l, l1, l2, l3, al[1], 5L, 0xf61e2562L);
        l3 = methodGG(l3, l, l1, l2, al[6], 9L, 0xc040b340L);
        l2 = methodGG(l2, l3, l, l1, al[11], 14L, 0x265e5a51L);
        l1 = methodGG(l1, l2, l3, l, al[0], 20L, 0xe9b6c7aaL);
        l = methodGG(l, l1, l2, l3, al[5], 5L, 0xd62f105dL);
        l3 = methodGG(l3, l, l1, l2, al[10], 9L, 0x2441453L);
        l2 = methodGG(l2, l3, l, l1, al[15], 14L, 0xd8a1e681L);
        l1 = methodGG(l1, l2, l3, l, al[4], 20L, 0xe7d3fbc8L);
        l = methodGG(l, l1, l2, l3, al[9], 5L, 0x21e1cde6L);
        l3 = methodGG(l3, l, l1, l2, al[14], 9L, 0xc33707d6L);
        l2 = methodGG(l2, l3, l, l1, al[3], 14L, 0xf4d50d87L);
        l1 = methodGG(l1, l2, l3, l, al[8], 20L, 0x455a14edL);
        l = methodGG(l, l1, l2, l3, al[13], 5L, 0xa9e3e905L);
        l3 = methodGG(l3, l, l1, l2, al[2], 9L, 0xfcefa3f8L);
        l2 = methodGG(l2, l3, l, l1, al[7], 14L, 0x676f02d9L);
        l1 = methodGG(l1, l2, l3, l, al[12], 20L, 0x8d2a4c8aL);
        l = methodHH(l, l1, l2, l3, al[5], 4L, 0xfffa3942L);
        l3 = methodHH(l3, l, l1, l2, al[8], 11L, 0x8771f681L);
        l2 = methodHH(l2, l3, l, l1, al[11], 16L, 0x6d9d6122L);
        l1 = methodHH(l1, l2, l3, l, al[14], 23L, 0xfde5380cL);
        l = methodHH(l, l1, l2, l3, al[1], 4L, 0xa4beea44L);
        l3 = methodHH(l3, l, l1, l2, al[4], 11L, 0x4bdecfa9L);
        l2 = methodHH(l2, l3, l, l1, al[7], 16L, 0xf6bb4b60L);
        l1 = methodHH(l1, l2, l3, l, al[10], 23L, 0xbebfbc70L);
        l = methodHH(l, l1, l2, l3, al[13], 4L, 0x289b7ec6L);
        l3 = methodHH(l3, l, l1, l2, al[0], 11L, 0xeaa127faL);
        l2 = methodHH(l2, l3, l, l1, al[3], 16L, 0xd4ef3085L);
        l1 = methodHH(l1, l2, l3, l, al[6], 23L, 0x4881d05L);
        l = methodHH(l, l1, l2, l3, al[9], 4L, 0xd9d4d039L);
        l3 = methodHH(l3, l, l1, l2, al[12], 11L, 0xe6db99e5L);
        l2 = methodHH(l2, l3, l, l1, al[15], 16L, 0x1fa27cf8L);
        l1 = methodHH(l1, l2, l3, l, al[2], 23L, 0xc4ac5665L);
        l = methodII(l, l1, l2, l3, al[0], 6L, 0xf4292244L);
        l3 = methodII(l3, l, l1, l2, al[7], 10L, 0x432aff97L);
        l2 = methodII(l2, l3, l, l1, al[14], 15L, 0xab9423a7L);
        l1 = methodII(l1, l2, l3, l, al[5], 21L, 0xfc93a039L);
        l = methodII(l, l1, l2, l3, al[12], 6L, 0x655b59c3L);
        l3 = methodII(l3, l, l1, l2, al[3], 10L, 0x8f0ccc92L);
        l2 = methodII(l2, l3, l, l1, al[10], 15L, 0xffeff47dL);
        l1 = methodII(l1, l2, l3, l, al[1], 21L, 0x85845dd1L);
        l = methodII(l, l1, l2, l3, al[8], 6L, 0x6fa87e4fL);
        l3 = methodII(l3, l, l1, l2, al[15], 10L, 0xfe2ce6e0L);
        l2 = methodII(l2, l3, l, l1, al[6], 15L, 0xa3014314L);
        l1 = methodII(l1, l2, l3, l, al[13], 21L, 0x4e0811a1L);
        l = methodII(l, l1, l2, l3, al[4], 6L, 0xf7537e82L);
        l3 = methodII(l3, l, l1, l2, al[11], 10L, 0xbd3af235L);
        l2 = methodII(l2, l3, l, l1, al[2], 15L, 0x2ad7d2bbL);
        l1 = methodII(l1, l2, l3, l, al[9], 21L, 0xeb86d391L);
        state[0] += l;
        state[1] += l1;
        state[2] += l2;
        state[3] += l3;
    }

    private void methodEncode(byte[] abyte0, long[] al, int i) {// 转换函数，将al中long型的变量输出到byte型的数组abyte0中，
        // 低位字节在前，高位字节在后；
        int j = 0;
        for (int k = 0; k < i; k += 4) {
            abyte0[k] = (byte) (int) (al[j] & 255L);
            abyte0[k + 1] = (byte) (int) (al[j] >>> 8 & 255L);
            abyte0[k + 2] = (byte) (int) (al[j] >>> 16 & 255L);
            abyte0[k + 3] = (byte) (int) (al[j] >>> 24 & 255L);
            j++;
        }
    }

    private void methodDecode(long[] al, byte[] abyte0, int i) {
        int j = 0;
        for (int k = 0; k < i; k += 4) {
            al[j] = b2iu(abyte0[k]) | b2iu(abyte0[k + 1]) << 8 | b2iu(abyte0[k + 2]) << 16
                    | b2iu(abyte0[k + 3]) << 24;
            j++;
        }
    }

    public static long b2iu(byte byte0) {
        return byte0 >= 0 ? byte0 : byte0 & 0xff;
    }

    public static String byteHEX(byte byte0) {// 字节到十六进制的ASCII码转换
        char ac[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
                'F' };
        char ac1[] = new char[2];
        ac1[0] = ac[byte0 >>> 4 & 0xf];
        ac1[1] = ac[byte0 & 0xf];
        String s = new String(ac1);
        return s;
    }

    public static String toMD5(String s) {
        MD5Tools md5 = new MD5Tools();
        return md5.getMD5ofStr(s);
    }

}
