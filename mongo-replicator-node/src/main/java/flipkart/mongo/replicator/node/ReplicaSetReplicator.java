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

package flipkart.mongo.replicator.node;

import com.google.common.util.concurrent.AbstractService;
import flipkart.mongo.replicator.core.model.ReplicaSetConfig;
import flipkart.mongo.replicator.core.model.TaskContext;
import flipkart.mongo.replicator.core.threadfactory.TaskThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by pradeep on 09/10/14.
 */
public class ReplicaSetReplicator extends AbstractService {
    private static final Logger logger = LoggerFactory.getLogger(ReplicaSetReplicator.class);

    private final String THREAD_NAME_PATTERN = "mongo-replicaset-replicator-%d";
    private final ExecutorService replicatorExecutor;
    private final ReplicationTask.ReplicationTaskFactory replicationTaskFactory;

    public ReplicaSetReplicator(TaskContext taskContext, ReplicaSetConfig rsConfig) {
        ThreadFactory threadFactory = TaskThreadFactoryBuilder.threadFactoryInstance(THREAD_NAME_PATTERN);
        this.replicatorExecutor = Executors.newSingleThreadExecutor(threadFactory);
        this.replicationTaskFactory = new ReplicationTask.ReplicationTaskFactory(taskContext, rsConfig);
    }

    @Override
    protected void doStart() {
        try {
            replicatorExecutor.submit(replicationTaskFactory.instance());
        } catch (Exception e) {
            logger.error("Exception in replicator thread", e);
        }
    }

    @Override
    protected void doStop() {
        replicatorExecutor.shutdownNow();
    }
}
