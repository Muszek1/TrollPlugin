package me.muszek_.troll.tasks;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;

public class AnnoySoundsTask extends BukkitRunnable {

    private final Player target;
    private final List<Sound> annoyingSounds;
    private final Random random = new Random();
    private int count = 0;

    public AnnoySoundsTask(Player target, List<Sound> annoyingSounds) {
        this.target = target;
        this.annoyingSounds = annoyingSounds;
    }

    @Override
    public void run() {
        if (count >= 20 || !target.isOnline()) {
            cancel();
            return;
        }
        Sound sound = annoyingSounds.get(random.nextInt(annoyingSounds.size()));
        target.playSound(target.getLocation(), sound, 1.0f, 1.0f);
        count++;
    }
}
