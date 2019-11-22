package redstoneparadox.nicetohave.recipe;

import net.minecraft.util.Pair;

import java.util.List;

public class Template {
    public final List<Pair<String, String>> fills;

    public Template(List<Pair<String, String>> fills) {
        this.fills = fills;
    }
}
