package dk.kavv.uuideck.encoding;

import dk.kavv.uuideck.compression.SixBitCompressor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.StringJoiner;

@Getter
@RequiredArgsConstructor
public class MultiEncoder implements Encoder {
    private final List<Encoder> encoders;

    @Override
    public String encode(byte[] bytes) {
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        StringBuilder sb = new StringBuilder();
        for (Encoder encoder : encoders) {
            if (encoder instanceof Base64Encoder) {
                sb.append("Base64 ");
                if (((Base64Encoder) encoder).getCompressor() instanceof SixBitCompressor) {
                    sb.append("(compressed):\t");
                } else {
                    sb.append("(uncompressed):\t");
                }
            } else if (encoder instanceof AsciiEncoder) {
                sb.append("ASCII:\t\t\t");
            }
            sb.append(encoder.encode(bytes));
            sj.add(sb);
            sb.delete(0, sb.length()); // or sb.setLength(0);
        }
        return sj.toString();
    }

    @Override
    public byte[] decode(String s) {
        throw new UnsupportedOperationException("Multi-encoder does not support decoding. Please specify which encoder to use.");
    }
}
