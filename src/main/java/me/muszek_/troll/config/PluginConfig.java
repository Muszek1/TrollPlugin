package me.muszek_.troll.config;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;

@Header("####################################################")
@Header("#                                                  #")
@Header("#   Author: Muszek                                 #")
@Header("#   If you need help, join to discord              #")
@Header("#   server: https://discord.com/invite/cT5MxqAYTd  #")
@Header("#                                                  #")
@Header("####################################################")
public class PluginConfig extends OkaeriConfig {

    public FireSection Fire = new FireSection();
    public KnockbackSection Knockback = new KnockbackSection();
    public FreezeSection Freeze = new FreezeSection();
    public DiamondSection Diamond = new DiamondSection();
    public CookieSection Cookie = new CookieSection();

    public static class FireSection extends OkaeriConfig {
        public int Default_Duration = 3;
    }

    public static class KnockbackSection extends OkaeriConfig {
        public String Item_Name = "&eKnockback Stick";
    }

    public static class FreezeSection extends OkaeriConfig {
        public int Default_Duration = 5;
    }

    public static class DiamondSection extends OkaeriConfig {
        public int Duration = 10;
    }

    public static class CookieSection extends OkaeriConfig {
        public String Item_Name = "&6&lInfinite Cookie 🍪";
        public boolean Glow = true;
    }
}