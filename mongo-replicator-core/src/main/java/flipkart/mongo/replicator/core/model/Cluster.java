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

import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * Created by pradeep on 29/10/14.
 */
public class Cluster {

    private ImmutableList<ReplicaSetConfig> replicaSets;
    public final ImmutableList<Node> cfgsvrs;

    public Cluster(List<ReplicaSetConfig> replicaSets, List<Node> cfgsvrs) {
        this.replicaSets = ImmutableList.copyOf(replicaSets);
        this.cfgsvrs = ImmutableList.copyOf(cfgsvrs);
    }

    public ImmutableList<ReplicaSetConfig> getReplicaSets() {
        return replicaSets;
    }

    public void setReplicaSets(List<ReplicaSetConfig> replicaSets) {
        this.replicaSets = ImmutableList.copyOf(replicaSets);
    }
}
