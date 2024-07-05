package txdev.txapibukkit.api;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import java.util.Collection;

public class Loc {
    // Obter a localização de uma entidade (jogador ou outra entidade)
    public static Location getLocalizacao(Entity entidade) {
        if (entidade == null) {
            throw new IllegalArgumentException("Entidade não pode ser nula.");
        }
        return entidade.getLocation();
    }

    // Calcular a distância entre duas localizações
    public static double calcularDistancia(Location loc1, Location loc2) {
        if (loc1 == null || loc2 == null) {
            throw new IllegalArgumentException("Localizações não podem ser nulas.");
        }
        if (!mesmoMundo(loc1, loc2)) {
            throw new IllegalArgumentException("Localizações devem estar no mesmo mundo.");
        }
        return loc1.distance(loc2);
    }

    // Calcular a distância quadrada entre duas localizações (mais eficiente para comparações)
    public static double calcularDistanciaQuadrada(Location loc1, Location loc2) {
        if (loc1 == null || loc2 == null) {
            throw new IllegalArgumentException("Localizações não podem ser nulas.");
        }
        if (!mesmoMundo(loc1, loc2)) {
            throw new IllegalArgumentException("Localizações devem estar no mesmo mundo.");
        }
        return loc1.distanceSquared(loc2);
    }

    // Verificar se duas localizações estão no mesmo mundo
    public static boolean mesmoMundo(Location loc1, Location loc2) {
        if (loc1 == null || loc2 == null) {
            throw new IllegalArgumentException("Localizações não podem ser nulas.");
        }
        return loc1.getWorld() == loc2.getWorld();
    }

    // Verificar se uma localização está dentro de uma área retangular
    public static boolean estaDentroDaArea(Location loc, Location canto1, Location canto2) {
        if (loc == null || canto1 == null || canto2 == null) {
            throw new IllegalArgumentException("Localizações não podem ser nulas.");
        }
        if (!mesmoMundo(loc, canto1) || !mesmoMundo(loc, canto2)) {
            throw new IllegalArgumentException("Localizações devem estar no mesmo mundo.");
        }

        double minX = Math.min(canto1.getX(), canto2.getX());
        double maxX = Math.max(canto1.getX(), canto2.getX());
        double minY = Math.min(canto1.getY(), canto2.getY());
        double maxY = Math.max(canto1.getY(), canto2.getY());
        double minZ = Math.min(canto1.getZ(), canto2.getZ());
        double maxZ = Math.max(canto1.getZ(), canto2.getZ());

        return loc.getX() >= minX && loc.getX() <= maxX &&
                loc.getY() >= minY && loc.getY() <= maxY &&
                loc.getZ() >= minZ && loc.getZ() <= maxZ;
    }

    // Calcular o ponto central entre duas localizações
    public static Location getLocalizacaoCentral(Location loc1, Location loc2) {
        if (loc1 == null || loc2 == null) {
            throw new IllegalArgumentException("Localizações não podem ser nulas.");
        }
        if (!mesmoMundo(loc1, loc2)) {
            throw new IllegalArgumentException("Localizações devem estar no mesmo mundo.");
        }

        double x = (loc1.getX() + loc2.getX()) / 2;
        double y = (loc1.getY() + loc2.getY()) / 2;
        double z = (loc1.getZ() + loc2.getZ()) / 2;
        World mundo = loc1.getWorld(); // Ambos os mundos são iguais, então podemos usar qualquer um
        return new Location(mundo, x, y, z);
    }

    // Encontrar a localização mais próxima de um conjunto de localizações
    public static Location encontrarLocalizacaoMaisProxima(Location origem, Collection<Location> localizacoes) {
        if (origem == null || localizacoes == null || localizacoes.isEmpty()) {
            throw new IllegalArgumentException("Parâmetros inválidos.");
        }

        Location localizacaoMaisProxima = null;
        double menorDistancia = Double.MAX_VALUE;

        for (Location loc : localizacoes) {
            if (!mesmoMundo(origem, loc)) {
                continue; // Ignorar localizações em mundos diferentes
            }

            double distancia = calcularDistanciaQuadrada(origem, loc);
            if (distancia < menorDistancia) {
                menorDistancia = distancia;
                localizacaoMaisProxima = loc;
            }
        }

        return localizacaoMaisProxima;
    }

    // Calcular a direção (vetor) entre duas localizações
    public static Vector calcularDirecao(Location de, Location para) {
        if (de == null || para == null) {
            throw new IllegalArgumentException("Localizações não podem ser nulas.");
        }
        if (!mesmoMundo(de, para)) {
            throw new IllegalArgumentException("Localizações devem estar no mesmo mundo.");
        }

        return para.toVector().subtract(de.toVector()).normalize();
    }
}
