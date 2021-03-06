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

package flipkart.mongo.replicator.core.interfaces;

import flipkart.mongo.replicator.core.exceptions.MongoReplicatorException;
import flipkart.mongo.replicator.core.model.ReplicationEvent;

/**
 * Created by pradeep on 10/10/14.
 */
public interface IReplicationHandler {

    void replicate(ReplicationEvent replicationEvent) throws MongoReplicatorException;

}
