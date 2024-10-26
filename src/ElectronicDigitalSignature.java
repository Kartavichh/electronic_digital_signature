import java.util.Arrays;

public class ElectronicDigitalSignature {

    /**
     * ЭЦП на ГОСТ 34.11-2012 (Стрибог) и RSA
     */

        private static final RSA rsa = new RSA(1024); // Класс RSA для управления ключами
        static Gost gost512 = new Gost(256);
        /**
         * Создает цифровую подпись для переданного сообщения.
         * @param message Сообщение для подписи.
         * @return Подпись в виде байтового массива.
         */
        public static byte[] signMessage(byte[] message) {
            String messageHash = Arrays.toString(gost512.getHash(message));
            return rsa.decrypt(messageHash.getBytes());
        }

        /**
         * Проверяет подпись, используя переданное сообщение и цифровую подпись.
         * @param message Исходное сообщение.
         * @param signature Подпись для проверки.
         * @return true, если подпись верна, иначе false.
         */
        public static boolean verifySignature(byte[] message, byte[] signature) {
            Gost gost512 = new Gost(256);
            String messageHash = Arrays.toString(gost512.getHash(message));
            byte[] decryptedHash = rsa.encrypt(signature);
            return messageHash.equals(new String(decryptedHash));
        }
    }
