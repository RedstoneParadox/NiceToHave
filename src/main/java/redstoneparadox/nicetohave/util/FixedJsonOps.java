package redstoneparadox.nicetohave.util;

import com.google.gson.JsonElement;
import com.mojang.datafixers.types.JsonOps;

import java.util.Optional;

public class FixedJsonOps extends JsonOps {
    public static final FixedJsonOps INSTANCE = new FixedJsonOps();

    @Override
    public Optional<Number> getNumberValue(JsonElement input) {
        if (input.isJsonPrimitive()) {
            if (input.getAsJsonPrimitive().isNumber()) return Optional.of(input.getAsNumber());
            if (input.getAsJsonPrimitive().isBoolean()) {
                boolean bool = input.getAsBoolean();
                if (bool) return Optional.of(1);
                else return Optional.of(0);
            }
        }
        return Optional.empty();
    }
}
