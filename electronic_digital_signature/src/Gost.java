import java.util.Arrays;

public class Gost {


    // Матрица A для функции перемешивания (MixColumns, или L-функции)
    private long[] A = {
            0x8e20faa72ba0b470L, 0x47107ddd9b505a38L, 0xad08b0e0c3282d1cL, 0xd8045870ef14980eL,
            0x6c022c38f90a4c07L, 0x3601161cf205268dL, 0x1b8e0b0e798c13c8L, 0x83478b07b2468764L,
            0xa011d380818e8f40L, 0x5086e740ce47c920L, 0x2843fd2067adea10L, 0x14aff010bdd87508L,
            0x0ad97808d06cb404L, 0x05e23c0468365a02L, 0x8c711e02341b2d01L, 0x46b60f011a83988eL,
            0x90dab52a387ae76fL, 0x486dd4151c3dfdb9L, 0x24b86a840e90f0d2L, 0x125c354207487869L,
            0x092e94218d243cbaL, 0x8a174a9ec8121e5dL, 0x4585254f64090fa0L, 0xaccc9ca9328a8950L,
            0x9d4df05d5f661451L, 0xc0a878a0a1330aa6L, 0x60543c50de970553L, 0x302a1e286fc58ca7L,
            0x18150f14b9ec46ddL, 0x0c84890ad27623e0L, 0x0642ca05693b9f70L, 0x0321658cba93c138L,
            0x86275df09ce8aaa8L, 0x439da0784e745554L, 0xafc0503c273aa42aL, 0xd960281e9d1d5215L,
            0xe230140fc0802984L, 0x71180a8960409a42L, 0xb60c05ca30204d21L, 0x5b068c651810a89eL,
            0x456c34887a3805b9L, 0xac361a443d1c8cd2L, 0x561b0d22900e4669L, 0x2b838811480723baL,
            0x9bcf4486248d9f5dL, 0xc3e9224312c8c1a0L, 0xeffa11af0964ee50L, 0xf97d86d98a327728L,
            0xe4fa2054a80b329cL, 0x727d102a548b194eL, 0x39b008152acb8227L, 0x9258048415eb419dL,
            0x492c024284fbaec0L, 0xaa16012142f35760L, 0x550b8e9e21f7a530L, 0xa48b474f9ef5dc18L,
            0x70a6a56e2440598eL, 0x3853dc371220a247L, 0x1ca76e95091051adL, 0x0edd37c48a08a6d8L,
            0x07e095624504536cL, 0x8d70c431ac02a736L, 0xc83862965601dd1bL, 0x641c314b2b8ee083L
    };

