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

import com.google.common.base.Function;
import flipkart.mongo.replicator.core.model.Operation;
import flipkart.mongo.replicator.core.model.ReplicationEvent;

/**
 * Created by kishan.gajjar on 03/12/14.
 */
public class OplogExampleFilter implements Function<ReplicationEvent, Boolean> {

    @Override
    public Boolean apply(ReplicationEvent replicationEvent) {
        return replicationEvent.namespace.equalsIgnoreCase("cv.o") && replicationEvent.operation.equals(Operation.INSERT);
    }
}
