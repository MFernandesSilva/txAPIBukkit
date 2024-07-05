package txdev.txapibukkit.api;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class NBT {

    // Manipulação de listas NBT
    public static void adicionarAListaNBT(ItemStack item, String chaveLista, Object valor) {
        NBTItem nbtItem = new NBTItem(item);
        List<Object> lista = nbtItem.getObject(chaveLista, List.class);
        if (lista == null) {
            lista = new ArrayList<>();
            nbtItem.setObject(chaveLista, lista);
        }
        lista.add(valor);
        nbtItem.applyNBT(item);
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> getListaNBT(ItemStack item, String chaveLista) {
        NBTItem nbtItem = new NBTItem(item);
        return (List<T>) nbtItem.getObject(chaveLista, List.class);
    }

    // Manipulação de compostos NBT
    public static void setarCompostoNBT(ItemStack item, String chaveComposto, NBTCompound composto) {
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setObject(chaveComposto, composto);
        nbtItem.applyNBT(item);
    }

    public static NBTCompound getCompostoNBT(ItemStack item, String chaveComposto) {
        NBTItem nbtItem = new NBTItem(item);
        return nbtItem.getCompound(chaveComposto);
    }

    // Serialização
    public static String serializarItem(ItemStack item) {
        NBTItem nbtItem = new NBTItem(item);
        return nbtItem.toString();
    }

    // Desserialização (limitada - suporta apenas tags simples)
    public static ItemStack desserializarItem(ItemStack itemBase, String nbtString) {
        NBTItem nbtItemBase = new NBTItem(itemBase);

        try {
            // Análise simplificada da string NBT
            String[] tags = nbtString.substring(1, nbtString.length() - 1).split(",");
            for (String tag : tags) {
                String[] partes = tag.split(":");
                String chave = partes[0].trim();
                String valor = partes[1].trim();

                // Lógica para definir o valor (limitada a boolean, int e string)
                if (valor.equals("true") || valor.equals("false")) {
                    nbtItemBase.setBoolean(chave, Boolean.parseBoolean(valor));
                } else {
                    try {
                        int valorInt = Integer.parseInt(valor);
                        nbtItemBase.setInteger(chave, valorInt);
                    } catch (NumberFormatException e) {
                        nbtItemBase.setString(chave, valor);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Tratar erros de desserialização
        }

        return nbtItemBase.getItem();
    }
}