    // Таблица замены для функции SubBytes
    private byte[] SBOX = {
            (byte) 0xFC, (byte) 0xEE, (byte) 0xDD, (byte) 0x11, (byte) 0xCF, (byte) 0x6E, (byte) 0x31, (byte) 0x16,
            (byte) 0xFB, (byte) 0xC4, (byte) 0xFA, (byte) 0xDA, (byte) 0x23, (byte) 0xC5, (byte) 0x04, (byte) 0x4D,
            (byte) 0xE9, (byte) 0x77, (byte) 0xF0, (byte) 0xDB, (byte) 0x93, (byte) 0x2E, (byte) 0x99, (byte) 0xBA,
            (byte) 0x17, (byte) 0x36, (byte) 0xF1, (byte) 0xBB, (byte) 0x14, (byte) 0xCD, (byte) 0x5F, (byte) 0xC1,
            (byte) 0xF9, (byte) 0x18, (byte) 0x65, (byte) 0x5A, (byte) 0xE2, (byte) 0x5C, (byte) 0xEF, (byte) 0x21,
            (byte) 0x81, (byte) 0x1C, (byte) 0x3C, (byte) 0x42, (byte) 0x8B, (byte) 0x01, (byte) 0x8E, (byte) 0x4F,
            (byte) 0x05, (byte) 0x84, (byte) 0x02, (byte) 0xAE, (byte) 0xE3, (byte) 0x6A, (byte) 0x8F, (byte) 0xA0,
            (byte) 0x06, (byte) 0x0B, (byte) 0xED, (byte) 0x98, (byte) 0x7F, (byte) 0xD4, (byte) 0xD3, (byte) 0x1F,
            (byte) 0xEB, (byte) 0x34, (byte) 0x2C, (byte) 0x51, (byte) 0xEA, (byte) 0xC8, (byte) 0x48, (byte) 0xAB,
            (byte) 0xF2, (byte) 0x2A, (byte) 0x68, (byte) 0xA2, (byte) 0xFD, (byte) 0x3A, (byte) 0xCE, (byte) 0xCC,
            (byte) 0xB5, (byte) 0x70, (byte) 0x0E, (byte) 0x56, (byte) 0x08, (byte) 0x0C, (byte) 0x76, (byte) 0x12,
            (byte) 0xBF, (byte) 0x72, (byte) 0x13, (byte) 0x47, (byte) 0x9C, (byte) 0xB7, (byte) 0x5D, (byte) 0x87,
            (byte) 0x15, (byte) 0xA1, (byte) 0x96, (byte) 0x29, (byte) 0x10, (byte) 0x7B, (byte) 0x9A, (byte) 0xC7,
            (byte) 0xF3, (byte) 0x91, (byte) 0x78, (byte) 0x6F, (byte) 0x9D, (byte) 0x9E, (byte) 0xB2, (byte) 0xB1,
            (byte) 0x32, (byte) 0x75, (byte) 0x19, (byte) 0x3D, (byte) 0xFF, (byte) 0x35, (byte) 0x8A, (byte) 0x7E,
            (byte) 0x6D, (byte) 0x54, (byte) 0xC6, (byte) 0x80, (byte) 0xC3, (byte) 0xBD, (byte) 0x0D, (byte) 0x57,
            (byte) 0xDF, (byte) 0xF5, (byte) 0x24, (byte) 0xA9, (byte) 0x3E, (byte) 0xA8, (byte) 0x43, (byte) 0xC9,
            (byte) 0xD7, (byte) 0x79, (byte) 0xD6, (byte) 0xF6, (byte) 0x7C, (byte) 0x22, (byte) 0xB9, (byte) 0x03,
            (byte) 0xE0, (byte) 0x0F, (byte) 0xEC, (byte) 0xDE, (byte) 0x7A, (byte) 0x94, (byte) 0xB0, (byte) 0xBC,
            (byte) 0xDC, (byte) 0xE8, (byte) 0x28, (byte) 0x50, (byte) 0x4E, (byte) 0x33, (byte) 0x0A, (byte) 0x4A,
            (byte) 0xA7, (byte) 0x97, (byte) 0x60, (byte) 0x73, (byte) 0x1E, (byte) 0x00, (byte) 0x62, (byte) 0x44,
            (byte) 0x1A, (byte) 0xB8, (byte) 0x38, (byte) 0x82, (byte) 0x64, (byte) 0x9F, (byte) 0x26, (byte) 0x41,
            (byte) 0xAD, (byte) 0x45, (byte) 0x46, (byte) 0x92, (byte) 0x27, (byte) 0x5E, (byte) 0x55, (byte) 0x2F,
            (byte) 0x8C, (byte) 0xA3, (byte) 0xA5, (byte) 0x7D, (byte) 0x69, (byte) 0xD5, (byte) 0x95, (byte) 0x3B,
            (byte) 0x07, (byte) 0x58, (byte) 0xB3, (byte) 0x40, (byte) 0x86, (byte) 0xAC, (byte) 0x1D, (byte) 0xF7,
            (byte) 0x30, (byte) 0x37, (byte) 0x6B, (byte) 0xE4, (byte) 0x88, (byte) 0xD9, (byte) 0xE7, (byte) 0x89,
            (byte) 0xE1, (byte) 0x1B, (byte) 0x83, (byte) 0x49, (byte) 0x4C, (byte) 0x3F, (byte) 0xF8, (byte) 0xFE,
            (byte) 0x8D, (byte) 0x53, (byte) 0xAA, (byte) 0x90, (byte) 0xCA, (byte) 0xD8, (byte) 0x85, (byte) 0x61,
            (byte) 0x20, (byte) 0x71, (byte) 0x67, (byte) 0xA4, (byte) 0x2D, (byte) 0x2B, (byte) 0x09, (byte) 0x5B,
            (byte) 0xCB, (byte) 0x9B, (byte) 0x25, (byte) 0xD0, (byte) 0xBE, (byte) 0xE5, (byte) 0x6C, (byte) 0x52,
            (byte) 0x59, (byte) 0xA6, (byte) 0x74, (byte) 0xD2, (byte) 0xE6, (byte) 0xF4, (byte) 0xB4, (byte) 0xC0,
            (byte) 0xD1, (byte) 0x66, (byte) 0xAF, (byte) 0xC2, (byte) 0x39, (byte) 0x4B, (byte) 0x63, (byte) 0xB6
    };

