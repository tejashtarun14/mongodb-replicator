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

package flipkart.mongo.replicator.core.threadfactory;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.HashMap;
import java.util.concurrent.ThreadFactory;

/**
 * Created by kishan.gajjar on 25/01/15.
 */
public class TaskThreadFactoryBuilder {

    private static HashMap<String, ThreadFactory> threadFactoryPool = Maps.newHashMap();

    public static ThreadFactory threadFactoryInstance(String threadNamePattern) {
        if (!threadFactoryPool.containsKey(threadNamePattern)) {
            synchronized (TaskThreadFactoryBuilder.class) {
                if (!threadFactoryPool.containsKey(threadNamePattern)) {
                    ThreadFactoryBuilder threadFactoryBuilder = new ThreadFactoryBuilder().setNameFormat(threadNamePattern);
                    threadFactoryPool.put(threadNamePattern, threadFactoryBuilder.build());
                }
            }
        }

        return threadFactoryPool.get(threadNamePattern);
    }
}
