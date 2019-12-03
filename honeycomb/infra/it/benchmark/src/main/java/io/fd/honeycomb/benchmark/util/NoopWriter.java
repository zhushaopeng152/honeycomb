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

package io.fd.honeycomb.benchmark.util;

import io.fd.honeycomb.translate.write.WriteContext;
import io.fd.honeycomb.translate.write.Writer;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

/**
 * Noop writer suitable for lists as well.
 */
@NotThreadSafe
public final class NoopWriter<T extends DataObject> implements Writer<T> {

    private final InstanceIdentifier<T> id;
    private long counter = 0;

    public NoopWriter(final InstanceIdentifier<T> id) {
        this.id = id;
    }

    @Override
    public void processModification(@Nonnull final InstanceIdentifier<? extends DataObject> id,
                       @Nullable final DataObject dataBefore,
                       @Nullable final DataObject dataAfter,
                       @Nonnull final WriteContext ctx) {
        counter++;
        // NOOP
    }

    @Nonnull
    @Override
    public InstanceIdentifier<T> getManagedDataObjectType() {
        return id;
    }

    @Override
    public String toString() {
        return "NoopWriter{" +
                id.getTargetType().getSimpleName() +
                ", counter=" + counter +
                '}';
    }

    @Override
    public boolean supportsDirectUpdate() {
        return true;
    }
}
