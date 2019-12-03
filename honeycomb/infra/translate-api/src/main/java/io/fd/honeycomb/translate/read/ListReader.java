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

package io.fd.honeycomb.translate.read;

import com.google.common.annotations.Beta;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.binding.Identifiable;
import org.opendaylight.yangtools.yang.binding.Identifier;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;

/**
 * List reader, allowing read of all the elements.
 *
 * @param <D> Specific DataObject derived type, that is handled by this reader
 */
@Beta
public interface ListReader
        <D extends DataObject & Identifiable<K>, K extends Identifier<D>, B extends Builder<D>> extends Reader<D, B> {

    /**
     * Read all elements in this list.
     *
     * @param id Wildcarded identifier of list managed by this reader
     * @param ctx Read context
     *
     * @return List of all entries in this list
     * @throws ReadFailedException if read was unsuccessful
     */
    @Nonnull
    List<D> readList(@Nonnull final InstanceIdentifier<D> id, @Nonnull final ReadContext ctx)
            throws ReadFailedException;

    /**
     * Get IDs for all entries in the list.
     */
    List<K> getAllIds(@Nonnull InstanceIdentifier<D> id, @Nonnull ReadContext ctx)
            throws ReadFailedException;

    /**
     * Merge read data into provided parent builder.
     */
    void merge(@Nonnull final Builder<? extends DataObject> builder, @Nonnull final List<D> readData);

    @Override
    default void merge(@Nonnull final Builder<? extends DataObject> parentBuilder, @Nonnull final D readValue) {
        merge(parentBuilder, Collections.singletonList(readValue));
    }
}
