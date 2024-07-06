package txdev.txapibukkit.api;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class Item {
    private ItemStack is;
    private ItemMeta im;

    public Item(Material m, int amount, short data) {
        is = new ItemStack(m, amount, data);
        im = is.getItemMeta();
    }

    public Item(ItemStack itemStack) {
        this.is = itemStack.clone();
        this.im = is.getItemMeta();
    }

    public Item setName(String name) {
        im.setDisplayName(name);
        is.setItemMeta(im);
        return this;
    }


    public Item setLore(List<String> lore) {
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    public Item setEnchant(Enchantment enchant, int value, boolean bl) {
        im.addEnchant(enchant, value, bl);
        is.setItemMeta(im);
        return this;
    }

    public ItemStack getIs(){
        return is;
    }

    public Item setUnbreakable(boolean unbreakable) {
        im.spigot().setUnbreakable(unbreakable);
        is.setItemMeta(im);
        return this;
    }

    public Item setDurability(short durability) {
        is.setDurability(durability);
        return this;
    }

    public Item setSkullOwner(String playerName) {
        if (is.getType() == Material.SKULL_ITEM) {
            SkullMeta skullMeta = (SkullMeta) im;
            skullMeta.setOwner(playerName);
            is.setItemMeta(skullMeta);
        }
        return this;
    }

    public Item setLeatherArmorColor(Color color) {
        if (is.getType() == Material.LEATHER_HELMET || is.getType() == Material.LEATHER_CHESTPLATE || is.getType()
                == Material.LEATHER_LEGGINGS || is.getType() == Material.LEATHER_BOOTS) {
            LeatherArmorMeta meta = (LeatherArmorMeta) is.getItemMeta();
            meta.setColor(color);
            is.setItemMeta(meta);
        }
        return this;
    }

    // NBT
    public <T> Item setNBT(String key, T value) {
        NBTItem nbtItem = new NBTItem(is);
        if (value instanceof Boolean) nbtItem.setBoolean(key, (Boolean) value);
        else if (value instanceof Integer) nbtItem.setInteger(key, (Integer) value);
        else if (value instanceof Double) nbtItem.setDouble(key, (Double) value);
        else if (value instanceof String) nbtItem.setString(key, (String) value);
        is = nbtItem.getItem();
        return this;
    }

    public <T> T getNBT(String key, Class<T> type) {
        NBTItem nbtItem = new NBTItem(is);
        if (type == Boolean.class) return type.cast(nbtItem.getBoolean(key));
        else if (type == Integer.class) return type.cast(nbtItem.getInteger(key));
        else if (type == Double.class) return type.cast(nbtItem.getDouble(key));
        else if (type == String.class) return type.cast(nbtItem.getString(key));
        return null;
    }

    public boolean hasNBTKey(String key) {
        NBTItem nbtItem = new NBTItem(is);
        return nbtItem.hasKey(key);
    }

    public Item removeNBTKey(String key) {
        NBTItem nbtItem = new NBTItem(is);
        nbtItem.removeKey(key);
        is = nbtItem.getItem();
        return this;
    }
}
