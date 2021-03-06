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

package flipkart.mongo.replicator.core.model;

import com.google.common.base.Optional;
import org.bson.BsonTimestamp;
import org.bson.Document;

/**
 * Created by pradeep on 10/10/14.
 */
public class ReplicationEvent {
    public final Operation operation;
    public final BsonTimestamp v;
    public final long h;
    public final String databaseName;
    public final String collectionName;
    public final String namespace;
    public final Document eventData;
    public final Optional<Document> objectId;

    public ReplicationEvent(String operation, BsonTimestamp v, long h, String namespace, Document eventData, Document objectId) {
        this.v = v;
        this.h = h;
        this.eventData = eventData;
        this.objectId = Optional.fromNullable(objectId);
        this.namespace = namespace;
        String[] namespaceSplit = namespace.split("\\.", 2);
        this.databaseName = namespaceSplit[0];
        this.collectionName = namespaceSplit[1];
        this.operation = getOperationType(operation, eventData);
    }

    private Operation getOperationType(String operation, Document eventData) {
        Operation operationType = Operation.getOperationType(operation);
        if (operationType == Operation.UPDATE && eventData.containsKey("_id")) {
            /**
             * if update operation payload has _id, then it should be replace operation from java driver
             */
            operationType = Operation.REPLACE;
        }
        return operationType;
    }

    @Override
    public String toString() {
        return "ReplicationEvent{" +
                "operation=" + operation +
                ", v=" + v +
                ", h=" + h +
                ", namespace='" + namespace + '\'' +
                ", databaseName='" + databaseName + '\'' +
                ", collectionName='" + collectionName + '\'' +
                ", eventData=" + eventData +
                ", objectId=" + objectId +
                '}';
    }
}
