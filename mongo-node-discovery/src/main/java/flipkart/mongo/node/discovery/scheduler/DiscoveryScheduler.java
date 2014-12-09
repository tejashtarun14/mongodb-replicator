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

package flipkart.mongo.node.discovery.scheduler;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import flipkart.mongo.node.discovery.interfaces.IDiscoveryCallback;
import flipkart.mongo.replicator.core.model.ReplicaSetConfig;

import java.util.List;

/**
 * Created by kishan.gajjar on 08/12/14.
 */
public abstract class DiscoveryScheduler {
    private List<IDiscoveryCallback> discoveryCallbacks = Lists.newArrayList();

    protected void publish(ImmutableList<ReplicaSetConfig> replicaSetConfigs) {
        for (IDiscoveryCallback discoveryCallback : this.discoveryCallbacks) {
            discoveryCallback.updateReplicaSetConfigs(replicaSetConfigs);
        }
    }

    public void registerCallback(IDiscoveryCallback discoveryCallback) {
        discoveryCallbacks.add(discoveryCallback);
    }
}
