public class Main {
    // Метод для преобразования массива байт в строку Hex
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString().toUpperCase();
    }

    public static void main(String[] args) {
        // Создание объектов GOST для 256 и 512-битных хешей
        Gost gost256 = new Gost(256);
        Gost gost512 = new Gost(512);

//        // Сообщение для хеширования
        String sMessage = "Слава России";
        byte[] message = sMessage.getBytes();
//        byte[] message = {
//                0x32, 0x31, 0x30, 0x39, 0x38, 0x37, 0x36, 0x35, 0x34, 0x33, 0x32, 0x31, 0x30, 0x39, 0x38, 0x37,
//                0x36, 0x35, 0x34, 0x33, 0x32, 0x31, 0x30, 0x39, 0x38, 0x37, 0x36, 0x35, 0x34, 0x33, 0x32, 0x31,
//                0x30, 0x39, 0x38, 0x37, 0x36, 0x35, 0x34, 0x33, 0x32, 0x31, 0x30, 0x39, 0x38, 0x37, 0x36, 0x35,
//                0x34, 0x33, 0x32, 0x31, 0x30, 0x39, 0x38, 0x37, 0x36, 0x35, 0x34, 0x33, 0x32, 0x31, 0x30
//        };

        // Вычисление хешей
        byte[] res256 = gost256.getHash(message);
        byte[] res512 = gost512.getHash(message);

        // Вывод результатов
        System.out.println("256-bit hash: " + bytesToHex(res256));
        System.out.println("512-bit hash: " + bytesToHex(res512));

        ElectronicDigitalSignature electronicDigitalSignature = new ElectronicDigitalSignature();
        byte[] signature = ElectronicDigitalSignature.signMessage(message);
        System.out.println("\n"+ "Подпись: " + ElectronicDigitalSignature.verifySignature(message, signature));
    }
}
