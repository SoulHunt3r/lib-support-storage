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

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dfkzbt.support.map.MapUtils;

import java.util.Map;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Generic description
 *
 * @author Fedorov Konstantin (mr.fedorov.konstantin@mail.ru)
 * @version 0.1.0 [MAJOR.MINOR.PATCH]
 * Created on 21.07.2019.
 */
public class UUIDMemoryStorageTest {
    private final static Logger logger = LoggerFactory.getLogger(UUIDMemoryStorageTest.class);

    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    @Test
    public void createByValue() {
        String value = "some sample string";

        UUIDStorage<String> storage = new UUIDMemoryStorage<>();

        UUID actual = storage.create(value);

        MapUtils.printMap(storage.getMap());

        assertEquals(1, storage.getMap().size());
        assertTrue(storage.getMap().containsKey(actual));
        assertTrue(storage.getMap().containsValue(value));
    }

    @Test
    public void createByValue1M() {
        String value = "some sample string";
        int runs = 1000000;
        //int runs = 5;

        UUIDStorage<String> storage = new UUIDMemoryStorage<>();


        for (int i = 0; i < runs; i++) {
            storage.create(value);
        }

        //MapUtils.printMap(storage.getMap());
        logger.debug("storage size: {}", storage.getMap().size());

        assertEquals(runs, storage.getMap().size());
        assertTrue(storage.getMap().containsValue(value));

        Map<UUID, String> map = storage.getMap();
        for (Map.Entry<UUID, String> entry :
                map.entrySet()) {
            errorCollector.checkThat(entry.getValue(), is(value));
        }
        //logger.debug("map: {}", map);
    }

    @Test
    public void createByValue1() {
        UUIDStorage<String> storage = new UUIDMemoryStorage<>();

        storage.create("some sample string");
        storage.create("another example 2");

        MapUtils.printMap(storage.getMap());
    }

    @Test
    public void createByValue2() {
        UUIDMemoryStorage<String> storage = new UUIDMemoryStorage<>();

        storage.create("some sample string");
        storage.create("another example 2");

        MapUtils.printMap(storage.getMap());
    }

    @Test
    public void createByValue3() {
        UUIDStorage<String> storage = UUIDStorageFactory.create(Storage.Type.MEMORY, String.class);

        storage.create("some sample string");
        storage.create("another example 2");

        MapUtils.printMap(storage.getMap());
    }

    @Test
    public void createByValue4() {
        UUIDStorage<String> storage = UUIDStorageFactory.create(String.class);

        storage.create("some sample string");
        storage.create("another example 2");

        MapUtils.printMap(storage.getMap());
    }

    @Test
    public void create1() {
    }

    @Test
    public void read() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void isExist() {
    }

    @Test
    public void isExistValue() {
    }

    @Test
    public void search() {
    }

    @Test
    public void getMap() {
    }
}