    // Замена функции транспонирования (P)
    private char[] TAU={
            0, 8, 16, 24, 32, 40, 48, 56,
            1, 9, 17, 25, 33, 41, 49, 57,
            2, 10, 18, 26, 34, 42, 50, 58,
            3, 11, 19, 27, 35, 43, 51, 59,
            4, 12, 20, 28, 36, 44, 52, 60,
            5, 13, 21, 29, 37, 45, 53, 61,
            6, 14, 22, 30, 38, 46, 54, 62,
            7, 15, 23, 31, 39, 47, 55, 63
    };
    // Постоянные значения для функции KeySchedule
    private final static byte[][] C = {{
            (byte)0xb1, (byte)0x08, (byte)0x5b, (byte)0xda, (byte)0x1e, (byte)0xca, (byte)0xda, (byte)0xe9,
            (byte)0xeb, (byte)0xcb, (byte)0x2f, (byte)0x81, (byte)0xc0, (byte)0x65, (byte)0x7c, (byte)0x1f,
            (byte)0x2f, (byte)0x6a, (byte)0x76, (byte)0x43, (byte)0x2e, (byte)0x45, (byte)0xd0, (byte)0x16,
            (byte)0x71, (byte)0x4e, (byte)0xb8, (byte)0x8d, (byte)0x75, (byte)0x85, (byte)0xc4, (byte)0xfc,
            (byte)0x4b, (byte)0x7c, (byte)0xe0, (byte)0x91, (byte)0x92, (byte)0x67, (byte)0x69, (byte)0x01,
            (byte)0xa2, (byte)0x42, (byte)0x2a, (byte)0x08, (byte)0xa4, (byte)0x60, (byte)0xd3, (byte)0x15,
            (byte)0x05, (byte)0x76, (byte)0x74, (byte)0x36, (byte)0xcc, (byte)0x74, (byte)0x4d, (byte)0x23,
            (byte)0xdd, (byte)0x80, (byte)0x65, (byte)0x59, (byte)0xf2, (byte)0xa6, (byte)0x45, (byte)0x07},
            {
                    (byte)0x6f, (byte)0xa3, (byte)0xb5, (byte)0x8a, (byte)0xa9, (byte)0x9d, (byte)0x2f, (byte)0x1a,
                    (byte)0x4f, (byte)0xe3, (byte)0x9d, (byte)0x46, (byte)0x0f, (byte)0x70, (byte)0xb5, (byte)0xd7,
                    (byte)0xf3, (byte)0xfe, (byte)0xea, (byte)0x72, (byte)0x0a, (byte)0x23, (byte)0x2b, (byte)0x98,
                    (byte)0x61, (byte)0xd5, (byte)0x5e, (byte)0x0f, (byte)0x16, (byte)0xb5, (byte)0x01, (byte)0x31,
                    (byte)0x9a, (byte)0xb5, (byte)0x17, (byte)0x6b, (byte)0x12, (byte)0xd6, (byte)0x99, (byte)0x58,
                    (byte)0x5c, (byte)0xb5, (byte)0x61, (byte)0xc2, (byte)0xdb, (byte)0x0a, (byte)0xa7, (byte)0xca,
                    (byte)0x55, (byte)0xdd, (byte)0xa2, (byte)0x1b, (byte)0xd7, (byte)0xcb, (byte)0xcd, (byte)0x56,
                    (byte)0xe6, (byte)0x79, (byte)0x04, (byte)0x70, (byte)0x21, (byte)0xb1, (byte)0x9b, (byte)0xb7},
            {
                    (byte)0xf5, (byte)0x74, (byte)0xdc, (byte)0xac, (byte)0x2b, (byte)0xce, (byte)0x2f, (byte)0xc7,
                    (byte)0x0a, (byte)0x39, (byte)0xfc, (byte)0x28, (byte)0x6a, (byte)0x3d, (byte)0x84, (byte)0x35,
                    (byte)0x06, (byte)0xf1, (byte)0x5e, (byte)0x5f, (byte)0x52, (byte)0x9c, (byte)0x1f, (byte)0x8b,
                    (byte)0xf2, (byte)0xea, (byte)0x75, (byte)0x14, (byte)0xb1, (byte)0x29, (byte)0x7b, (byte)0x7b,
                    (byte)0xd3, (byte)0xe2, (byte)0x0f, (byte)0xe4, (byte)0x90, (byte)0x35, (byte)0x9e, (byte)0xb1,
                    (byte)0xc1, (byte)0xc9, (byte)0x3a, (byte)0x37, (byte)0x60, (byte)0x62, (byte)0xdb, (byte)0x09,
                    (byte)0xc2, (byte)0xb6, (byte)0xf4, (byte)0x43, (byte)0x86, (byte)0x7a, (byte)0xdb, (byte)0x31,
                    (byte)0x99, (byte)0x1e, (byte)0x96, (byte)0xf5, (byte)0x0a, (byte)0xba, (byte)0x0a, (byte)0xb2},
            {
                    (byte)0xef, (byte)0x1f, (byte)0xdf, (byte)0xb3, (byte)0xe8, (byte)0x15, (byte)0x66, (byte)0xd2,
                    (byte)0xf9, (byte)0x48, (byte)0xe1, (byte)0xa0, (byte)0x5d, (byte)0x71, (byte)0xe4, (byte)0xdd,
                    (byte)0x48, (byte)0x8e, (byte)0x85, (byte)0x7e, (byte)0x33, (byte)0x5c, (byte)0x3c, (byte)0x7d,
                    (byte)0x9d, (byte)0x72, (byte)0x1c, (byte)0xad, (byte)0x68, (byte)0x5e, (byte)0x35, (byte)0x3f,
                    (byte)0xa9, (byte)0xd7, (byte)0x2c, (byte)0x82, (byte)0xed, (byte)0x03, (byte)0xd6, (byte)0x75,
                    (byte)0xd8, (byte)0xb7, (byte)0x13, (byte)0x33, (byte)0x93, (byte)0x52, (byte)0x03, (byte)0xbe,
                    (byte)0x34, (byte)0x53, (byte)0xea, (byte)0xa1, (byte)0x93, (byte)0xe8, (byte)0x37, (byte)0xf1,
                    (byte)0x22, (byte)0x0c, (byte)0xbe, (byte)0xbc, (byte)0x84, (byte)0xe3, (byte)0xd1, (byte)0x2e},
            {
                    (byte)0x4b, (byte)0xea, (byte)0x6b, (byte)0xac, (byte)0xad, (byte)0x47, (byte)0x47, (byte)0x99,
                    (byte)0x9a, (byte)0x3f, (byte)0x41, (byte)0x0c, (byte)0x6c, (byte)0xa9, (byte)0x23, (byte)0x63,
                    (byte)0x7f, (byte)0x15, (byte)0x1c, (byte)0x1f, (byte)0x16, (byte)0x86, (byte)0x10, (byte)0x4a,
                    (byte)0x35, (byte)0x9e, (byte)0x35, (byte)0xd7, (byte)0x80, (byte)0x0f, (byte)0xff, (byte)0xbd,
                    (byte)0xbf, (byte)0xcd, (byte)0x17, (byte)0x47, (byte)0x25, (byte)0x3a, (byte)0xf5, (byte)0xa3,
                    (byte)0xdf, (byte)0xff, (byte)0x00, (byte)0xb7, (byte)0x23, (byte)0x27, (byte)0x1a, (byte)0x16,
                    (byte)0x7a, (byte)0x56, (byte)0xa2, (byte)0x7e, (byte)0xa9, (byte)0xea, (byte)0x63, (byte)0xf5,
                    (byte)0x60, (byte)0x17, (byte)0x58, (byte)0xfd, (byte)0x7c, (byte)0x6c, (byte)0xfe, (byte)0x57},
            {
                    (byte)0xae, (byte)0x4f, (byte)0xae, (byte)0xae, (byte)0x1d, (byte)0x3a, (byte)0xd3, (byte)0xd9,
                    (byte)0x6f, (byte)0xa4, (byte)0xc3, (byte)0x3b, (byte)0x7a, (byte)0x30, (byte)0x39, (byte)0xc0,
                    (byte)0x2d, (byte)0x66, (byte)0xc4, (byte)0xf9, (byte)0x51, (byte)0x42, (byte)0xa4, (byte)0x6c,
                    (byte)0x18, (byte)0x7f, (byte)0x9a, (byte)0xb4, (byte)0x9a, (byte)0xf0, (byte)0x8e, (byte)0xc6,
                    (byte)0xcf, (byte)0xfa, (byte)0xa6, (byte)0xb7, (byte)0x1c, (byte)0x9a, (byte)0xb7, (byte)0xb4,
                    (byte)0x0a, (byte)0xf2, (byte)0x1f, (byte)0x66, (byte)0xc2, (byte)0xbe, (byte)0xc6, (byte)0xb6,
                    (byte)0xbf, (byte)0x71, (byte)0xc5, (byte)0x72, (byte)0x36, (byte)0x90, (byte)0x4f, (byte)0x35,
                    (byte)0xfa, (byte)0x68, (byte)0x40, (byte)0x7a, (byte)0x46, (byte)0x64, (byte)0x7d, (byte)0x6e},
            {
                    (byte)0xf4, (byte)0xc7, (byte)0x0e, (byte)0x16, (byte)0xee, (byte)0xaa, (byte)0xc5, (byte)0xec,
                    (byte)0x51, (byte)0xac, (byte)0x86, (byte)0xfe, (byte)0xbf, (byte)0x24, (byte)0x09, (byte)0x54,
                    (byte)0x39, (byte)0x9e, (byte)0xc6, (byte)0xc7, (byte)0xe6, (byte)0xbf, (byte)0x87, (byte)0xc9,
                    (byte)0xd3, (byte)0x47, (byte)0x3e, (byte)0x33, (byte)0x19, (byte)0x7a, (byte)0x93, (byte)0xc9,
                    (byte)0x09, (byte)0x92, (byte)0xab, (byte)0xc5, (byte)0x2d, (byte)0x82, (byte)0x2c, (byte)0x37,
                    (byte)0x06, (byte)0x47, (byte)0x69, (byte)0x83, (byte)0x28, (byte)0x4a, (byte)0x05, (byte)0x04,
                    (byte)0x35, (byte)0x17, (byte)0x45, (byte)0x4c, (byte)0xa2, (byte)0x3c, (byte)0x4a, (byte)0xf3,
                    (byte)0x88, (byte)0x86, (byte)0x56, (byte)0x4d, (byte)0x3a, (byte)0x14, (byte)0xd4, (byte)0x93},
            {
                    (byte)0x9b, (byte)0x1f, (byte)0x5b, (byte)0x42, (byte)0x4d, (byte)0x93, (byte)0xc9, (byte)0xa7,
                    (byte)0x03, (byte)0xe7, (byte)0xaa, (byte)0x02, (byte)0x0c, (byte)0x6e, (byte)0x41, (byte)0x41,
                    (byte)0x4e, (byte)0xb7, (byte)0xf8, (byte)0x71, (byte)0x9c, (byte)0x36, (byte)0xde, (byte)0x1e,
                    (byte)0x89, (byte)0xb4, (byte)0x44, (byte)0x3b, (byte)0x4d, (byte)0xdb, (byte)0xc4, (byte)0x9a,
                    (byte)0xf4, (byte)0x89, (byte)0x2b, (byte)0xcb, (byte)0x92, (byte)0x9b, (byte)0x06, (byte)0x90,
                    (byte)0x69, (byte)0xd1, (byte)0x8d, (byte)0x2b, (byte)0xd1, (byte)0xa5, (byte)0xc4, (byte)0x2f,
                    (byte)0x36, (byte)0xac, (byte)0xc2, (byte)0x35, (byte)0x59, (byte)0x51, (byte)0xa8, (byte)0xd9,
                    (byte)0xa4, (byte)0x7f, (byte)0x0d, (byte)0xd4, (byte)0xbf, (byte)0x02, (byte)0xe7, (byte)0x1e},
            {
                    (byte)0x37, (byte)0x8f, (byte)0x5a, (byte)0x54, (byte)0x16, (byte)0x31, (byte)0x22, (byte)0x9b,
                    (byte)0x94, (byte)0x4c, (byte)0x9a, (byte)0xd8, (byte)0xec, (byte)0x16, (byte)0x5f, (byte)0xde,
                    (byte)0x3a, (byte)0x7d, (byte)0x3a, (byte)0x1b, (byte)0x25, (byte)0x89, (byte)0x42, (byte)0x24,
                    (byte)0x3c, (byte)0xd9, (byte)0x55, (byte)0xb7, (byte)0xe0, (byte)0x0d, (byte)0x09, (byte)0x84,
                    (byte)0x80, (byte)0x0a, (byte)0x44, (byte)0x0b, (byte)0xdb, (byte)0xb2, (byte)0xce, (byte)0xb1,
                    (byte)0x7b, (byte)0x2b, (byte)0x8a, (byte)0x9a, (byte)0xa6, (byte)0x07, (byte)0x9c, (byte)0x54,
                    (byte)0x0e, (byte)0x38, (byte)0xdc, (byte)0x92, (byte)0xcb, (byte)0x1f, (byte)0x2a, (byte)0x60,
                    (byte)0x72, (byte)0x61, (byte)0x44, (byte)0x51, (byte)0x83, (byte)0x23, (byte)0x5a, (byte)0xdb},
            {
                    (byte)0xab, (byte)0xbe, (byte)0xde, (byte)0xa6, (byte)0x80, (byte)0x05, (byte)0x6f, (byte)0x52,
                    (byte)0x38, (byte)0x2a, (byte)0xe5, (byte)0x48, (byte)0xb2, (byte)0xe4, (byte)0xf3, (byte)0xf3,
                    (byte)0x89, (byte)0x41, (byte)0xe7, (byte)0x1c, (byte)0xff, (byte)0x8a, (byte)0x78, (byte)0xdb,
                    (byte)0x1f, (byte)0xff, (byte)0xe1, (byte)0x8a, (byte)0x1b, (byte)0x33, (byte)0x61, (byte)0x03,
                    (byte)0x9f, (byte)0xe7, (byte)0x67, (byte)0x02, (byte)0xaf, (byte)0x69, (byte)0x33, (byte)0x4b,
                    (byte)0x7a, (byte)0x1e, (byte)0x6c, (byte)0x30, (byte)0x3b, (byte)0x76, (byte)0x52, (byte)0xf4,
                    (byte)0x36, (byte)0x98, (byte)0xfa, (byte)0xd1, (byte)0x15, (byte)0x3b, (byte)0xb6, (byte)0xc3,
                    (byte)0x74, (byte)0xb4, (byte)0xc7, (byte)0xfb, (byte)0x98, (byte)0x45, (byte)0x9c, (byte)0xed},
            {
                    (byte)0x7b, (byte)0xcd, (byte)0x9e, (byte)0xd0, (byte)0xef, (byte)0xc8, (byte)0x89, (byte)0xfb,
                    (byte)0x30, (byte)0x02, (byte)0xc6, (byte)0xcd, (byte)0x63, (byte)0x5a, (byte)0xfe, (byte)0x94,
                    (byte)0xd8, (byte)0xfa, (byte)0x6b, (byte)0xbb, (byte)0xeb, (byte)0xab, (byte)0x07, (byte)0x61,
                    (byte)0x20, (byte)0x01, (byte)0x80, (byte)0x21, (byte)0x14, (byte)0x84, (byte)0x66, (byte)0x79,
                    (byte)0x8a, (byte)0x1d, (byte)0x71, (byte)0xef, (byte)0xea, (byte)0x48, (byte)0xb9, (byte)0xca,
                    (byte)0xef, (byte)0xba, (byte)0xcd, (byte)0x1d, (byte)0x7d, (byte)0x47, (byte)0x6e, (byte)0x98,
                    (byte)0xde, (byte)0xa2, (byte)0x59, (byte)0x4a, (byte)0xc0, (byte)0x6f, (byte)0xd8, (byte)0x5d,
                    (byte)0x6b, (byte)0xca, (byte)0xa4, (byte)0xcd, (byte)0x81, (byte)0xf3, (byte)0x2d, (byte)0x1b},
            {
                    (byte)0x37, (byte)0x8e, (byte)0xe7, (byte)0x67, (byte)0xf1, (byte)0x16, (byte)0x31, (byte)0xba,
                    (byte)0xd2, (byte)0x13, (byte)0x80, (byte)0xb0, (byte)0x04, (byte)0x49, (byte)0xb1, (byte)0x7a,
                    (byte)0xcd, (byte)0xa4, (byte)0x3c, (byte)0x32, (byte)0xbc, (byte)0xdf, (byte)0x1d, (byte)0x77,
                    (byte)0xf8, (byte)0x20, (byte)0x12, (byte)0xd4, (byte)0x30, (byte)0x21, (byte)0x9f, (byte)0x9b,
                    (byte)0x5d, (byte)0x80, (byte)0xef, (byte)0x9d, (byte)0x18, (byte)0x91, (byte)0xcc, (byte)0x86,
                    (byte)0xe7, (byte)0x1d, (byte)0xa4, (byte)0xaa, (byte)0x88, (byte)0xe1, (byte)0x28, (byte)0x52,
                    (byte)0xfa, (byte)0xf4, (byte)0x17, (byte)0xd5, (byte)0xd9, (byte)0xb2, (byte)0x1b, (byte)0x99,
                    (byte)0x48, (byte)0xbc, (byte)0x92, (byte)0x4a, (byte)0xf1, (byte)0x1b, (byte)0xd7, (byte)0x20}
    };
    private final byte[] iv = new byte[64];
    private byte[] N = new byte[64];
    private byte[] Sigma = new byte[64];
    private int outLen;

