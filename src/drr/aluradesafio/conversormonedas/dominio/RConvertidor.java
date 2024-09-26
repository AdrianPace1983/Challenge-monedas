package drr.aluradesafio.conversormonedas.dominio;

import java.math.BigDecimal;

public record RConvertidor(long time_last_update_unix,
                           long time_next_update_unix,
                           String base_code,
                           String target_code,
                           BigDecimal conversion_rate,
                           BigDecimal conversion_result) {
}
