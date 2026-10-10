package me.muszek_.troll.config;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;

@Header("####################################################")
@Header("#                                                  #")
@Header("#   EpicTroll - Messages Configuration             #")
@Header("#                                                  #")
@Header("####################################################")
public class MessageConfig extends OkaeriConfig {

    public String Not_A_Player_Message = "&c> &fYou must be a player to use this command!";
    public String Player_Not_Found = "&c> &fPlayer %player% not found!";
    public String Mob_Not_Found = "&c> &fMob %mob% not found!";
    public String No_Permissions = "&c> &fYou do not have permissions to do that!";
    public String Plugin_Reloaded = "&a> &fYou have successfully reloaded the EpicTroll Plugin!";
    public String Wrong_Number = "&c> &fYou have provided a wrong number!";
    public String Invalid_Usage = "&c> &fYou have provided an invalid usage!";

    public FireSection Fire = new FireSection();
    public MobSection Mob = new MobSection();
    public AnvilSection Anvil = new AnvilSection();
    public FreezeSection Freeze = new FreezeSection();
    public ExplodePlayerSection ExplodePlayer = new ExplodePlayerSection();
    public AppleSection Apple = new AppleSection();
    public DiamondSection Diamond = new DiamondSection();
    public LaunchSection Launch = new LaunchSection();
    public KnockbackSection Knockback = new KnockbackSection();
    public CookieSection Cookie = new CookieSection();
    public JumplockSection Jumplock = new JumplockSection();
    public FakeopSection Fakeop = new FakeopSection();
    public BlockcraftSection Blockcraft = new BlockcraftSection();
    public ReversechatSection Reversechat = new ReversechatSection();
    public AnnoysoundsSection Annoysounds = new AnnoysoundsSection();
    public DropinvSection Dropinv = new DropinvSection();
    public BlocktooluseSection Blocktooluse = new BlocktooluseSection();
    public FakexpSection Fakexp = new FakexpSection();
    public ShuffleSection Shuffle = new ShuffleSection();
    public PumpkinSection Pumpkin = new  PumpkinSection();
    public CobwebSection Cobweb = new CobwebSection();

    public static class FireSection extends OkaeriConfig {
        public String Message = "&e> &fYou fired %player% for %time% seconds";
        public String Invalid_Duration = "&c> &fInvalid duration in second argument!";
    }

    public static class MobSection extends OkaeriConfig {
        public String Message = "&e> &fYou spawned %mob%(s) behind %player%";
    }

    public static class AnvilSection extends OkaeriConfig {
        public String Message = "&e> &fYou spawned an anvil above %player%";
        public String Error = "&c> &fYou cannot spawn anvil - the block above is not air!";
    }

    public static class FreezeSection extends OkaeriConfig {
        public String Message = "&b> &fYou froze %player% for %time% seconds";
        public String Target_Message = "&b> &bYou are FROZEN!";
    }

    public static class ExplodePlayerSection extends OkaeriConfig {
        public String Message = "&e> You exploded %player%!";
        public String Going_To_Explode = "&c> &cYOU ARE GOING TO EXPLODE!";
        public String You_Were_Blown_Up = "&c> &cYOU EXPLODED!";
    }

    public static class AppleSection extends OkaeriConfig {
        public String Eaten = "&c> &fYou ate &2&lPOISONED &fapple!";
        public String Given = "&e> &fYou gave %amount% poisoned apple to %player%";
    }

    public static class DiamondSection extends OkaeriConfig {
        public String Given = "&e> You spawned untouchable diamond around %player%";
    }

    public static class LaunchSection extends OkaeriConfig {
        public String Launched = "&e> You launched %player% into sky!";
    }

    public static class KnockbackSection extends OkaeriConfig {
        public String Given = "&e> &fYou gave %amount% knockback stick(s) to %player%";
    }

    public static class CookieSection extends OkaeriConfig {
        public String Given = "&e> &fYou gave %amount% infinite cookie(s) to %player%";
    }

    public static class JumplockSection extends OkaeriConfig {
        public String Lock = "&e> &fYou locked jumps for %player%";
        public String Unlock = "&e> &fYou unlocked jumps for %player%";
    }

    public static class FakeopSection extends OkaeriConfig {
        public String Message_Sent = "&7&o[Server: Made %player% a server operator]";
        public String Message_Confirmation = "&e> You send fake message to %player%";
    }

    public static class BlockcraftSection extends OkaeriConfig {
        public String Block = "&e> &fYou blocked the possibility of crafting for %player%";
        public String Unblock = "&e> &fYou unblocked the possibility of crafting for %player%";
    }

    public static class ReversechatSection extends OkaeriConfig {
        public String Reverse = "&e> &fYou reversed players' messages for %player%";
        public String Unreversed = "&e> &fYou restored players' chat to normal for %player%";
    }

    public static class AnnoysoundsSection extends OkaeriConfig {
        public String Sent = "&e> &fYou sent annoy sounds to %player%";
    }

    public static class DropinvSection extends OkaeriConfig {
        public String Dropped = "&e> &fYou dropped items from %player%'s inventory";
    }

    public static class BlocktooluseSection extends OkaeriConfig {
        public String Block = "&e> &fYou blocked use of tools for %player%";
        public String Unlock = "&e> &fYou unlocked use of tools for %player%";
    }

    public static class FakexpSection extends OkaeriConfig {
        public String Given = "&e> &fYou gave fake xp to %player%";
    }

    public static class ShuffleSection extends OkaeriConfig {
        public String Sent = "&e> &fYou shuffled %player%'s inventory!";
    }

    public static class PumpkinSection extends OkaeriConfig {
        public String Put = "&e> &fYou put a pumpkin on the %player%'s head!";
    }

    public static class CobwebSection extends OkaeriConfig {
        public String Sent = "&e> &fYou have created a cobweb around %player%!";
    }
}