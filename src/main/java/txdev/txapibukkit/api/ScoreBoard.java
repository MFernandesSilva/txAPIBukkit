package txdev.txapibukkit.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreBoard {
    public static Scoreboard criarScoreboard(String titulo) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("display", "dummy"); // Remove o terceiro argumento
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(titulo); // Define o título aqui
        return scoreboard;
    }

    public static void atualizarLinha(Scoreboard scoreboard, int linha, String texto) {
        Objective objective = scoreboard.getObjective("display");
        if (objective == null) {
            return; // Objetivo não encontrado
        }
        Score score = objective.getScore(texto);
        score.setScore(linha);
    }

    public static void setarScoreboard(Player player, Scoreboard scoreboard) {
        player.setScoreboard(scoreboard);
    }

    public static void removerScoreboard(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }

    public static void limparScoreboard(Scoreboard scoreboard) {
        Objective objective = scoreboard.getObjective("display");
        if (objective != null) {
            for (String entry : objective.getScoreboard().getEntries()) {
                objective.getScoreboard().resetScores(entry);
            }
        }
    }
}
