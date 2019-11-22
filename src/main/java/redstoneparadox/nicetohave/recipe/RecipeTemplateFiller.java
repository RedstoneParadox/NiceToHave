package redstoneparadox.nicetohave.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecipeTemplateFiller {
    public static List<Pair<Identifier, JsonObject>> read(Identifier id, JsonObject object) {
        JsonArray templateArray = object.getAsJsonArray("template");
        List<Template> templates = new ArrayList<>();
        List<Pair<Identifier, JsonObject>> filledRecipes = new ArrayList<>();

        for (JsonElement element: templateArray) {
            if (element.isJsonObject()) {
                JsonObject obj = element.getAsJsonObject();
                List<Pair<String, String>> pairs = new ArrayList<>();
                for (Map.Entry<String, JsonElement> entry: obj.entrySet()) {
                    if (entry.getValue().isJsonPrimitive()) {
                        Pair<String, String> pair = new Pair<>(entry.getKey(), entry.getValue().getAsString());
                        pairs.add(pair);
                    }
                }
                templates.add(new Template(pairs));
            }
        }

        for (Template template: templates) {
            JsonObject copy = new JsonObject();
            copy.add("type", new JsonPrimitive("minecraft:crafting_shaped"));
            copy.add("pattern", object.get("pattern"));
            copy.add("key", fillKeyTemplate(object.getAsJsonObject("key"), template));
            copy.add("result", fillResultTemplate(object.getAsJsonObject("result"), template));

            Identifier identifier = new Identifier(id.getNamespace(), replace(object.get("id").getAsString(), template));

            filledRecipes.add(new Pair<>(identifier, copy));
        }

        return filledRecipes;
    }

    private static JsonObject fillKeyTemplate(JsonObject object, Template template) {
        JsonObject key = new JsonObject();

        for (Map.Entry<String, JsonElement> entry: object.entrySet()) {
            if (entry.getValue() instanceof JsonObject) {
                JsonObject keyObject = new JsonObject();
                String filledString = replace(((JsonObject) entry.getValue()).get("item").getAsString(), template);
                keyObject.add("item", new JsonPrimitive(filledString));
                key.add(entry.getKey(), keyObject);
            }
        }
        return key;
    }

    private static JsonObject fillResultTemplate(JsonObject object, Template template) {
        JsonObject result = new JsonObject();
        result.add("count", object.get("count"));

        String item = replace(object.get("item").getAsString(), template);
        result.add("item", new JsonPrimitive(item));

        return result;
    }

    private static String replace(String string, Template template) {
        String out = string;
        for (Pair<String, String> pair: template.fills) {
            out = out.replace("${" + pair.getLeft() +"}", pair.getRight());
        }
        return out;
    }
}
