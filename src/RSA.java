import java.math.BigInteger;
import java.util.Random;

/**
 * RSA - Реализация алгоритма шифрования RSA
 */
public class RSA {
    /** Публичный ключ RSA (n, e) - доступен всем для шифрования. */
    public BigInteger n;
    public BigInteger e;

    /** Приватный ключ RSA (n, d) - секретный ключ для расшифровки. */
    private BigInteger d;

    /**
     * Конструктор для генерации ключевой пары RSA с заданным количеством битов.
     *
     * @param bits Количество бит для простых чисел p и q.
     */
    public RSA(int bits) {
        Random randomInteger = new Random();
        BigInteger p = BigInteger.probablePrime(bits, randomInteger);
        BigInteger q = BigInteger.probablePrime(bits, randomInteger);

        n = p.multiply(q);
        e = BigInteger.probablePrime(bits / 2, randomInteger);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        d = e.modInverse(phi);
    }

    /**
     * Шифрует сообщение (массив байтов) с использованием публичного ключа.
     *
     * @param message Сообщение для шифрования в виде массива байтов.
     * @return Зашифрованное сообщение в виде массива байтов.
     */
    public byte[] encrypt(byte[] message) {
        return (new BigInteger(message)).modPow(e, n).toByteArray();
    }

    /**
     * Дешифрует зашифрованное сообщение (массив байтов) с использованием приватного ключа.
     *
     * @param message Зашифрованное сообщение (шифртекст) для дешифровки.
     * @return Расшифрованное сообщение в виде массива байтов.
     */
    public byte[] decrypt(byte[] message) {
        return (new BigInteger(message).modPow(d, n)).toByteArray();
    }
}