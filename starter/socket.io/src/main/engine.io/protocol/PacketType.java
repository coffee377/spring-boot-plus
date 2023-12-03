package protocol;

public class PacketType {
    public static final PacketType open = new PacketType("open", 0, "Used during the handshake");
    public static final PacketType close = new PacketType("close", 1, "Used to indicate that a transport can be closed");
    public static final PacketType ping = new PacketType("ping", 2, "Used in the heartbeat mechanism");
    public static final PacketType pong = new PacketType("pong", 3, "Used in the heartbeat mechanism");
    public static final PacketType message = new PacketType("message", 4, "Used to send a payload to the other side");
    public static final PacketType upgrade = new PacketType("upgrade", 5, "Used during the upgrade process");
    public static final PacketType noop = new PacketType("noop", 6, "Used during the upgrade process");


    private final String type;
    private final int id;
    private final String usage;

    public PacketType(String type, int id, String usage) {
        this.type = type;
        this.id = id;
        this.usage = usage;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getUsage() {
        return usage;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
