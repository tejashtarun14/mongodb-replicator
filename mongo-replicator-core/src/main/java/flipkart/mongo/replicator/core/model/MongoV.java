package flipkart.mongo.replicator.core.model;

import java.util.Optional;

/**
 * Created by pradeep on 17/10/14.
 */
public class MongoV {
    public final int major;
    public final int minor;
    public final Optional<Integer> patch;

    public MongoV(int major, int minor, int patch) {
        this(major, minor, Optional.of(patch));
    }

    protected MongoV(int major, int minor, Optional<Integer> patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    public MongoV(int major, int minor) {
        this(major, minor, Optional.<Integer>empty());
    }


    public boolean isGte(MongoV v) {
        return (major >= v.major) ||
                (major == v.major && minor >= v.minor) ||
                (major == v.major && minor == v.minor && ( (patch.isPresent() && v.patch.isPresent()) && patch.get() >= v.patch.get() ));
    }

    public boolean isLte(MongoV v) {
        return (major <= v.major) ||
                (major == v.major && minor <= v.minor) ||
                (major == v.major && minor == v.minor && ( (patch.isPresent() && v.patch.isPresent()) && patch.get() <= v.patch.get() ));
    }
}