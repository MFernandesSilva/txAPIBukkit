# TxApiBukkit - Documentação da API (Spigot 1.7.10)

TxApiBukkit é uma API para o Spigot 1.7.10 que simplifica tarefas comuns de desenvolvimento de plugins, como manipulação de inventários, itens, localizações, mensagens, dados NBT, placares e sons.

## Inventario

### Métodos

*   **`criarInventario(int tamanho, String titulo)`:** Cria um novo inventário com o tamanho e título especificados. O tamanho deve ser múltiplo de 9 e estar entre 9 e 54.

```java
Inventory meuInventario = Inventario.criarInventario(27, "Meu Inventário");
```

*   **`adicionarItem(Inventory inventario, ItemStack item, int slot)`:** Adiciona um item a um slot específico do inventário (0 a tamanho-1).

```java
ItemStack espada = new ItemStack(Material.DIAMOND_SWORD);
Inventario.adicionarItem(meuInventario, espada, 0); // Adiciona no primeiro slot (0)
```

*   **`removerItem(Inventory inventario, int slot)`:** Remove o item do slot especificado.

```java
Inventario.removerItem(meuInventario, 5); // Remove o item do sexto slot
```

*   **`substituirItem(Inventory inventario, ItemStack novoItem, int slot)`:** Substitui o item no slot especificado por outro item.

```java
ItemStack picareta = new ItemStack(Material.DIAMOND_PICKAXE);
Inventario.substituirItem(meuInventario, picareta, 0); // Substitui a espada pela picareta
```

*   **`preencherInventario(Inventory inventario, ItemStack item)`:** Preenche todos os slots vazios do inventário com o item especificado.

```java
ItemStack vidro = new ItemStack(Material.GLASS);
Inventario.preencherInventario(meuInventario, vidro);
```

*   **`getItem(Inventory inventario, int slot)`:** Obtém o item no slot especificado. Retorna `null` se o slot estiver vazio.

```java
ItemStack itemNoSlot10 = Inventario.getItem(meuInventario, 10);
```

*   **`temEspaco(Inventory inventario)`:** Verifica se o inventário tem pelo menos um slot vazio.

```java
if (Inventario.temEspaco(meuInventario)) {
    // Há espaço no inventário
}
```

*   **`getPrimeiroSlotVazio(Inventory inventario)`:** Obtém o índice do primeiro slot vazio. Retorna -1 se não houver slots vazios.

```java
int primeiroSlotVazio = Inventario.getPrimeiroSlotVazio(meuInventario);
```

*   **`gerarItemAleatorio()`:** Gera um `ItemStack` aleatório, com material, quantidade e possivelmente encantamentos aleatórios.

```java
ItemStack itemAleatorio = Inventario.gerarItemAleatorio();
```

## Item

### Métodos

*   **`new Item(Material m, int amount, short data)`:** Cria um novo item com o material, quantidade e dados (durabilidade/tipo) especificados.

```java
Item espada = new Item(Material.DIAMOND_SWORD, 1, (short) 0);
```

*   **`new Item(ItemStack itemStack)`:** Cria um novo objeto `Item` a partir de um `ItemStack` existente.

```java
ItemStack itemExistente = player.getInventory().getItemInMainHand();
Item item = new Item(itemExistente);
```

*   **`setName(String name)`:** Define o nome do item, aceitando cores e formatação com o caractere '&'.

```java
item.setName("&aEspada Lendária"); // Nome com código de cor
```

*   **`setLore(List<String> lore)`:** Define a descrição (lore) do item, aceitando cores e formatação com o caractere '&'.

```java
List<String> lore = Arrays.asList("&7Uma espada antiga", "&7e poderosa.");
item.setLore(lore);
```

*   **`setEnchant(Enchantment enchant, int level, boolean ignoreLevelRestriction)`:** Adiciona um encantamento ao item.

```java
item.setEnchant(Enchantment.DAMAGE_ALL, 5, true); // Nível 5 de Sharpness, ignorando restrições de nível
```

*   **`setUnbreakable(boolean unbreakable)`:** Define se o item é inquebrável.

```java
item.setUnbreakable(true);
```

*   **`setDurability(short durability)`:** Define a durabilidade do item.

```java
item.setDurability((short) 100); // 100 pontos de durabilidade restantes
```

*   **`setSkullOwner(String playerName)`:** Define o dono de um item de cabeça (apenas para `Material.SKULL_ITEM`).

```java
item.setSkullOwner("Notch");
```

*   **`setLeatherArmorColor(Color color)`:** Define a cor de uma armadura de couro.

```java
item.setLeatherArmorColor(Color.RED);
```

*   **`getIs()`:** Retorna o `ItemStack` correspondente ao objeto `Item`.

```java
ItemStack itemStack = item.getIs();
```

### Métodos NBT:

*   **`setNBT(String key, T value)`:** Define um valor NBT (Named Binary Tag) no item.
*   **`getNBT(String key, Class<T> type)`:** Obtém um valor NBT do item.
*   **`hasNBTKey(String key)`:** Verifica se o item possui uma chave NBT específica.
*   **`removeNBTKey(String key)`:** Remove uma chave NBT do item.

## Loc (Localização)

### Métodos

*   **`getLocalizacao(Entity entidade)`:** Obtém a localização (`Location`) de uma entidade (`Player`, `Creature`, etc.).

```java
Location localizacaoJogador = Loc.getLocalizacao(player);
```

*   **`calcularDistancia(Location loc1, Location loc2)`:** Calcula a distância em blocos entre duas localizações.

