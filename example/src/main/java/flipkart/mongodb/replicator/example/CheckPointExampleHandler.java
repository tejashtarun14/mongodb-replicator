/*
 * Copyright 2012-2015, the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package flipkart.mongodb.replicator.example;

import com.google.common.collect.ImmutableMap;
import flipkart.mongo.replicator.core.interfaces.ICheckPointHandler;
import org.bson.BsonTimestamp;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by kishan.gajjar on 03/12/14.
 */
public class CheckPointExampleHandler implements ICheckPointHandler {
    ConcurrentHashMap<String, BsonTimestamp> checkpoint = new ConcurrentHashMap<String, BsonTimestamp>();

    @Override
    public void checkPoint(String replicaSetId, BsonTimestamp timestamp) {
        System.out.println("ReplicaSet::" + replicaSetId + ", timestamp::(" + timestamp.getTime() + "," + timestamp.getInc() + ")");
        checkpoint.put(replicaSetId, timestamp);
    }

    @Override
    public ImmutableMap<String, BsonTimestamp> getAllCheckPoints() {
        return ImmutableMap.copyOf(checkpoint);
    }

    @Override
    public BsonTimestamp getCheckPoint(String replicaSetId) {
        return checkpoint.get(replicaSetId);
    }

    @Override
    public int getCycleTimeinSecs() {
        return 10;
    }
}
