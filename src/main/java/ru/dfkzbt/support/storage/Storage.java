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

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * Generic description
 *
 * @author Fedorov Konstantin (mr.fedorov.konstantin@mail.ru)
 * @version 0.1.0 [MAJOR.MINOR.PATCH]
 * Created on 21.07.2019.
 */
public interface Storage<K, V> {
    /**
     * create new unique key
     *
     * @param value
     * @return
     */
    K create(V value);

    /**
     * put new value by key
     * sim to update
     *
     * @param key
     * @param value
     * @return
     */
    V create(K key, V value);

    /**
     * get value by key
     *
     * @param key
     * @return
     */
    V read(K key);

    /**
     * update value by key
     *
     * @param key
     * @param value
     * @return
     */
    V update(K key, V value);

    /**
     * delete value by key
     *
     * @param key
     * @return
     */
    V delete(K key);


    boolean isExist(K key);

    boolean isExistValue(V value);

    /**
     * get first found key by value
     *
     * @param value
     * @return
     */
    K searchFirst(V value);

    List<K> search(V value);

    /**
     * get map representation of storage
     *
     * @return
     */
    Map<K, V> getMap();

    Instant getLastUpdate();

    enum Type {MEMORY, MEMORY_M, SERVICE, PROXY_SERVICE, PROXY_SERVICE_M}
}
