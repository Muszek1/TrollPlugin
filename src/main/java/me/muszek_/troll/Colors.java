package me.muszek_.troll;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class Colors {

  // Podstawowa metoda
  public static Component color(String args) {
    return LegacyComponentSerializer.legacyAmpersand().deserialize(args);
  }

  // NOWA METODA: Obsługuje zamienniki ("placeholder", "wartość")
  public static Component color(String text, String... replacements) {
    // Przechodzimy po argumentach parami
    for (int i = 0; i < replacements.length; i += 2) {
      // Zabezpieczenie, jeśli podasz nieparzystą liczbę argumentów
      if (i + 1 >= replacements.length) {
        break;
      }

      String target = replacements[i];
      String replacement = replacements[i + 1];

      text = text.replace(target, replacement);
    }
    return color(text);
  }
}