package filtres;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;

public final class JwtKey {

    private static final String SECRET =
            "ChangezCetteCleSecretePourLAtelierSOA-UP_WEB-2025-2026-0123456789-ABCDEFGH";

    private static final Key KEY =
            new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8), "HmacSHA512");

    private JwtKey() { }   // empêche l'instanciation

    public static Key get() {
        return KEY;
    }
}