/*
 *    Copyright 2019 Konstantin Fedorov
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package ru.dfkzbt.support.storage;

/**
 * Generic description
 *
 * @author Fedorov Konstantin (mr.fedorov.konstantin@mail.ru)
 * @version 0.1.0 [MAJOR.MINOR.PATCH]
 * Created on 21.07.2019.
 */
public class UUIDStorageFactory {
    public static <V> UUIDStorage<V> create(Class<V> clazz) {
        return create(Storage.Type.MEMORY, clazz);
    }

    public static <V> UUIDStorage<V> create(Storage.Type type, Class<V> clazz) {
        UUIDStorage<V> storage;

        switch (type) {
            case MEMORY:
                storage = new UUIDMemoryStorage<>(false);
                break;
            case MEMORY_M:
                storage = new UUIDMemoryStorage<>(true);
                break;
            case SERVICE:
            case PROXY_SERVICE:
            case PROXY_SERVICE_M:
                throw new IllegalStateException("not implemented: " + type.toString());
            default:
                storage = new UUIDMemoryStorage<>();
                break;
        }

        return storage;
    }
}
