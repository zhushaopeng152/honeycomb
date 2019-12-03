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

package io.fd.honeycomb.translate.util.read.cache;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;

/**
 * Provides keys for provided {@code InstanceIdentifier}
 */
public interface CacheKeyFactory<U> {

    /**
     * Construct key accordingly to provided {@code InstanceIdentifier<?>} and dumpParams
     */
    @Nonnull
    String createKey(@Nonnull final InstanceIdentifier<?> actualContextIdentifier, @Nullable final U dumpParams);

    /**
     * Returns type of data, for which is this factory creating keys
     */
    @Nonnull
    Class<?> getCachedDataType();
}
