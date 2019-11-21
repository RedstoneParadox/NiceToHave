package redstoneparadox.nicetohave.recipe;

import com.google.gson.JsonObject;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class TemplateCraftingRecipe implements Recipe<CraftingInventory> {
    private final int width;
    private final int height;
    private final DefaultedList<Predicate<ItemStack>> ingredients;

    public TemplateCraftingRecipe(int width, int height, DefaultedList<Predicate<ItemStack>> ingredients) {
        this.width = width;
        this.height = height;
        this.ingredients = ingredients;
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        if (inventory.getWidth() == 2 && inventory.getHeight() == 2) return matchesSmall(inventory);
        return matchesNormal(inventory);
    }

    private boolean matchesSmall(CraftingInventory inventory) {
        if (width > 2 || height > 2) return false;

        int xShifts = 2 - width;
        int yShifts = 2 - height;

        return false;
    }

    private boolean matchesNormal(CraftingInventory inventory) {
        if (width > 3 || height > 3) return false;

        int xShifts = 3 - width;
        int yShifts = 3 - height;

        return false;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
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
        return new Identifier("nicetohave:crafting_template_shaped");
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public RecipeType<?> getType() {
        return null;
    }

    class Serializer implements RecipeSerializer<TemplateCraftingRecipe> {

        @Override
        public TemplateCraftingRecipe read(Identifier id, JsonObject object) {
            String name = JsonHelper.getString(object, "group", "");



            return null;
        }

        @Override
        public TemplateCraftingRecipe read(Identifier id, PacketByteBuf buf) {
            return null;
        }

        @Override
        public void write(PacketByteBuf buf, TemplateCraftingRecipe recipe) {

        }
    }
}
