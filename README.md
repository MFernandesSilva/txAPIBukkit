## TxApiBukkit - Documentação da Classe Inventario (Spigot 1.7.10)

A classe `Inventario` fornece métodos utilitários para facilitar a manipulação de inventários no Spigot 1.7.10.

### Métodos:

**1. `criarInventario(int tamanho, String titulo)`**

*   **Descrição:** Cria um novo inventário com o tamanho e título especificados.
*   **Parâmetros:**
    *   `tamanho` (int): O tamanho do inventário (deve ser múltiplo de 9 e estar entre 9 e 54).
    *   `titulo` (String): O título do inventário.
*   **Retorno:** Um objeto `Inventory` representando o novo inventário.
*   **Exceção:** Lança `IllegalArgumentException` se o tamanho for inválido.
*   **Exemplo:**

```java
Inventory meuInventario = Inventario.criarInventario(27, "Meu Inventário");
```

**2. `adicionarItem(Inventory inventario, ItemStack item, int slot)`**

*   **Descrição:** Adiciona um item a um slot específico do inventário.
*   **Parâmetros:**
    *   `inventario` (Inventory): O inventário onde o item será adicionado.
    *   `item` (ItemStack): O item a ser adicionado.
    *   `slot` (int): O índice do slot (0 a tamanho-1).
*   **Exceção:** Lança `IllegalArgumentException` se o slot for inválido.
*   **Exemplo:**

```java
ItemStack espada = new ItemStack(Material.DIAMOND_SWORD);
Inventario.adicionarItem(meuInventario, espada, 0); // Adiciona no primeiro slot
```

**3. `removerItem(Inventory inventario, int slot)`**

*   **Descrição:** Remove um item de um slot específico.
*   **Parâmetros:**
    *   `inventario` (Inventory): O inventário de onde o item será removido.
    *   `slot` (int): O índice do slot.
*   **Exceção:** Lança `IllegalArgumentException` se o slot for inválido.
*   **Exemplo:**

```java
Inventario.removerItem(meuInventario, 5); // Remove o item do sexto slot
```

**4. `substituirItem(Inventory inventario, ItemStack novoItem, int slot)`**

*   **Descrição:** Substitui um item em um slot específico por outro item.
*   **Parâmetros:**
    *   `inventario` (Inventory): O inventário onde o item será substituído.
    *   `novoItem` (ItemStack): O novo item que substituirá o antigo.
    *   `slot` (int): O índice do slot.
*   **Exceção:** Lança `IllegalArgumentException` se o slot for inválido.
*   **Exemplo:**

```java
ItemStack picareta = new ItemStack(Material.DIAMOND_PICKAXE);
Inventario.substituirItem(meuInventario, picareta, 0);
```

**5. `preencherInventario(Inventory inventario, ItemStack item)`**

*   **Descrição:** Preenche todos os slots vazios do inventário com o item especificado.
*   **Parâmetros:**
    *   `inventario` (Inventory): O inventário a ser preenchido.
    *   `item` (ItemStack): O item que preencherá os slots vazios.
*   **Exemplo:**

```java
ItemStack vidro = new ItemStack(Material.GLASS);
Inventario.preencherInventario(meuInventario, vidro);
```

**6. `getItem(Inventory inventario, int slot)`**

*   **Descrição:** Obtém o item em um slot específico.
*   **Parâmetros:**
    *   `inventario` (Inventory): O inventário de onde o item será obtido.
    *   `slot` (int): O índice do slot.
*   **Retorno:** O `ItemStack` no slot especificado, ou `null` se o slot estiver vazio.
*   **Exceção:** Lança `IllegalArgumentException` se o slot for inválido.
*   **Exemplo:**

```java
ItemStack itemNoSlot10 = Inventario.getItem(meuInventario, 10);
```

**7. `temEspaco(Inventory inventario)`**

*   **Descrição:** Verifica se o inventário tem pelo menos um slot vazio.
*   **Parâmetros:**
    *   `inventario` (Inventory): O inventário a ser verificado.
*   **Retorno:** `true` se houver pelo menos um slot vazio, `false` caso contrário.
*   **Exemplo:**

```java
if (Inventario.temEspaco(meuInventario)) {
    // Adiciona um item ao inventário
}
```

**8. `getPrimeiroSlotVazio(Inventory inventario)`**

*   **Descrição:** Obtém o índice do primeiro slot vazio no inventário.
*   **Parâmetros:**
    *   `inventario` (Inventory): O inventário a ser verificado.
*   **Retorno:** O índice do primeiro slot vazio, ou -1 se não houver slots vazios.
*   **Exemplo:**

```java
int slotVazio = Inventario.getPrimeiroSlotVazio(meuInventario);
if (slotVazio != -1) {
    // Adiciona um item ao slot vazio
}
```

**9. `gerarItemAleatorio()`**

*   **Descrição:** Gera um `ItemStack` aleatório, com material, quantidade e possivelmente encantamentos aleatórios.
*   **Retorno:** O `ItemStack` gerado aleatoriamente.
*   **Exemplo:**

```java
ItemStack itemAleatorio = Inventario.gerarItemAleatorio();
```
