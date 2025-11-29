package dk.kavv.uuideck;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Fork(value = 1)
@Warmup(iterations = 1)
@Measurement(iterations = 1)
public class ZeroPadBenchmark {
    @Param({"111111111111111", "1111111111111111", "11111111111111111"})
    public Long value;

    @Benchmark
    public String padToGroupOfThreeRemainder() {
        String s = value.toString();
        int remainder = s.length() % 3;
        if (remainder == 0) return s;
        return "0".repeat(3 - remainder) + s;
    }

    @Benchmark
    public String padToGroupOfThreeRemainderSwitch() {
        String s = value.toString();
        int remainder = s.length() % 3;
        return switch (remainder) {
            case 0 -> s;
            case 1 -> "00" + s;
            case 2 -> "0" + s;
            default -> throw new IllegalStateException("Unexpected value: " + remainder);
        };
    }

    @Benchmark
    public String padToGroupOfThreeFormatLength() {
        String s = value.toString();
        return String.format("%0" + (int) Math.ceil(s.length() / 3.0) * 3 + "d", value);
    }
}
