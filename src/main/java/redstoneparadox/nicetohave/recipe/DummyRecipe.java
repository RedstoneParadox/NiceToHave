package redstoneparadox.nicetohave.recipe;

import com.google.gson.JsonObject;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.Optional;

public class DummyRecipe implements Recipe<Inventory> {

    public static final RecipeType<DummyRecipe> TYPE = Registry.register(Registry.RECIPE_TYPE, "nicetohave:dummy", new Type());
    public static final RecipeSerializer<DummyRecipe> SERIALIZER = RecipeSerializer.register("nicetohave:dummy", new DummyRecipe.Serializer());

    @Override
    public boolean isIgnoredInRecipeBook() {
        return true;
    }

    @Override
    public String getGroup() {
        return ItemGroup.MISC.getId();
    }

    @Override
    public boolean matches(Inventory var1, World var2) {
        return false;
    }

    @Override
    public ItemStack craft(Inventory var1) {
        return null;
    }

    @Override
    public boolean fits(int var1, int var2) {
        return false;
    }

    @Override
    public ItemStack getOutput() {
        return null;
    }

    @Override
    public Identifier getId() {
        return null;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return TYPE;
    }

    @Override
    public DefaultedList<Ingredient> getPreviewInputs() {
        return DefaultedList.of();
    }

    @Override
    public DefaultedList<ItemStack> getRemainingStacks(Inventory inventory_1) {
        return DefaultedList.of();
    }

    private static class Type implements RecipeType<DummyRecipe> {
        @Override
        public <C extends Inventory> Optional<DummyRecipe> get(Recipe<C> recipe_1, World world_1, C inventory_1) {
            return Optional.empty();
        }
    }

    private static class Serializer implements RecipeSerializer<DummyRecipe> {

        @Override
        public DummyRecipe read(Identifier var1, JsonObject var2) {
            return new DummyRecipe();
        }

        @Override
        public DummyRecipe read(Identifier var1, PacketByteBuf var2) {
            return new DummyRecipe();
        }

        @Override
        public void write(PacketByteBuf var1, DummyRecipe var2) {

        }
    }
}
