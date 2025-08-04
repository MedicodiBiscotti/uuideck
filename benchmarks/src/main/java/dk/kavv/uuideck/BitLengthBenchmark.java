package dk.kavv.uuideck;

import org.openjdk.jmh.annotations.*;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Fork(value = 1)
@Warmup(iterations = 1)
@Measurement(iterations = 1)
public class BitLengthBenchmark {

    @Param({"0", "1", "127", "255", "1023", "65535", "1000000000"})
    public int value;

    @Benchmark
    public int bigIntegerBitLength() {
        return BigInteger.valueOf(value).bitLength();
    }

    @Benchmark
    public int stringBitLength() {
        return Integer.toBinaryString(value).length();
    }

    @Benchmark
    public int bitwiseBitLength() {
        return Integer.SIZE - Integer.numberOfLeadingZeros(value);
    }
}