    public Gost(int outputLength) {
        // Инициализация начального вектора (iv), N и Sigma в зависимости от длины выхода
        if (outputLength == 512) {
            Arrays.fill(N, (byte) 0x00);
            Arrays.fill(Sigma, (byte) 0x00);
            Arrays.fill(iv, (byte) 0x00);
            outLen = 512;
        } else if (outputLength == 256) {
            Arrays.fill(N, (byte) 0x00);
            Arrays.fill(Sigma, (byte) 0x00);
            Arrays.fill(iv, (byte) 0x01);
            outLen = 256;
        }
    }

    // Функция сложения по модулю 512
    private byte[] addModulo512(byte[] a, byte[] b) {
        byte[] result = new byte[64];
        int carry = 0;
        for (int i = 63; i >= 0; i--) {
            int sum = (a[i] & 0xFF) + (b[i] & 0xFF) + carry;
            result[i] = (byte) (sum & 0xFF);
            carry = (sum >> 8);
        }
        return result;
    }

    // Функция XOR 512 бит
    private byte[] addXor512(byte[] a, byte[] b) {
        byte[] result = new byte[64];
        for (int i = 0; i < 64; i++) {
            result[i] = (byte) (a[i] ^ b[i]);
        }
        return result;
    }

    // Функция S - замена байтов по таблице SBOX
    private byte[] S(byte[] state) {
        byte[] result = new byte[64];
        for (int i = 0; i < 64; i++) {
            result[i] = SBOX[state[i] & 0xFF];
        }
        return result;
    }

