package ac.artemis.packet.wrapper.server;

import ac.artemis.packet.wrapper.PacketServer;

public interface PacketPlayServerEntityRelLook extends PacketPlayServerEntity {

    byte getYaw();
    byte getPitch();
}
