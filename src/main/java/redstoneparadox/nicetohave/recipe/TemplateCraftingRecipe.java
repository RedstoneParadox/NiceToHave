package redstoneparadox.nicetohave.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.JsonOps;
import net.minecraft.datafixers.NbtOps;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.recipe.*;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TemplateCraftingRecipe implements CraftingRecipe {
    private final List<ShapedRecipe> recipes;
    private final JsonObject original;

    private TemplateCraftingRecipe(List<ShapedRecipe> recipes, JsonObject original) {
        this.recipes = recipes;
        this.original = original;
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        for (ShapedRecipe recipe: recipes) {
            if (recipe.matches(inventory, world)) return true;
        }
        return false;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
        for (ShapedRecipe recipe: recipes) {
            if (recipe.matches(inventory, null)) return recipe.craft(inventory);
        }
        return null;
    }

    @Override
    public boolean fits(int var1, int var2) {
        return recipes.get(0).fits(var1, var2);
    }

    @Override
    public ItemStack getOutput() {
        return recipes.get(0).getOutput();
    }

    @Override
    public Identifier getId() {
        return new Identifier("nicetohave:crafting_template_shaped");
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.CRAFTING_TEMPLATE;
    }

    public static class Serializer implements RecipeSerializer<TemplateCraftingRecipe> {

        public static final Serializer CRAFTING_TEMPLATE = RecipeSerializer.register("nicetohave:crafting_template_shaped", new Serializer());

        @Override
        public TemplateCraftingRecipe read(Identifier id, JsonObject object) {
            JsonArray templateArray = object.getAsJsonArray("template");
            List<Template> templates = new ArrayList<>();
            List<ShapedRecipe> recipes = new ArrayList<>();

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
                System.out.println("Here's the copy!: " + copy);
                try {
                    ShapedRecipe recipe = SHAPED.read(id, copy);
                    recipes.add(recipe);
                } catch (Exception ignored) {

                }
            }

            return new TemplateCraftingRecipe(recipes, object);
        }

        private JsonObject fillKeyTemplate(JsonObject object, Template template) {
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

        private JsonObject fillResultTemplate(JsonObject object, Template template) {
            JsonObject result = new JsonObject();
            result.add("count", object.get("count"));

            String item = replace(object.get("item").getAsString(), template);
            result.add("item", new JsonPrimitive(item));

            return result;
        }

        private String replace(String string, Template template) {
            String out = string;
            for (Pair<String, String> pair: template.fills) {
                out = out.replace("${" + pair.getLeft() +"}", pair.getRight());
            }
            return out;
        }

        @Override
        public TemplateCraftingRecipe read(Identifier id, PacketByteBuf buf) {
            Tag nbt = buf.readCompoundTag();
            if (nbt != null) {
                JsonElement json = Dynamic.convert(NbtOps.INSTANCE, JsonOps.INSTANCE, nbt);
                if (json instanceof JsonObject) {
                    return read(id, (JsonObject) json);
                }
            }
            return null;
        }

        @Override
        public void write(PacketByteBuf buf, TemplateCraftingRecipe recipe) {
            Tag nbt = Dynamic.convert(JsonOps.INSTANCE, NbtOps.INSTANCE, recipe.original);
            if (nbt instanceof CompoundTag) {
                buf.writeCompoundTag((CompoundTag) nbt);
            }
        }

        private static class Template {
            final List<Pair<String, String>> fills;

            Template(List<Pair<String, String>> fills) {
                this.fills = fills;
            }
        }
    }
}