    // Функция P - перестановка байтов по таблице TAU
    private byte[] P(byte[] state) {
        byte[] result = new byte[64];
        for (int i = 0; i < 64; i++) {
            result[i] = state[TAU[i]];
        }
        return result;
    }

    // Функция L - линейное преобразование
    private byte[] L(byte[] state) {
        byte[] result = new byte[64];
        for (int i = 0; i < 8; i++) {
            long t = 0;
            for (int j = 0; j < 64; j++) {
                if (((state[i * 8 + j / 8] >> (7 - (j % 8))) & 1) != 0) {
                    t ^= A[j];
                }
            }
            for (int k = 0; k < 8; k++) {
                result[i * 8 + k] = (byte) ((t >> (8 * (7 - k))) & 0xFF);
            }
        }
        return result;
    }

    // Функция KeySchedule для получения ключа на основе индекса
    private byte[] keySchedule(byte[] K, int i) {
        K = addXor512(K, C[i]);
        K = S(K);
        K = P(K);
        K = L(K);
        return K;
    }

    // Функция E - шифрование блока данных с использованием ключа
    private byte[] E(byte[] K, byte[] m) {
        byte[] state = addXor512(K, m);
        for (int i = 0; i < 12; i++) {
            state = S(state);
            state = P(state);
            state = L(state);
            K = keySchedule(K, i);
            state = addXor512(state, K);
        }
        return state;
    }