```java
double distancia = Loc.calcularDistancia(localizacao1, localizacao2);
```

*   **`calcularDistanciaQuadrada(Location loc1, Location loc2)`:** Calcula a distância quadrada (mais eficiente para comparações) entre duas localizações.

```java
double distanciaQuadrada = Loc.calcularDistanciaQuadrada(localizacao1, localizacao2);
```

*   **`mesmoMundo(Location loc1, Location loc2)`:** Verifica se duas localizações estão no mesmo mundo.

```java
boolean mesmoMundo = Loc.mesmoMundo(localizacao1, localizacao2);
```

*   **`estaDentroDaArea(Location loc, Location canto1, Location canto2)`:** Verifica se uma localização está dentro de uma área retangular definida por dois cantos.

```java
boolean dentroDaArea = Loc.estaDentroDaArea(localizacao, canto1, canto2);
```

*   **`getLocalizacaoCentral(Location loc1, Location loc2)`:** Calcula o ponto central entre duas localizações.

```java
Location centro = Loc.getLocalizacaoCentral(localizacao1, localizacao2);
```

*   **`encontrarLocalizacaoMaisProxima(Location origem, Collection<Location> localizacoes)`:** Encontra a localização mais próxima de um conjunto de localizações a partir de uma origem.

```java
List<Location> locais = ...; // Lista de localizações
Location maisProxima = Loc.encontrarLocalizacaoMaisProxima(origem, locais);
```

*   **`calcularDirecao(Location de, Location para)`:** Calcula a direção (vetor) entre duas localizações.

```java
Vector direcao = Loc.calcularDirecao(localizacao1, localizacao2);
```

## Mensagem

### Métodos

*   **`formatar(String mensagem)`:** Formata a mensagem com cores e estilos (&a, &l, etc.).

```java
String mensagemFormatada = Mensagem.formatar("&aOlá, mundo!");
```

*   **`negrito(String mensagem)`:** Aplica negrito à mensagem.
*   **`italico(String mensagem)`:** Aplica itálico à mensagem.
*   **`sublinhado(String mensagem)`:** Aplica sublinhado à mensagem.
*   **`riscado(String mensagem)`:** Aplica riscado à mensagem.
*   **`formatarPlaceholders(String mensagem, String placeholder, String valor)`:** Substitui placeholders na mensagem (ex: %jogador% -> nome do jogador).

## NBT (Named Binary Tag)

### Métodos

*   **`adicionarAListaNBT(ItemStack item, String chaveLista, Object valor)`:** Adiciona um valor a uma lista NBT no item.

```java
NBT.adicionarAListaNBT(espada, "encantamentos", "afiado:5");
```

*   **`getListaNBT(ItemStack item, String chaveLista)`:** Obtém uma lista NBT do item.

```java
List<String> encantamentos = NBT.getListaNBT(espada, "encantamentos");
```

*   **`setarCompostoNBT(ItemStack item, String chaveComposto, NBTCompound composto)`:** Define um composto NBT no item.

```java
NBTCompound display = new NBTCompound();
display.setString("Name", "Espada Lendária");
NBT.setarCompostoNBT(espada, "display", display);
```

*   **`getCompostoNBT(ItemStack item, String chaveComposto)`:** Obtém um composto NBT do item.

```java
NBTCompound display = NBT.getCompostoNBT(espada, "display");
```

*   **`serializarItem(ItemStack item)`:** Serializa um item para uma string NBT.

```java
String nbtString = NBT.serializarItem(espada);
```

*   **`desserializarItem(ItemStack itemBase, String nbtString)`:** Desserializa uma string NBT em um item (**limitado a tags simples: boolean, int e string**).

```java
ItemStack espadaRecuperada = NBT.desserializarItem(new ItemStack(Material.DIAMOND_SWORD), nbtString);
```

**Observação:** A desserialização de NBT nesta versão da API é limitada. Para um suporte completo, considere atualizar o Spigot.

## ScoreBoard

### Métodos

*   **`criarScoreboard(String titulo)`:** Cria um novo scoreboard com o título especificado.

```java
Scoreboard scoreboard = ScoreBoard.criarScoreboard("Minhas Estatísticas");
```

*   **`atualizarLinha(Scoreboard scoreboard, int linha, String texto)`:** Atualiza uma linha do scoreboard (15 - linha superior, 1 - linha inferior).

```java
ScoreBoard.atualizarLinha(scoreboard, 15, "Nome: " + player.getName());
```

*   **`setarScoreboard(Player player, Scoreboard scoreboard)`:** Define o scoreboard para um jogador.

```java
ScoreBoard.setarScoreboard(player, scoreboard);
```

*   **`removerScoreboard(Player player)`:** Remove o scoreboard de um jogador.

```java
ScoreBoard.removerScoreboard(player);
```

*   **`limparScoreboard(Scoreboard scoreboard)`:** Limpa todas as linhas do scoreboard.

```java
ScoreBoard.limparScoreboard(scoreboard);
```

## Som

### Métodos

*   **`tocarSom(Player player, Sound som, float volume, float pitch)`:** Toca um som para um jogador.

```java
Som.tocarSom(player, Sound.ENTITY_ENDERDRAGON_GROWL, 1.0f, 1.0f); // Som de dragão
```

**Observações:**

*   Para sons personalizados, coloque o arquivo de som na pasta `sounds` do servidor e use o nome do arquivo (sem extensão) como argumento.
*   A lista de sons disponíveis pode variar dependendo da versão do Minecraft. Consulte a documentação do Bukkit para obter a lista completa.
