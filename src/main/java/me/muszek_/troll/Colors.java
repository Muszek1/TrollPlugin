package me.muszek_.troll;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class Colors {

  public static Component color(String args) {
    return LegacyComponentSerializer.legacyAmpersand().deserialize(args);
  }

  public static Component color(String text, String... replacements) {
    for (int i = 0; i < replacements.length; i += 2) {
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