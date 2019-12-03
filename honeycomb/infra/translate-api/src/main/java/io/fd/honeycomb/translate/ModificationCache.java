/*
 * Copyright (c) 2016 Cisco and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.fd.honeycomb.translate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.concurrent.ThreadSafe;

/**
 * Simple context class that provides transient storage during one or more read/write operations.
 * Internally Thread-save.
 */
@ThreadSafe
public class ModificationCache implements AutoCloseable {

    protected final Map<Object, Object> map;

    public ModificationCache() {
        map = new ConcurrentHashMap<>();
    }

    public Object get(final Object o) {
        return map.get(o);
    }

    public boolean containsKey(final Object o) {
        return map.containsKey(o);
    }

    public Object put(final Object o, final Object o2) {
        return map.put(o, o2);
    }

    @Override
    public void close() {
        map.clear();
    }
}