    // Функция G_n - итерация хэширования
    private byte[] G_n(byte[] N, byte[] h, byte[] m) {
        byte[] K = addXor512(h, N);
        K = S(K);
        K = P(K);
        K = L(K);
        byte[] t = E(K, m);
        t = addXor512(t, h);
        return addXor512(t, m);
    }

    // Основная функция для вычисления хэша
    public byte[] getHash(byte[] message) {
        byte[] paddedMes = new byte[64];
        int len = message.length * 8;
        byte[] h = Arrays.copyOf(iv, 64);
        byte[] N_0 = new byte[64];
        byte[] N_512 = new byte[64];
        N_512[63] = 64; // Эквивалент 512 бит

        int inc = 0;
        while (len >= 512) {
            inc++;
            byte[] tempMes = Arrays.copyOfRange(message, message.length - inc * 64, message.length - (inc - 1) * 64);
            h = G_n(N, h, tempMes);
            N = addModulo512(N, N_512);
            Sigma = addModulo512(Sigma, tempMes);
            len -= 512;
        }

        byte[] message1 = Arrays.copyOfRange(message, 0, message.length - inc * 64);
        Arrays.fill(paddedMes, (byte) 0);
        paddedMes[64 - message1.length - 1] = 0x01;
        System.arraycopy(message1, 0, paddedMes, 64 - message1.length, message1.length);

        h = G_n(N, h, paddedMes);
        byte[] messageLen = new byte[64];
        messageLen[63] = (byte) (message1.length * 8);

        N = addModulo512(N, messageLen);
        Sigma = addModulo512(Sigma, paddedMes);

        h = G_n(N_0, h, N);
        h = G_n(N_0, h, Sigma);

        if (outLen == 512) {
            return h;
        } else {
            return Arrays.copyOf(h, 32);
        }
    }
}

