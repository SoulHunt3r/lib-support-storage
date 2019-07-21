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
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Generic description
 *
 * @author Fedorov Konstantin (mr.fedorov.konstantin@mail.ru)
 * @version 0.1.0 [MAJOR.MINOR.PATCH]
 * Created on 21.07.2019.
 */
public class UUIDMemoryStorage<V> implements UUIDStorage<V> {
    private Map<UUID, V> map;
    private Instant lastUpdate;
    private boolean concurrent;

    public UUIDMemoryStorage() {
        this(false);
    }

    public UUIDMemoryStorage(boolean concurrent) {
        this.concurrent = concurrent;
        if (concurrent) {
            map = new ConcurrentHashMap<>();
        } else {
            map = new HashMap<>();
        }
    }

    public UUIDMemoryStorage(UUIDMemoryStorage<V> storage) {
        this.concurrent = storage.concurrent;
        this.lastUpdate = storage.lastUpdate;
        if (this.concurrent) {
            this.map = new ConcurrentHashMap<>();
        } else {
            this.map = new HashMap<>();
        }

        this.map.putAll(storage.map);
    }


    @Override
    public UUID create(V value) {
        UUID uuid;
        do {
            uuid = UUID.randomUUID();
        } while (map.containsKey(uuid));

        map.put(uuid, value);
        lastUpdate = Instant.now();

        return uuid;
    }

    @Override
    public V create(UUID key, V value) {
        return map.put(key, value);
    }

    @Override
    public V read(UUID key) {
        return map.get(key);
    }

    @Override
    public V update(UUID key, V value) {
        V result = map.replace(key, value);

        if (result != null) lastUpdate = Instant.now();

        return result;
    }

    @Override
    public V delete(UUID key) {
        V result = map.remove(key);

        if (result != null) lastUpdate = Instant.now();

        return result;
    }

    @Override
    public boolean isExist(UUID key) {
        return map.containsKey(key);
    }

    @Override
    public boolean isExistValue(V value) {
        return map.containsValue(value);
    }

    @Override
    public UUID searchFirst(V value) {
        for (Map.Entry<UUID, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) return entry.getKey();
        }

        return null;
    }

    @Override
    public List<UUID> search(V value) {
        List<UUID> list = new LinkedList<>();

        for (Map.Entry<UUID, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) list.add(entry.getKey());
        }

        return list;
    }

    @Override
    public Map<UUID, V> getMap() {
        return map;
    }

    @Override
    public Instant getLastUpdate() {
        return lastUpdate;
    }

    public boolean isConcurrent() {
        return concurrent;
    }
}
