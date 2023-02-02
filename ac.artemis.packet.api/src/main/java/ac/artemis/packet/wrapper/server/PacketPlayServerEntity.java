package ac.artemis.packet.wrapper.server;

import ac.artemis.packet.wrapper.PacketServer;

public interface PacketPlayServerEntity extends PacketServer {

    int getEntityId();
    boolean isOnGround();
}