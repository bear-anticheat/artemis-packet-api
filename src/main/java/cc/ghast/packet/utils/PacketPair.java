package cc.ghast.packet.utils;

import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.PacketInfo;
import cc.ghast.packet.wrapper.packet.PacketInformation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ghast
 * @since 19/08/2020
 * Artemis © 2020
 */

@AllArgsConstructor
@Data
public class PacketPair {
    private final Map<Integer, PacketInfo> client;
    private final Map<Integer, PacketInfo> server;

    /**
     * Packet pair that contains both the client and the server packets. This is used to dynamically allocate
     * and search for packets for a specific protocol. This unfortunately requires the protocols to be hand-written
     * every single time. Job must be done as ViaVersion is just not working out and is flushing the packets
     * pre-emptively.
     * @param client PacketInfo array of every single packet which is received from the client. Can be empty
     * @param server PacketInfo array of every single packet which is sent to the client. Can be empty
     */
    @SneakyThrows
    public PacketPair(PacketInfo[] client, PacketInfo[] server) {
        /*
         * Here we simply initialize the hashmap (not a weak one, we want to conserve this even if the reference is
         * null). We then add as the key the ID of the packet and associate to this key a packet info.
         * All the packets contained in here are packets issued BY the client TOWARDS the server.
         * (Client [Game] -> Server [NMS])
         */
        this.client = new HashMap<>();

        for (PacketInfo packetInfo : client) {

            /*
             * By having a null packet, we run into the risk of breaking and crashing the pipeline when trying
             * to process the information. Henceforth, we must preemptively prevent such behavior from happening.
             */
            if (packetInfo == null) {
                throw new IllegalStateException("Packet information cannot be null.");
            }

            /*
             * Packets containing the same id is not possible. This overriding is prone to issues henceforth
             * we must preemptively prevent it.
             */
            final boolean invalid = this.client.containsKey(packetInfo.getId());

            if (invalid) {
                throw new IllegalStateException("Duplicate packets of ID " + packetInfo.getId());
            }

            /*
             * Once we passed every single sanitization checks, we can just add it to the map.
             */
            this.client.put(packetInfo.getId(), packetInfo);
        }

        /*
         * Here we simply initialize the hashmap (not a weak one, we want to conserve this even if the reference is
         * null). We then add as the key the ID of the packet and associate to this key a packet info.
         * All the packets contained in here are packets issued BY the server TOWARDS the client.
         * (Server [NMS] -> Client [Game])
         */
        this.server = new HashMap<>();

        for (PacketInfo packetInfo : server) {
            /*
             * By having a null packet, we run into the risk of breaking and crashing the pipeline when trying
             * to process the information. Henceforth, we must preemptively prevent such behavior from happening.
             */
            if (packetInfo == null) {
                throw new IllegalStateException("Packet information cannot be null.");
            }

            /*
             * Packets containing the same id is not possible. This overriding is prone to issues henceforth
             * we must preemptively prevent it.
             */
            final boolean invalid = this.server.containsKey(packetInfo.getId());

            if (invalid) {
                throw new IllegalStateException("Duplicate packets of ID " + packetInfo.getId());
            }

            /*
             * Once we passed every single sanitization checks, we can just add it to the map.
             */
            this.server.put(packetInfo.getId(), packetInfo);
        }

    }
}
