package dev.ferriarnus.tinkersjewelry.network;

import dev.ferriarnus.tinkersjewelry.TinkersJewelry;
import net.minecraftforge.network.NetworkDirection;
import slimeknights.mantle.network.NetworkWrapper;

public class JewelryNetwork extends NetworkWrapper {
    private static JewelryNetwork instance;

    public JewelryNetwork() {
        super(TinkersJewelry.getResource("network"), "1.0");
    }

    public static JewelryNetwork getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Attempt to call network getInstance before network is setup");
        }
        return instance;
    }

    public static void setup() {
        if (instance != null) {
            return;
        }
        instance = new JewelryNetwork();

        instance.registerPacket(SubSpacePacket.class, SubSpacePacket::new, NetworkDirection.PLAY_TO_SERVER);

    }
}
